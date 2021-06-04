# gotool

[![jdk版本](https://img.shields.io/badge/java-1.8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![许可证](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/z875479694h/gotool/blob/master/LICENSE)
[![博客](https://img.shields.io/badge/blog-Kenith–Zhang-blueviolet.svg)](https://hcworld.xyz)
[![Github stars](https://img.shields.io/github/stars/z875479694h/gotool.svg)](https://github.com/z875479694h/gotool)

## use

### quote

 ```xml 
    <dependency>
      <groupId>xyz.hcworld.gotool</groupId>
      <artifactId>gotool</artifactId>
      <version>1.3.0</version>
    </dependency>
    <dependency>
      <groupId>xyz.hcworld.gotool</groupId>
      <artifactId>gotool</artifactId>
      <version>1.3.0</version>
      <classifier>javadoc</classifier>
    </dependency>
```

### usage method

```java 
    // HttpRequest http请求
    System.out.println(HttpRequest.sendPost("http://localhost:8080/login","username=123","password=123"));
    System.out.println(HttpRequest.sendGet("https://baidu.com"));
    /* 结果
       response数据
       <!DOCTYPE html><!--STATUS OK-->    <html><head>...</html>
    */
    
    System.out.println(Ping.ping());
    System.out.println(Ping.ping("baidu.com"));
    System.out.println(Ping.ping(3000));
    System.out.println(Ping.ping("baidu.com",3000));
    /* 结果
       true
       true
       true
       true
     */  
    
    // DateTimeUtils 获取当前时间的格式化，
    //可不设置，默认东八区
    DateTimeUtils.setZone("+8");
    System.out.println(DateTimeUtils.getCurrentDateTimeStr());
    System.out.println(DateTimeUtils.getCurrentDateStr());
    System.out.println(DateTimeUtils.getCurrentTimeStr());
    System.out.println(DateTimeUtils.getCurrentTimeSssStr());
    /* 结果
       2020-12-09 12:59:27
       2020-12-09
       12:59:27
       12:59:27:347
    */
    
    //Random  随机字符串生成
    Random.lowercase(16);
    Random.capitals(16);
    Random.number(16);
    Random.symbol(16);
    Random.lettersAndNum(16);
    Random.lettersAndNumAndSym(16);
    Random.lettersAndNumAndSym(16,".%");
    /* 结果
       zlwjqxczsawxlcxi
       LVRWCHHNZORVAEUA
       6945399191398076
       +!-,:.{"=)}*):<{
       nkvmqhibxZyeQiNJ
       25{gg{i/.2Cl?/JA
       q.SfYpIvi%FZPFU0
    */
    
    //MD5摘要算法
    String ran= Random.lettersAndNumAndSym(16);
    System.out.println(ran);
    System.out.println(MesDig5.md5Encode(ran)+"\t"+md5Encode(ran));
    System.out.println(md5Encode(ran,"UTF-8"));
    System.out.println(md5EncodeCapitalize(ran));
    System.out.println(md5EncodeCapitalize(ran,"UTF-8"));
    /* 结果
       fWd;t]C=&Tj'~.8h
       a5dceaa6ad1e2338f694fbf9cdf3e921	a5dceaa6ad1e2338f694fbf9cdf3e921
       a5dceaa6ad1e2338f694fbf9cdf3e921
       A5DCEAA6AD1E2338F694FBF9CDF3E921
       A5DCEAA6AD1E2338F694FBF9CDF3E921
    */
```

#### RSA and AES

1.details see example of AesAndRsaT demo

```java 
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
```

#### FullPermutation

```java 
        //对元素进行全排列
        List<Character> c= new ArrayList<>();
        c.add('a');
        c.add('b');
        c.add('c');
        FullPermutation<Character> go =new FullPermutation<>();
        go.fullPermutation(c, 0);
        System.out.println(go.result().toString());

        List<Integer> i= new ArrayList<>();
        i.add(1);
        i.add(2);
        i.add(3);
        FullPermutation<Integer> go1 =new FullPermutation<>(i);
        System.out.println(go1.result().toString());
        /* 结果
             [[a, b, c], [a, c, b], [b, a, c], [b, c, a], [c, b, a], [c, a, b]]
             [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
        */
```