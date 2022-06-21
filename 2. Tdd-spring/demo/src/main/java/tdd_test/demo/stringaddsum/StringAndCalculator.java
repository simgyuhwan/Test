package tdd_test.demo.stringaddsum;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAndCalculator {
    public static Integer splitAndSum(String s) {
        if(isZeroOrNull(s)){
            return 0;
        }
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(s);
        if(matcher.find()){
            String findMatches = matcher.group(1);
            return Arrays.stream(matcher.group(2)
                    .split(findMatches))
                    .map(n -> Integer.parseInt(n))
                    .reduce(0, Integer::sum);
        }

        return Arrays.stream(s.split(",|:"))
                .map(n -> Integer.parseInt(n))
                .reduce(0, Integer::sum);
    }

    private static boolean isZeroOrNull(String s) {
        return ObjectUtils.isEmpty(s) || s.trim().length() == 0;
    }
}
