package com.zjpavt.test;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.action.GetPropertyAction;

import java.io.UnsupportedEncodingException;
import  static  java.lang.System.out;
import java.nio.charset.Charset;

public class Base64Test {
    public static Logger logger = LoggerFactory.getLogger(Base64Test.class);
    public static final  String CHARSET = "UTF-8";
    public static  String testString = new GetPropertyAction("file.encoding").run();

    public static  void  main(String[] args){
        testString = "askdjf;lasd";
        commonsBase64(testString);
        utilBase64(testString);
        testString = "df;lasd";
        commonsBase64(testString);
        utilBase64(testString);
    }
    public static void commonsBase64(String testString){
        try {
            byte[] enMessage = Base64.encodeBase64(testString.getBytes(CHARSET));
            logger.info("encode message=" + byte2String(enMessage));
            out.println(new String(Base64.decodeBase64(enMessage),CHARSET));
            //logger.info("encode message=" + enMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void utilBase64(String testString){
        try {
            byte[] enMessage = java.util.Base64.getEncoder().encode(testString.getBytes(CHARSET));
            logger.info("encode message=" + byte2String(enMessage));
            String str = java.util.Base64.getEncoder().encodeToString(testString.getBytes(CHARSET));
            logger.info("encode message decode to string = " + java.util.Base64.getEncoder().encodeToString(testString.getBytes(CHARSET)));
            logger.info(byte2String(str.getBytes(CHARSET)));
            out.println(new String(java.util.Base64.getDecoder().decode(enMessage),CHARSET));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String byte2String(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte byteOne :bytes){
            sb.append(String.valueOf(byteOne));
        }
        return sb.toString();
    }
}
