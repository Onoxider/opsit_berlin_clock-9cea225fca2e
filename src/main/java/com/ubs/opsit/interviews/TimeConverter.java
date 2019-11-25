package com.ubs.opsit.interviews;

public interface TimeConverter {

    /**
     * Converts time
     *
     * @param time - 24h time format between 00:00:00 and 24:00:00
     * @return String representing Berlin Clock
     */
    String convertTime(String time);

}
