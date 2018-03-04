package com.yang.Jks;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * Created by yz on 2018/2/14.
 */
public class TestJks {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("server.keystore");
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("JKS");
            try {
                keyStore.load(resource.getInputStream(), "123456".toCharArray());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }


    }

    public static String encodeBase64(String string) throws Exception {
        return new BASE64Encoder().encode(string.getBytes("UTF-8"));
    }

    public static String decodeBase64(String base64Str) throws Exception {
        return new String(new BASE64Decoder().decodeBuffer(base64Str), "UTF-8");
    }

    @Test
    public void TestKeyStore() {
        try {
            Resource resource = new ClassPathResource("server.keystore");
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(resource.getInputStream(), "123456".toCharArray());
            Certificate certificate = keyStore.getCertificate("server");
            PublicKey publicKey = certificate.getPublicKey();
            System.out.println("提取的公钥为___:\\n" + encodeBase64(publicKey.toString()));

            PrivateKey privateKey = (PrivateKey) keyStore.getKey("server", "123456".toCharArray());
            System.out.println("提取的私钥为___:\n" + encodeBase64(privateKey.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
