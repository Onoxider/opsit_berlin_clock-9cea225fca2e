package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.mengenlehreuhr.BerlinClockTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Runner {

    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {

        TimeConverter timeConverter = new BerlinClockTimeConverter();
        LOG.info("Please enter the time to be converted to Berlin clock format. Press esc to exit");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            }

            String berlinTime = "";
            try {
                berlinTime = timeConverter.convertTime(input);
            } catch (IllegalArgumentException e) {
                LOG.info(e.getMessage());
            }
            System.out.println(berlinTime);
        }
    }
}
