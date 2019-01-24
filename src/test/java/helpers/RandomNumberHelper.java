package helpers;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomNumberHelper {

    public static String getRandomPassword() {
        Random rnd = new Random();
        return "test" + String.valueOf(100000 + rnd.nextInt(999999));
    }

    public static String generateRandomEmail() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        return RandomStringUtils.random(12, allowedChars) + "@netflix.com";
    }
}
