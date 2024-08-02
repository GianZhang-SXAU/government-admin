package com.zja.sxau.govenmentadmin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

        /**
         * 获取当前日期的字符串格式
         * 格式为：yyyyMMdd
         *
         * @return 当前日期的字符串格式
         */
        public static String getCurrentDateString() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.format(new Date());
        }

        /**
         * 获取指定日期的字符串格式
         * 格式为：yyyyMMdd
         *
         * @param date 指定的日期
         * @return 日期的字符串格式
         */
        public static String getDateString(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.format(date);
        }

        /**
         * 计算两个时间之间的天数差
         *
         * @param startDate 起始日期
         * @param endDate   结束日期
         * @return 两个时间之间的天数差
         */
        public static long calculateDaysDifference(Date startDate, Date endDate) {
            long diffInMillies = endDate.getTime() - startDate.getTime();
            return diffInMillies / (1000 * 60 * 60 * 24);
        }

        /**
         * 检查日期是否为今天
         *
         * @param date 需要检查的日期
         * @return 如果日期为今天则返回true，否则返回false
         */
        public static boolean isToday(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(new Date());
            String targetDate = sdf.format(date);
            return currentDate.equals(targetDate);
        }
    }

