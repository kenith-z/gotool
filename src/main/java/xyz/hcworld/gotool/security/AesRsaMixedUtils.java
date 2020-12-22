package xyz.hcworld.gotool.security;

import xyz.hcworld.gotool.random.Random;
import xyz.hcworld.gotool.security.entity.DecryptInfo;
import xyz.hcworld.gotool.security.entity.EncryptedInfo;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * RSA+AES混合加解密
 * 使用RSA私钥解密AES密钥，再使用AES密钥和向量解密数据
 * AES使用密钥和向量加密数据，再使用RSA公钥加密AES密钥
 *
 * @ClassName: AesRsaMixedUtils
 * @Author: 张红尘
 * @Date: 2020/12/11 17:17
 * @Version： 1.0
 */
public class AesRsaMixedUtils {
    /**
     * RSA+AES混合解密
     * 1.使用RSA私钥解密AES密钥<br/>
     * 2.使用AES密钥和偏置向量解密内容<br/>
     *
     * @param encryptedInfo AES数据封装类
     * @param privateKey    RSA私钥
     * @return 解密数据
     */
    public static DecryptInfo decode(EncryptedInfo encryptedInfo, String privateKey) {
        byte[] cipherText = Base64.getDecoder().decode(encryptedInfo.getEncryptedKey());
        byte[] iv = Base64.getDecoder().decode(encryptedInfo.getEncryptedIv());
        byte[] date = Base64.getDecoder().decode(encryptedInfo.getEncryptedData());
        //RSA私钥解密出AES密钥
        byte[] aesKeyBytes = RsaUtils.decryptByPrivateKey(cipherText, privateKey);
        //解密出加密后的data
        byte[] dec = AesUtils.decrypt(date, aesKeyBytes, iv);
        return new DecryptInfo(new String(dec), aesKeyBytes, iv);
    }

    /**
     * RSA+AES混合加密（自定义密钥+自定义向量）
     * 1.使用AES密钥和偏置向量加密内容<br/>
     * 2.使用RSA公钥加密AES密钥<br/>
     *
     * @param content   需要加密的明文
     * @param publicKey RSA公钥
     * @param keyBytes  AES密钥
     * @param iv        偏移向量
     * @return Base64编码加密数据
     */
    public static EncryptedInfo encode(String publicKey, String content, byte[] keyBytes, byte[] iv) {
        //RSA公钥加密Aes的密钥
        byte[] cipherText = RsaUtils.encryptByPublicKey(keyBytes, publicKey);
        //aes加密，内容，密钥，向量
        byte[] aesDataByte = AesUtils.encrypt(content.getBytes(), keyBytes, iv);
        //将数据Aes加密后的数据和公钥加密后的AES密钥，向量iv以base64的形势发送给后端
        String data = Base64.getEncoder().encodeToString(aesDataByte);
        String key = Base64.getEncoder().encodeToString(cipherText);
        String ivs = Base64.getEncoder().encodeToString(iv);
        //以base64编码传给出
        return new EncryptedInfo(data, key, ivs);
    }

    /**
     * RSA+AES混合加密（自定义密钥+随机向量）
     * 1.使用AES密钥和偏置向量加密内容<br/>
     * 2.使用RSA公钥加密AES密钥<br/>
     *
     * @param content   需要加密的明文
     * @param publicKey RSA公钥
     * @param keyBytes  AES密钥
     * @return Base64编码加密数据
     */
    public static EncryptedInfo encode(String publicKey, String content, byte[] keyBytes) {
        String ivStr = Random.number(16);
        byte[] iv;
        try {
            iv = ivStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            iv = new byte[]{0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38};
        }
        return encode(publicKey, content, keyBytes, iv);
    }

    /**
     * RSA+AES混合加密（随机密钥+随机向量）
     * 1.使用AES密钥和偏置向量加密内容<br/>
     * 2.使用RSA公钥加密AES密钥<br/>
     *
     * @param content   需要加密的明文
     * @param publicKey RSA公钥
     * @return Base64编码加密数据
     */
    public static EncryptedInfo encode(String publicKey, String content) {
        byte[] iv, keyBytes;
        try {
            iv = Random.number(16).getBytes("UTF-8");
            keyBytes = Random.number(16).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            iv = new byte[]{0x30, 0x31, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36, 0x30, 0x37, 0x30, 0x38};
            keyBytes = new byte[]{0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38};
        }
        return encode(publicKey, content, keyBytes, iv);
    }


}
