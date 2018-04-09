package com.zjpavt.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PatternTest {
    static Logger logger = LoggerFactory.getLogger(PatternTest.class);
    public static void main(String[] args){
        String patternString = "[\\s\\S]*([0-9]{5}):([0-9]+)[\\s\\S]*";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher("faf111111:1111111");
        logger.info(String.valueOf(matcher.matches()));
        if(matcher.matches()){
            logger.info(matcher.group());
            logger.info(matcher.group(1));
            logger.info(matcher.group(2));
        }
    }


}
