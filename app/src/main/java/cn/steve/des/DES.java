package cn.steve.des;


import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {

    static final String key = "20140401";
    static final String iv = "12345678";

    public static void main(String[] args) {
        try {
            System.out.println(decryptDES("FbWfZtyPPOY="));
            System.out.println(encryptDES("gitsea"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptDES(String encryptString)
        throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return new String(Base64.encode(encryptedData, 0));
    }

    public static String decryptDES(String decryptString)
        throws Exception {
        byte[] byteMi = Base64.decode(decryptString, 0);
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skey, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData);
    }
}