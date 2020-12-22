
import org.junit.Test;

import xyz.hcworld.gotool.random.Random;
import xyz.hcworld.gotool.security.AesRsaMixedUtils;
import xyz.hcworld.gotool.security.AesUtils;
import xyz.hcworld.gotool.security.RsaUtils;
import xyz.hcworld.gotool.security.entity.DecryptInfo;
import xyz.hcworld.gotool.security.entity.EncryptedInfo;

import java.util.Arrays;
import java.util.Base64;
import java.util.Map;


/**
 * @ClassName: MyTest
 * @Author: 张红尘
 * @Date: 2020/12/9 11:00
 * @Version： 1.0
 */
public class MyTest {

    @Test
    public void go() {


        //前端请求RSA公钥
        //后端生成rsa公钥密钥
        Map<String, String> publicAndPrivateKey = null;
        try {
            System.out.println("--------------生成RSA公私钥-----------------------");
            publicAndPrivateKey = RsaUtils.genKeyPair();
            System.out.println("publicKey:" + publicAndPrivateKey.get("publicKey"));
            System.out.println("privateKey:" + publicAndPrivateKey.get("privateKey"));
            System.out.println("--------------------------------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //测试rsa加解密
            //AES的密钥key 1234567812345678
            byte[] keyBytes = {0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38};
            //测试RSA的加密
            //公钥加密
            byte[] ciphertext = RsaUtils.encryptByPublicKey(keyBytes, publicAndPrivateKey.get("publicKey"));
            //私钥解密
            byte[] plaintext = RsaUtils.decryptByPrivateKey(ciphertext, publicAndPrivateKey.get("privateKey"));
            System.out.println("--------------测试RSA的加密-----------------------");
            System.out.println("公钥加密AES的密钥前：" + new String(keyBytes));
            System.out.println("公钥加密AES的密钥后：" + Base64.getEncoder().encodeToString(ciphertext));
            System.out.println("私钥解密AES的密钥后：" + new String(plaintext));
            System.out.println("--------------------------------------------------\n");
        } catch (Exception e) {

        }


        EncryptedInfo encryptedInfo = null;
        //模拟前端加密
        try {
            //自定义密钥与向量
            byte[] keyBytes = Random.number(16).getBytes("UTF-8");
//          byte[] iv = Random.number(16).getBytes("UTF-8");
            System.out.println("-----------------前端加密-----------------------");
            //AES要加密的数据
            String content = "RSA加解密安全性高但长文本处理性能不及AES。AES加解密长文本性能优于RSA但需要明文密钥和向量加解密，密钥的安全性成问题。";
//          encryptedInfo = AesRsaMixedUtils.encode(publicAndPrivateKey.get("publicKey"), content, keyBytes, iv);
//          encryptedInfo = AesRsaMixedUtils.encode(publicAndPrivateKey.get("publicKey"), content,keyBytes);
            encryptedInfo = AesRsaMixedUtils.encode(publicAndPrivateKey.get("publicKey"), content);
            System.out.println(encryptedInfo.getEncryptedData());
            System.out.println(encryptedInfo.getEncryptedKey());
            System.out.println(encryptedInfo.getEncryptedIv());
            System.out.println("-----------------前端加密-----------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String enData = new String();
        byte[] keyBytesF = new byte[0];
        byte[] ivF = new byte[0];
        // 后端解密
        try {
            System.out.println("-----------------后端解密-----------------------");
            DecryptInfo decryptInfo = AesRsaMixedUtils.decode(encryptedInfo, publicAndPrivateKey.get("privateKey"));
            System.out.println(decryptInfo.getData());
            keyBytesF = decryptInfo.getKey();
            ivF = decryptInfo.getIv();
            System.out.println(new String(decryptInfo.getKey()));
            System.out.println(new String(decryptInfo.getIv()));
            System.out.println("-----------------后端加密-----------------------\n");
            enData = AesUtils.encryptStr("啊!@#$%^*()+￥、。，“”；；‘；加密后的数据".getBytes(), decryptInfo.getKey(), decryptInfo.getIv());
            System.out.println(enData);
            System.out.println("-----------------后端加密-----------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("-----------------前端解密-----------------------");
            //模拟前端请求前就缓存的AES密钥以及向量
            byte[] keyByst = keyBytesF;
            byte[] iv = ivF;
            byte[] data = AesUtils.decrypt(Base64.getDecoder().decode(enData), keyByst, iv);
            System.out.println(new String(data));
            System.out.println("-----------------前端解密-----------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
