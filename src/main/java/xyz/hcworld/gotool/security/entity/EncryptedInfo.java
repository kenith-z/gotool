package xyz.hcworld.gotool.security.entity;


/**
 * 加密数据类
 * @ClassName: EncryptedInfo
 * @Author: 张红尘
 * @Date: 2019/12/7 15:12
 * @Version： 1.0
 */

public class EncryptedInfo {

    private String encryptedData;
    private String encryptedKey;
    private String encryptedIv;

    public EncryptedInfo() {}

    /**
     *
     * @param encryptedData AES加密数据
     * @param encryptedKey RSA加密后的AES秘钥
     * @param encryptedIv AES偏移向量
     */
    public EncryptedInfo(String encryptedData, String encryptedKey, String encryptedIv) {
        this.encryptedData = encryptedData;
        this.encryptedKey = encryptedKey;
        this.encryptedIv = encryptedIv;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public String getEncryptedIv() {
        return encryptedIv;
    }

    public void setEncryptedIv(String encryptedIv) {
        this.encryptedIv = encryptedIv;
    }
}
