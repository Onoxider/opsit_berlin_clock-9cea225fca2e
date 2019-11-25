package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.mengenlehreuhr.BerlinClockTimeConverter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BerlinClockTimeConverterTest {

    TimeConverter timeConverter = new BerlinClockTimeConverter();

    @Test
    public void testMaxValuesReturnsError() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> timeConverter.convertTime("24:59:59"));
    }

    @Test
    public void testUpperBoundNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> timeConverter.convertTime("24:00:01"));
    }

    @Test
    public void testNegativeNumberReturnsError() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> timeConverter.convertTime("-24:00:01"));
    }

    @Test
    public void testNullReturnsError() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> timeConverter.convertTime(null));
    }

    @Test
    public void testInvalidNumberOfDigitsReturnsError() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> timeConverter.convertTime("24:000:01"));
    }

    @Test
    public void testValidInputMin() {
        String time = timeConverter.convertTime("00:00:00");
        String expected = joinWithLineSeparator("Y", "OOOO", "OOOO", "OOOOOOOOOOO", "OOOO");
        assertEquals(expected, time);
    }

    @Test
    public void testValidInputOddSevondsAndLessThanFifteenMinutes() {
        String time = timeConverter.convertTime("13:17:01");
        String expected = joinWithLineSeparator("O", "RROO", "RRRO", "YYROOOOOOOO", "YYOO");
        assertEquals(expected, time);
    }

    @Test
    public void testValidInputUpperBoundMinutes() {
        String time = timeConverter.convertTime("23:59:59");
        String expected = joinWithLineSeparator("O", "RRRR", "RRRO", "YYRYYRYYRYY", "YYYY");
        assertEquals(expected, time);
    }

    @Test
    public void testValidInputMax() {
        String time = timeConverter.convertTime("24:00:00");
        String expected = joinWithLineSeparator("Y", "RRRR", "RRRR", "OOOOOOOOOOO", "OOOO");
        assertEquals(expected, time);
    }

    private static String joinWithLineSeparator(String... strings) {
        return String.join(System.lineSeparator(), strings);
    }
}
