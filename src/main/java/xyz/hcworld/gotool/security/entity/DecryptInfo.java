package xyz.hcworld.gotool.security.entity;

/**
 * 解密数据
 * @ClassName: Decryptinfo
 * @Author: 张红尘
 * @Date: 2020/12/14 16:31
 * @Version： 1.0
 */
public class DecryptInfo {

    private String data;
    private byte[] key;
    private byte[] iv;

    public DecryptInfo(){}

    public DecryptInfo(String data,byte[] key,byte[] iv){
        this.data = data;
        this.key = key;
        this.iv = iv;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }
}
