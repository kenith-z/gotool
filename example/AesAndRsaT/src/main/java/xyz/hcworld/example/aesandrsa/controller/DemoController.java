package xyz.hcworld.example.aesandrsa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hcworld.gotool.security.AesRsaMixedUtils;
import xyz.hcworld.gotool.security.AesUtils;
import xyz.hcworld.gotool.security.RsaUtils;
import xyz.hcworld.gotool.security.entity.DecryptInfo;
import xyz.hcworld.gotool.security.entity.EncryptedInfo;

import java.util.Map;

/**
 *
 * @ClassName: DemoController
 * @Author: 张红尘
 * @Date: 2020-12-20
 * @Version： 1.0
 */
@RestController
public class DemoController {

    /**
     * Rsa公私钥
     */
    Map<String, String> publicAndPrivateKey = null;

    /**
     * 获取public
     * @return
     */
    @GetMapping("/getPublicKey")
    public String end(){
        publicAndPrivateKey = RsaUtils.genKeyPair();
        return publicAndPrivateKey.get("publicKey");
    }

    @PostMapping("/post")
    public String end(@RequestBody EncryptedInfo info){
        System.out.println(info.getEncryptedData());
        System.out.println(info.getEncryptedKey());
        System.out.println(info.getEncryptedIv());
        System.out.println("-----------------后端解密-----------------------");
        DecryptInfo decryptInfo = AesRsaMixedUtils.decode(info, publicAndPrivateKey.get("privateKey"));
        //获取到的内容如
        System.out.println(decryptInfo.getData());
        //秘钥
        System.out.println(new String(decryptInfo.getKey()));
        //向量
        System.out.println(new String(decryptInfo.getIv()));
        System.out.println("-----------------后端加密-----------------------\n");
        return AesUtils.encryptStr("啊!@#$%^*()+￥、。，“”；；‘；加密后的数据".getBytes(), decryptInfo.getKey(), decryptInfo.getIv());
    }



}
