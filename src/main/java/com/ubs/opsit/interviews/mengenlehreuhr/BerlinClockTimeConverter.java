package com.ubs.opsit.interviews.mengenlehreuhr;

import com.ubs.opsit.interviews.TimeConverter;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static com.ubs.opsit.interviews.mengenlehreuhr.Constants.*;
import static org.apache.commons.lang.StringUtils.repeat;

public class BerlinClockTimeConverter implements TimeConverter {

    @Override
    public String convertTime(String aTime) {

        if (aTime == null || !TIME_PATTERN.matcher(aTime).matches()) {
            throw new IllegalArgumentException("invalid input: Time should fall within the range of  00:00:00 to 24:00:00");
        }
        List<Integer> timeParts = Stream.of(aTime.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String hoursLamps   = getLamps(timeParts.get(0), HOURS_MAX_NUMBER, RED, false);
        String minutesLamps = getLamps(timeParts.get(1), MINUTES_MAX_NUMBER, YELLOW, true);
        String secondsLamp  = timeParts.get(2) % 2 == 0 ? YELLOW : OFF;

        return String.join(LINE_SEPARATOR, secondsLamp, hoursLamps, minutesLamps);
    }

    private String getLamps(int units, int maxValue,  String color, boolean isReplacementWithRedRequired) {

        int fiveUnitsLampsActiveNumber = units / 5;
        int fiveUnitsLampsOff          = (maxValue - units) / 5;
        int oneUnitLampsActiveNumber   = units % 5;
        int oneUnitLampsOff            = (maxValue - units) % 5;

        String fiveUnitsActiveLamps = repeat(color, fiveUnitsLampsActiveNumber);
        if (isReplacementWithRedRequired && YELLOW.equals(color)) {
            fiveUnitsActiveLamps = fiveUnitsActiveLamps.replaceAll(color + "{3}", color + color + RED);
        }
        String fiveUnitsLamps = fiveUnitsActiveLamps + repeat(OFF, fiveUnitsLampsOff);
        String oneUnitLamps = repeat(color, oneUnitLampsActiveNumber) + repeat(OFF, oneUnitLampsOff);

        return String.join(LINE_SEPARATOR, fiveUnitsLamps, oneUnitLamps);
    }
}
