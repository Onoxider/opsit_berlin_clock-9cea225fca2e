package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.mengenlehreuhr.BerlinClockTimeConverter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
    public void testValidInputOddSevondsAndLessThanFifteenMinutes() {
        String time = timeConverter.convertTime("13:17:01");

    }

    @Test
    public void testValidInputEvenSecondsAndMoreThanFifteenMinutes() {
        String time = timeConverter.convertTime("17:46:34");

    }

    @Test
    public void testValidInputUpperBoundMinutes() {
        String time = timeConverter.convertTime("23:59:59");
    }

    @Test
    public void testValidInputUpperBoundHours() {
        String time = timeConverter.convertTime("24:00:00");
    }

    private static String joinWithLineSeparator(String... strings) {
        return String.join(System.lineSeparator(), strings);
    }
}
