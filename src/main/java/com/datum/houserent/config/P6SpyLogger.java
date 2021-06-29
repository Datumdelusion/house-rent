package com.datum.houserent.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * @author datum
 * @date 2021/6/22 22:24
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    /**
     * Formats a log message for the logging module
     *
     * @param connectionId the id of the connection
     * @param now          the current ime expressing in milliseconds
     * @param elapsed      the time in milliseconds that the operation took to complete
     * @param category     the category of the operation
     * @param prepared     the SQL statement with all bind variables replaced with actual values
     * @param sql          the sql statement executed
     * @param url          the database url where the sql statement executed
     * @return the formatted log message
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        StringBuilder log = new StringBuilder();
        return StringUtils.isNotBlank(sql) ? log.append("【").append(now).append("】").append("本次执行时间: ")
                .append(elapsed).append("ms").append("\n执行的SQL语句: ").append(sql.replaceAll("[\\s]+", " "))
                .toString()
                : "";
    }
}
