package com.zjpavt.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSASignature {
    private static final String CHARSET = "UTF-8";
    private static final String RSA_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static String messageToTest= "messagd";
    private PrivateKey privateKey;
    private PublicKey publicKey;
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException {
        RSASignature rsaSignature = new RSASignature();
        KeyPair keyPair = rsaSignature.getRSAKeyPair(2048);
        byte[] encodeMessage = rsaSignature.encodeByRSA(messageToTest);
        logger.info(new String(encodeMessage,CHARSET));
        String decodeMessage = rsaSignature.decodeByRSA(encodeMessage);
        logger.info("decodeMessage = " + decodeMessage);
        String signData = rsaSignature.sign(messageToTest);
        logger.info(signData + signData.length());
        boolean verify = rsaSignature.verify(messageToTest,signData);
        logger.info(String.valueOf(verify));

    }

    public RSASignature(){
        getRSAKeyPair(2048);
    }
       private static Logger logger = LoggerFactory.getLogger(RSASignature.class);
    public KeyPair getRSAKeyPair(int length){
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(length);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                this.privateKey =  keyPair.getPrivate();
                this.publicKey = keyPair.getPublic();
                return keyPair;
            } catch (NoSuchAlgorithmException e) {
                logger.warn("获取RSAKeyPairGenerator失败");
            }
            return null;
    }

/*    public static RSAPublicKey getPubKey(KeyPair keyPair){
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
    }*/

    public byte[] encodeByRSA(String content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PublicKey pubKey = this.publicKey;
        X509EncodedKeySpec X509EncodedKeySpec = new X509EncodedKeySpec(pubKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey rsaPublicKey = keyFactory.generatePublic(X509EncodedKeySpec);
        //获取公钥
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,rsaPublicKey);
        //String enmessage = null;
        try {
            //enmessage = new String(cipher.doFinal(Base64.getEncoder().encode(content.getBytes(CHARSET))),CHARSET);
            byte[] encodeMessage = cipher.doFinal(Base64.getEncoder().encode(content.getBytes(CHARSET)));
            logger.info("encodeMessage       " + Base64.getEncoder().encodeToString(encodeMessage));
            return cipher.doFinal(Base64.getEncoder().encode(content.getBytes(CHARSET)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decodeByRSA(byte[] content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey rsaPrivateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        //不考虑变更的情况
        cipher.init(Cipher.DECRYPT_MODE,rsaPrivateKey);
        logger.info(Base64.getEncoder().encodeToString(content));
        String decodeMessage = new String(cipher.doFinal(content),CHARSET);
        return new String(Base64.getDecoder().decode(cipher.doFinal(content)));
    }

    public String sign(String signDate){
        String signedData = null;
        try {
            byte[] keyBytes = Base64.getEncoder().encode(signDate.getBytes(CHARSET));
            PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(this.privateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            PrivateKey rsaPrivateKey = keyFactory.generatePrivate(x509EncodedKeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(rsaPrivateKey);
            signature.update(keyBytes);
            return Base64.getEncoder().encodeToString(signature.sign());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return signedData;
    }

    public boolean verify(String signData, String sign){
        try {
            byte[] signBytes= Base64.getDecoder().decode(sign);
            byte[] signDataBytes = Base64.getEncoder().encode(signData.getBytes(CHARSET));
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(this.publicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            PublicKey rsaPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(rsaPublicKey);
            signature.update(signDataBytes);
            logger.info(String.valueOf(Base64.getDecoder().decode(sign).length) + " " + sign.length()) ;
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

/*    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

X509
    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }


    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }


    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }*/
}

