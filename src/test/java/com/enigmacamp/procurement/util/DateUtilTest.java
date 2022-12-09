package com.enigmacamp.procurement.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

class DateUtilTest {

    @Test
    void itShould_ReturnFirstDate() {
        Date date = DateUtil.getFirstDayOfTheMonth();
        System.out.println(date);
    }

    @Test
    void itShould_ReturnLastDate() {
        Date date = DateUtil.getLastDayOfTheMonth();
        System.out.println(date);
    }
}