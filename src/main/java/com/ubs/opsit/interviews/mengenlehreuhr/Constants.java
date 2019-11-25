package com.ubs.opsit.interviews.mengenlehreuhr;

import java.util.regex.Pattern;

public class Constants {

    public static final String YELLOW = "Y";
    public static final String RED = "R";
    public static final String OFF = "O";
    public static final int HOURS_MAX_NUMBER = 24;
    public static final int MINUTES_MAX_NUMBER = 59;
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final Pattern TIME_PATTERN = Pattern.
            compile("([01]\\d|2[0-3]|24(?=:00?:00?$)):([0-5]\\d):([0-5]\\d)$");
}
