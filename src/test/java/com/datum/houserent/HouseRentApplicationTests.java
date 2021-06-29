package com.datum.houserent;

import com.datum.houserent.dao.mapper.LocationMapper;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.Location;
import com.datum.houserent.service.HouseService;
import com.datum.houserent.service.LocationService;
import com.datum.houserent.utils.JsonUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class HouseRentApplicationTests {

    @Autowired
    LocationService locationService;
    @Autowired
    HouseService houseService;

    @Test
    void houseJsoup() throws IOException {
        Connection connect = Jsoup.connect("https://cd.ziroom.com/x/808105461.html");
        Document document = connect.get();

        House house = new House();
        String name = document.getElementsByClass("Z_name").text();
        Set<String> pics = document.getElementsByClass("Z_slider").stream().map(e -> {
            String attr = e.getElementsByTag("img").first().attr("src");
            return attr.substring(2);
        }).collect(Collectors.toSet());
        String neighborhood = document.getElementsByClass("Z_village_info").text().split(" ")[0];
        String[] message = document.getElementsByClass("Z_home_o").text().split(" ");
        String location = message[1];
        Integer storey = Integer.parseInt(message[2].substring(2, 3));
        boolean elevator = message[3].charAt(2) == '有';
        Integer year = Integer.parseInt(message[4].substring(2, 6));
        Double green = Double.parseDouble(message[6].substring(2, 4)) / 100;



        house.setName(name);
        house.setHead(pics.iterator().next());
        house.setPics(JsonUtil.toJsonString(pics));
        house.setNeighbourhood(neighborhood);
        house.setDetailLocation(location);
        house.setStorey(storey);
        house.setElevator(elevator);
        house.setYears(year);
        house.setGreenArea(green);

        System.out.println(house);
    }


    @Test
    void contextLoads() {
        File file = new File("E:\\JavaProject\\house-rent\\src\\main\\resources\\area.txt");

        List<Province> provinces = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        List<Region> regions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            int all = 0;
            while (!((line = reader.readLine()) == null)) {
                if (line.contains("{") || line.contains("}")) {
                    continue;
                }
                all++;
                String[] split = line.split("\"");
                String id = split[1].trim();
                String name = split[3].trim();
                String[] numbers = splitId(id);
                if (!"00".equals(numbers[0]) && "00".equals(numbers[1]) && "00".equals(numbers[2])) {
                    provinces.add(new Province(numbers[0], name));
                }
                if (!"00".equals(numbers[0]) && !"00".equals(numbers[1]) && "00".equals(numbers[2])) {
                    cities.add(new City(numbers[0], numbers[1], name));
                }
                if (!"00".equals(numbers[0]) && !"00".equals(numbers[2])) {
                    regions.add(new Region(numbers[0], numbers[1], numbers[2], name));
                }

            }
//            System.out.println(provinces);
//            System.out.println(cities);
//            System.out.println(regions);

            List<Location> provincesList = new ArrayList<>();
            for (Province province : provinces) {
                Location location = new Location();
                location.setName(province.getName());
                location.setId(Integer.parseInt(province.getProvince()));
                location.setPid(-1);
                location.setPinyin(getHanziInitials(province.getName()));

                String[] strings = {province.getProvince()};
                String s = JsonUtil.toJsonString(strings);
                location.setGenealogy(s);

                location.setLevel(1);
                provincesList.add(location);
            }
//            System.out.println(provincesList);
            locationService.saveBatch(provincesList);

            List<Location> citiesList = new ArrayList<>();
            for (City city : cities) {
                Location location = new Location();
                location.setName(city.getName());

                String idSt = city.getProvince() + city.getCity();
                int id = Integer.parseInt(idSt);
                location.setId(id);

                location.setPid(Integer.parseInt(city.getProvince()));
                location.setPinyin(getHanziInitials(city.getName()));

                String[] strings = {city.getProvince(), city.getCity()};
                String s = JsonUtil.toJsonString(strings);
                location.setGenealogy(s);

                location.setLevel(2);
                citiesList.add(location);
            }

            locationService.saveBatch(citiesList);

            List<Location> regionsList = new ArrayList<>();
            for (Region region : regions) {
                Location location = new Location();
                location.setName(region.getName());

                String idSt = region.getProvince() + region.getCity() + region.getRegion();
                int id = Integer.parseInt(idSt);
                location.setId(id);

                location.setPid(Integer.parseInt(region.getProvince() + region.getCity()));
                location.setPinyin(getHanziInitials(region.getName()));

                location.setGenealogy(JsonUtil.toJsonString(new String[]{region.getProvince(), region.getCity(), region.getRegion()}));

                location.setLevel(3);
                regionsList.add(location);
            }
            locationService.saveBatch(regionsList);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String[] splitId(String idString) {
        String[] strings = new String[3];

        strings[0] = idString.substring(0, 2);

        strings[1] = idString.substring(2, 4);

        strings[2] = idString.substring(4);


        return strings;
    }

    public static String getHanziInitials(String hanzi) {
        String result = null;
        if (null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != stringArray) {
                    sb.append(stringArray[0].charAt(0));
                }
            }
            if (sb.length() > 0) {
                result = sb.toString().toUpperCase();
            }
        }
        return result;
    }

    @Test
    void pinyin() {
        Location entity = new Location();
        entity.setId(12);
        entity.setName("测试");
        locationService.save(entity);
    }

    @Test
    void sub() {
        String id = "442000117";
        System.out.println(id.substring(4));
    }

}
