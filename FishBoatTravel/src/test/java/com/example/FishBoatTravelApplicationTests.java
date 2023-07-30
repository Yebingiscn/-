package com.example;

import com.example.mapper.OrderMapping;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.regex.Pattern;

@SpringBootTest
class FishBoatTravelApplicationTests {
    @Resource
    OrderMapping orderMapping;

    @Test
    void contextLoads() {
        Random random = new Random();
        System.out.println(Math.random()*10000);
    }

    public void findOnlyNumber(String[] str) {
        int temp = Integer.MIN_VALUE;
        Pattern pattern;
        for (String s : str) {
            pattern = Pattern.compile("^[0-9]*$");
            if (pattern.matcher(s).matches() && Integer.parseInt(s) != temp) {
                if (temp != Integer.MIN_VALUE) {
                    System.out.println(s);
                    break;
                } else {
                    temp = Integer.parseInt(s);
                }
            }
        }
    }

}
