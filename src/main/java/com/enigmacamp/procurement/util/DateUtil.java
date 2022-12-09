package com.enigmacamp.procurement.util;

import java.util.Calendar;
import java.util.Date;

public abstract class DateUtil {
    public static Date getFirstDayOfTheMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getLastDayOfTheMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }
}
