package com.jgyuer.lib.wechat;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
 * Created by KOH on 2017/4/25.
 * <p>
 * webFramework
 */
public class WechatAESUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
            params.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);// 初始化
            return cipher.doFinal(content);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }
}
