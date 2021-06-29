package com.datum.houserent;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HouseRentApplicationTests {

    @Test
    void contextLoads() {
        File file = new File("E:\\JavaProject\\house-rent\\src\\main\\resources\\area.txt");

        List<Province> provinces = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        List<Region> regions = new ArrayList<>();

        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line = "";

            int all = 0;
            while (!((line = reader.readLine()) == null)) {
                if (line.contains("{")||line.contains("}")) {
                    continue;
                }
                all++;
                String[] split = line.split("\"");
                String id = split[1].trim();
                String name = split[3].trim();
                String[] numbers = splitId(id);
                if (!"00".equals(numbers[0]) && "00".equals(numbers[1]) && "00".equals(numbers[2])) {
                    provinces.add(new Province(Integer.parseInt(numbers[0]), name));
                }
                if (!"00".equals(numbers[0]) && !"00".equals(numbers[1])&&"00".equals(numbers[2])) {
                    cities.add(new City(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), name));
                }
                if (!"00".equals(numbers[0]) &&!"00".equals(numbers[2])) {
                    regions.add(new Region(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]), name));
                }

            }
            System.out.println(provinces);
            System.out.println(cities);
            System.out.println(regions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String[] splitId(String idString) {
        String[] strings = new String[3];

        strings[0] = idString.substring(0, 2);

        strings[1] = idString.substring(2, 4);

        strings[2] = idString.substring(4, 6);


        return strings;
    }

    public static String getHanziInitials(String hanzi) {
        String result = null;
        if(null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if(null != stringArray) {
                    sb.append(stringArray[0].charAt(0));
                }
            }
            if(sb.length() > 0) {
                result = sb.toString().toUpperCase();
            }
        }
        return result;
    }

    @Test
    void pinyin() {
        String hello = getHanziInitials("重庆市");
        System.out.println(hello);

    }

}
