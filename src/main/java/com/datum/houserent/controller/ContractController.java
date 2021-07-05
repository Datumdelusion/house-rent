package com.datum.houserent.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.datum.houserent.exception.BadRequestException;
import com.datum.houserent.exception.ServiceException;
import com.datum.houserent.model.entity.Contract;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.Intention;
import com.datum.houserent.model.entity.User;
import com.datum.houserent.model.entity.enums.ContractStatus;
import com.datum.houserent.model.entity.enums.HouseStatus;
import com.datum.houserent.model.vo.constract.ConstractVO;
import com.datum.houserent.model.vo.user.UserVO;
import com.datum.houserent.service.ContractService;
import com.datum.houserent.service.HouseService;
import com.datum.houserent.service.IntentionService;
import com.datum.houserent.service.UserService;
import com.datum.houserent.support.ResponsePack;
import com.datum.houserent.utils.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author datum
 * @since 2021-06-27
 */
@RestController
@ResponsePack
@Api(tags = "合约 - 前端控制器")
public class ContractController {

    private final HouseService houseService;

    private final UserService userService;

    private final ContractService contractService;

    private final IntentionService intentionService;

    public ContractController(HouseService houseService, UserService userService,
                              ContractService contractService, IntentionService intentionService) {
        this.houseService = houseService;
        this.userService = userService;
        this.contractService = contractService;
        this.intentionService = intentionService;
    }

    @ApiOperation("卖家建立合约")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/contracts/intention/{intentionId:\\d+}", method = RequestMethod.PUT)
    public boolean createContract(@ApiParam("意向id") @PathVariable Integer intentionId) {
        Intention intention = intentionService.getById(intentionId);
        if (intention == null) {
            throw new BadRequestException("用户意向id不存在", "用户意向条目不存在");
        }
        if (!intention.getLessor().equals(StpUtil.getLoginIdAsInt())) {
            throw new BadRequestException("该意向id不属于当前出租者", "请操作自己的意向条目yo");
        }
        House house = houseService.getById(intention.getHouseId());
        if (house == null) {
            throw new BadRequestException("房源id不存在", "房源走丢了");
        }
        if (!house.getLessor().equals(StpUtil.getLoginIdAsInt())) {
            throw new BadRequestException("房源不属于操作者", "房源异常，不属于操作者");
        }
        if (house.getStatus().equals(HouseStatus.OffShelf)) {
            throw new BadRequestException("房屋已出租", "房屋已出租，请勿重复操作");
        }
        Contract one = contractService.getOne(new LambdaQueryWrapper<Contract>()
                .eq(Contract::getHouseId, house.getId())
                .eq(Contract::getLessor, StpUtil.getLoginIdAsInt())
                .eq(Contract::getUserId, intention.getUserId()));
        if (one != null&&one.getSuccess()) {
            throw new BadRequestException("合约已存在", "不要重复提价合约");
        }
        intentionService.removeById(intentionId);
        Contract contract = new Contract();
        contract.setLessor(StpUtil.getLoginIdAsInt());
        contract.setHouseId(intention.getHouseId());
        contract.setUserId(intention.getUserId());
        contract.setLessorSign(true);
        return contractService.save(contract);
    }

    @ApiOperation("用户确认合约并输入租赁时长")
    @SaCheckRole(value = {"admin", "user"}, mode = SaMode.OR)
    @RequestMapping(value = "/user/contracts/{contractId:\\d+}/{month:\\d+}", method = RequestMethod.POST)
    public boolean confirmContract(@ApiParam("合约id") @PathVariable Integer contractId,
                                   @ApiParam("租赁时长（月）") @PathVariable Integer month) {

        Contract contract = contractService.getById(contractId);
        if (contract == null) {
            throw new BadRequestException("合约id不存在", "合约已经不存在了");
        }
        if (!contract.getUserId().equals(StpUtil.getLoginIdAsInt())) {
            throw new BadRequestException("合约不属于当前操作者", "请操作自己的合约");
        }
        House house = houseService.getById(contract.getHouseId());
        if (contract.getSuccess() != null && contract.getSuccess()) {
            throw new BadRequestException("合约已经生效了", "该合约已签约，请不要重复操作");
        }
        if (contract.getSuccess() != null && !contract.getSuccess()) {
            throw new BadRequestException("合约已失效", "合约已失效，房源已被别人签约");
        }
        contract.setRentMoney(house.getMoneyMonth() * month);
        contract.setUserSign(true);
        contract.setTime(LocalDate.now().plusMonths(month));
        return contractService.updateById(contract);
    }

    @ApiOperation("我的租房合约")
    @SaCheckRole(value = {"admin", "user"}, mode = SaMode.OR)
    @RequestMapping(value = "/user/contracts", method = RequestMethod.GET)
    public List<ConstractVO> rentContract(@RequestParam ContractStatus contractStatus) {
        int userId = StpUtil.getLoginIdAsInt();

        Boolean wrapper = null;
        if (contractStatus.equals(ContractStatus.success)) {
            wrapper = true;
        }
        if (contractStatus.equals(ContractStatus.failed)) {
            wrapper = false;
        }

        LambdaQueryWrapper<Contract> queryWrapper = null;
        if (wrapper != null) {
            queryWrapper = new LambdaQueryWrapper<Contract>().eq(Contract::getUserId, userId).eq(Contract::getSuccess, wrapper ? 1 : 0);
        } else {
            queryWrapper = new LambdaQueryWrapper<Contract>().eq(Contract::getUserId, userId).isNull(Contract::getSuccess);
        }

        List<Contract> contracts = contractService.list(queryWrapper);
        if (contracts.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> houseIds = contracts.stream().map(Contract::getHouseId).collect(Collectors.toList());
        Map<Integer, House> houseMap = houseService.list(new LambdaQueryWrapper<House>()
                .in(House::getId, houseIds))
                .stream()
                .collect(Collectors
                        .toMap(House::getId, Function.identity()));
        List<ConstractVO> convert = BeanUtil.convert(contracts, ConstractVO.class);
        for (ConstractVO constractVO : convert) {
            constractVO.setHouseName(houseMap.get(constractVO.getHouseId()).getName());
        }
        return convert;
    }

    @ApiOperation("我的出租合约")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/contracts", method = RequestMethod.GET)
    public List<ConstractVO> lessorContract(@RequestParam ContractStatus contractStatus) {
        int lessor = StpUtil.getLoginIdAsInt();

        Boolean wrapper = null;
        if (contractStatus.equals(ContractStatus.success)) {
            wrapper = true;
        }
        if (contractStatus.equals(ContractStatus.failed)) {
            wrapper = false;
        }

        LambdaQueryWrapper<Contract> queryWrapper = null;
        if (wrapper != null) {
            queryWrapper = new LambdaQueryWrapper<Contract>().eq(Contract::getLessor, lessor).eq(Contract::getSuccess, wrapper ? 1 : 0);
        } else {
            queryWrapper = new LambdaQueryWrapper<Contract>().eq(Contract::getLessor, lessor).isNull(Contract::getSuccess);
        }
        List<Contract> contracts = contractService.list(queryWrapper);
        if (contracts.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> houseIds = contracts.stream().map(Contract::getHouseId).collect(Collectors.toList());
        Map<Integer, House> houseMap = houseService.list(new LambdaQueryWrapper<House>()
                .in(House::getId, houseIds))
                .stream()
                .collect(Collectors
                        .toMap(House::getId, Function.identity()));
        List<ConstractVO> convert = BeanUtil.convert(contracts, ConstractVO.class);
        for (ConstractVO constractVO : convert) {
            constractVO.setHouseName(houseMap.get(constractVO.getHouseId()).getName());
        }
        return convert;
    }

    @ApiOperation("卖家最终确认一个合约")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/contracts/{contractId:\\d+}", method = RequestMethod.PUT)
    @Transactional(rollbackFor = {BadRequestException.class, ServiceException.class})
    public boolean lessorConfirmContract(@ApiParam("合约id") @PathVariable Integer contractId) {

        Contract contract = contractService.getById(contractId);
        if (contract == null) {
            throw new BadRequestException("合约id不存在", "合约已经走丢了");
        }
        if (!contract.getLessor().equals(StpUtil.getLoginIdAsInt())) {
            throw new BadRequestException("合约不属于当前操作者", "请操作自己的合约");
        }
        if (contract.getSuccess() != null && contract.getSuccess()) {
            throw new BadRequestException("合约已经签了", "合约已签，不要重复操作");
        }
        if (contract.getSuccess() != null && !contract.getSuccess()) {

            throw new BadRequestException("该合约已经失败", "该房屋已有其他合约");
        }
        if (contract.getUserSign() == null || !contract.getUserSign()) {
            throw new BadRequestException("用户未同意该合约", "请等待用户同意该合约");
        }
        List<Contract> contracts = contractService.list(new LambdaQueryWrapper<Contract>().eq(Contract::getHouseId, contract.getHouseId()));
        for (Contract c : contracts) {
            c.setSuccess(false);
        }
        House house = houseService.getById(contract.getHouseId());
        if (house == null) {
            throw new BadRequestException("房源id不存在", "系统错误，房源已经丢失");
        }
        House houseSave = new House();
        houseSave.setId(house.getId());
        houseSave.setStatus(HouseStatus.OffShelf);
        if (!houseService.updateById(houseSave)) {
            throw new ServiceException("更新房屋状态失败", "系统错误，请稍后再试");
        }

        if (!contractService.updateBatchById(contracts)) {
            throw new ServiceException("保存【失败合约】失败", "系统错误，稍后重试");
        }

        Contract contract1 = new Contract();
        contract1.setId(contractId);
        contract1.setSuccess(true);
        if (!contractService.updateById(contract1)) {
            throw new ServiceException("保存【成功合约】失败", "系统错误，稍后再试");
        }
        return true;

    }

    @ApiOperation("卖家拒绝合约")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/contract/reject/{contractId:\\d+}", method = RequestMethod.PUT)
    public boolean lessorRejectContract(@ApiParam("合约id") @PathVariable Integer contractId) {

        Contract contract = contractService.getById(contractId);

        if (contract == null) {
            throw new BadRequestException("合约id不存在", "合约已经走丢了");
        }
        if (!contract.getLessor().equals(StpUtil.getLoginIdAsInt())) {
            throw new BadRequestException("合约不属于当前操作者", "请操作自己的合约");
        }
        if (contract.getSuccess() != null) {
            throw new BadRequestException("合约" + (contract.getSuccess() ? "已生效" : "已失败"), "合约" + (contract.getSuccess() ? "已生效" : "已失败"));
        }
        Contract con = new Contract();
        con.setId(contractId);
        con.setLessorSign(false);
        con.setSuccess(false);
        return contractService.updateById(con);
    }

    @ApiOperation("买拒绝合约")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/user/contract/reject/{contractId:\\d+}", method = RequestMethod.PUT)
    public boolean userRejectContract(@ApiParam("合约id") @PathVariable Integer contractId) {

        Contract contract = contractService.getById(contractId);

        if (contract == null) {
            throw new BadRequestException("合约id不存在", "合约已经走丢了");
        }
        if (!contract.getUserId().equals(StpUtil.getLoginIdAsInt())) {
            throw new BadRequestException("合约不属于当前操作者", "请操作自己的合约");
        }
        if (contract.getSuccess() != null) {
            throw new BadRequestException("合约" + (contract.getSuccess() ? "已生效" : "已失败"), "合约" + (contract.getSuccess() ? "已生效" : "已失败"));
        }
        Contract con = new Contract();
        con.setId(contractId);
        con.setUserSign(false);
        con.setSuccess(false);
        return contractService.updateById(con);
    }

    @ApiOperation("卖家在合约列表通过用户id获得用户姓名电话")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/contract/userInfo/{userId:\\d+}", method = RequestMethod.GET)
    public UserVO getUserInfo(@ApiParam("用户id")@PathVariable Integer userId) {

        List<Contract> contracts = contractService.list(new LambdaQueryWrapper<Contract>().eq(Contract::getLessor, StpUtil.getLoginIdAsInt()));
        if (contracts.isEmpty()) {
            throw new BadRequestException("当前用户id不属于出租者合约中的用户", "该用户不是你的客户");
        }
        Set<Integer> userIds = contracts.stream().map(Contract::getUserId).collect(Collectors.toSet());
        if (!userIds.contains(userId)) {
            throw new BadRequestException("当前用户id不属于出租者合约中的用户", "该用户不是你的客户");
        }
        User user = userService.getById(userId);
        if (user == null) {
            throw new BadRequestException("当前用户id信息丢失", "用户信息丢失了");
        }
        return BeanUtil.convert(user, UserVO.class);
    }
}

