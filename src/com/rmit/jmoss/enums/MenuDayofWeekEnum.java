package com.rmit.jmoss.enums;

public enum MenuDayofWeekEnum {
    MONDAY
    , TUESDAY
    , WENESDAY
    , THURSDAY
    , FRIDAY
    , SATURDAY
    , SUNDAY
    ;

    public static String[] getNames() {
        String[] values = new String[7];
        for (int i = 0;i < MenuDayofWeekEnum.values().length; i++) {
            values[i] = MenuDayofWeekEnum.values()[i].name();
        }

        return values;
    }
}
