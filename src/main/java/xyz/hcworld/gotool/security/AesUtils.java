package xyz.hcworld.gotool.security;

import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES128 算法<br/>
 * CBC 模式<br/>
 * PKCS5Padding 填充模式(PKCS5Padding，PKCS7Padding的子集，块大小固定为8字节。)<br/>
 * CBC模式需要添加一个参数iv<br/>
 * java 不支持PKCS7Padding，只支持PKCS5Padding PKCS7Padding 和 PKCS5Padding 没有什么区别<br/>
 * @ClassName: AesUtils
 * @Author: 张红尘
 * @Date: 2020/12/11 17:25
 * @Version： 1.0
 */
public class AesUtils {
    /**
     * 加密方式
     */
    private static String KEY_ALGORITHM = "AES";
    /**
     * 数据填充方式
     */
    private static String ALGORITHM_STR = "AES/CBC/PKCS5Padding";


    /**
     * AES加密
     *
     * @param originalContent 加密内容
     * @param encryptKey 秘钥
     * @param ivByte 偏移向量
     * @return 加密字节数组
     */
    public static byte[] encrypt(byte[] originalContent, byte[] encryptKey, byte[] ivByte) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(ivByte));
            return cipher.doFinal(originalContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES加密
     *
     * @param originalContent 加密内容
     * @param encryptKey 秘钥
     * @param ivByte 偏移向量
     * @return 加密Base64字符串
     */
    public static String encryptStr(byte[] originalContent, byte[] encryptKey, byte[] ivByte) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(ivByte));
            return  Base64.getEncoder().encodeToString(cipher.doFinal(originalContent));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES解密
     * 填充模式AES/CBC/PKCS7Padding
     * 解密模式128
     *
     * @param content 目标密文
     * @return
     * @throws Exception
     * @throws InvalidKeyException
     * @throws NoSuchProviderException
     */
    public static byte[] decrypt(byte[] content, byte[] aesKey, byte[] ivByte) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            Key sKeySpec = new SecretKeySpec(aesKey, KEY_ALGORITHM);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIv(ivByte));
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * AES解密
     * 填充模式AES/CBC/PKCS7Padding
     * 解密模式128
     *
     * @param content Base64加密后的数据
     * @param aesKey Base64加密后的AES秘钥
     * @param ivByte Base64加密后的AES向量
     * @return 解密后的字节数组
     */
    public static byte[] decrypt(String content, String aesKey, String ivByte) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            Key sKeySpec = new SecretKeySpec(Base64.getDecoder().decode(aesKey), KEY_ALGORITHM);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIv(Base64.getDecoder().decode(ivByte)));
            return cipher.doFinal(Base64.getDecoder().decode(content));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 生成iv
     */
    private static AlgorithmParameters generateIv(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        params.init(new IvParameterSpec(iv));
        return params;
    }
}