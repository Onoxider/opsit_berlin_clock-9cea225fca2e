package com.ubs.opsit.interviews.mengenlehreuhr;

import com.ubs.opsit.interviews.TimeConverter;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BerlinClockTimeConverter implements TimeConverter {

    private static final Pattern pattern = Pattern.compile("([01]\\d|2[0-3]|24(?=:00?:00?$)):([0-5]\\d):([0-5]\\d)$");

    private static final char YELLOW = 'Y';
    private static final char RED = 'R';
    private static final char OFF = 'O';

    @Override
    public String convertTime(String aTime) {

        if (aTime == null || !pattern.matcher(aTime).matches()) {
            throw new IllegalArgumentException("invalid input: Time should fall within the range of  00:00:00 to 24:00:00");
        }

        List<Integer> timeParts = Stream.of(aTime.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int hours = timeParts.get(0);
        int minutes = timeParts.get(1);
        int seconds = timeParts.get(2);
        int hoursMax = 24;
        int minutesMax = 59;

        int fiveHourLampsNumber = hours / 5;
        int oneHourLampsNumber = hours % 5;
        int fiveMinuteLampsNumber = minutes / 5;
        int oneminuteLampsNumber = minutes % 5;
        char secondsLamp = seconds % 2 == 0 ? YELLOW : OFF;


        String lineSeparator = System.lineSeparator();
        StringBuilder sb = new StringBuilder().append(secondsLamp).append(lineSeparator);
        sb.append(symbolMultiplier(RED, fiveHourLampsNumber)).append(symbolMultiplier(OFF, (hoursMax / 5 - fiveHourLampsNumber))).append(lineSeparator);
        sb.append(symbolMultiplier(RED, oneHourLampsNumber)).append(symbolMultiplier(OFF, (hoursMax % 5 - oneHourLampsNumber))).append(lineSeparator);
        // TODO: 25-Nov-19 remove "" in replacement + create constants class
        String minutesFiveUnits = symbolMultiplier(YELLOW, fiveMinuteLampsNumber).replaceAll(YELLOW + "{3}", "" + YELLOW + YELLOW + RED);
        sb.append(minutesFiveUnits).append(symbolMultiplier(OFF, (minutesMax / 5 - fiveMinuteLampsNumber))).append(lineSeparator);
        sb.append(symbolMultiplier(YELLOW, oneminuteLampsNumber)).append(symbolMultiplier(OFF, (minutesMax % 5 - oneminuteLampsNumber)));

        return sb.toString();
    }

    private static String symbolMultiplier(char c, int times) {

        if (times == 0) return "";
        if (times == 1) return String.valueOf(c);
        return c + symbolMultiplier(c, times - 1);
    }
}
