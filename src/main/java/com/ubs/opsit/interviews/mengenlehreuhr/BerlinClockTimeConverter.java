package com.ubs.opsit.interviews.mengenlehreuhr;

import com.ubs.opsit.interviews.TimeConverter;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BerlinClockTimeConverter implements TimeConverter {

    private static final Pattern pattern = Pattern.compile("([01]\\d|2[0-3]|24(?=:00?:00?$)):([0*5]\\d):([0-5]\\d)$");

    private static final char YELLOW = 'Y';
    private static final char RED = 'R';
    private static final char OFF = 'O';

    @Override
    public String convertTime(String aTime) {

        if (!pattern.matcher(aTime).matches() || aTime == null) {
            throw new IllegalArgumentException("invalid input: Time should fall within the range of  00:00:00 to 24:00:00");
        }

        List<Integer> timeParts = Stream.of(aTime.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return "";
    }

    private static String symbolMultiplier(char c, int times) {

        if (times == 0) return "";
        if (times == 1) return String.valueOf(c);
        return c + symbolMultiplier(c, times - 1);
    }
}
