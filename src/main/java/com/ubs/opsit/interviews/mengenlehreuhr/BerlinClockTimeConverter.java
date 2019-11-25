package com.ubs.opsit.interviews.mengenlehreuhr;

import com.ubs.opsit.interviews.TimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ubs.opsit.interviews.mengenlehreuhr.BerlinTimeConstants.HOURS_MAX_NUMBER;
import static com.ubs.opsit.interviews.mengenlehreuhr.BerlinTimeConstants.LINE_SEPARATOR;
import static com.ubs.opsit.interviews.mengenlehreuhr.BerlinTimeConstants.MINUTES_MAX_NUMBER;
import static com.ubs.opsit.interviews.mengenlehreuhr.BerlinTimeConstants.OFF;
import static com.ubs.opsit.interviews.mengenlehreuhr.BerlinTimeConstants.RED;
import static com.ubs.opsit.interviews.mengenlehreuhr.BerlinTimeConstants.TIME_PATTERN;
import static com.ubs.opsit.interviews.mengenlehreuhr.BerlinTimeConstants.YELLOW;
import static org.apache.commons.lang.StringUtils.repeat;

public class BerlinClockTimeConverter implements TimeConverter {

    private static final Logger LOG = LoggerFactory.getLogger(BerlinClockTimeConverter.class);

    /**
     *
     * @param time - 24h time format between 00:00:00 and 24:00:00
     * @return String representing Berlin Clock
     */
    @Override
    public String convertTime(String time) {

        if (time == null || !TIME_PATTERN.matcher(time).matches()) {
            throw new IllegalArgumentException("invalid input: Time should fall within the range of  00:00:00 to 24:00:00");
        }
        List<Integer> timeParts = Stream.of(time.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String hoursLamps   = getLamps(timeParts.get(0), HOURS_MAX_NUMBER, RED, false);
        String minutesLamps = getLamps(timeParts.get(1), MINUTES_MAX_NUMBER, YELLOW, true);
        String secondsLamp  = timeParts.get(2) % 2 == 0 ? YELLOW : OFF;

        return String.join(LINE_SEPARATOR, secondsLamp, hoursLamps, minutesLamps);
    }

    /**
     * Calculates On and OFF lamps for a given type of timeunit
     *
     * @param units - amount of units to be converted to lamps on
     * @param maxValue - max possible value for current timeunit
     * @param color - color of lamps
     * @param isReplacementWithRedRequired - enables changing light for every 3rd lamp in fiveUnits line
     * @return String representation
     */
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
