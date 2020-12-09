# gotool
[![jdk版本](https://img.shields.io/badge/java-1.8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![许可证](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/z875479694h/gotool/blob/master/LICENSE)
[![博客](https://img.shields.io/badge/blog-Kenith–Zhang-blueviolet.svg)](https://hcworld.xyz)
[![Github stars](https://img.shields.io/github/stars/z875479694h/gotool.svg)](https://github.com/z875479694h/gotool)

## use

### quote
 
    <dependency>
      <groupId>xyz.hcworld.gotool</groupId>
      <artifactId>gotool</artifactId>
      <version>1.1.0</version>
    </dependency>
    <dependency>
      <groupId>xyz.hcworld.gotool</groupId>
      <artifactId>gotool</artifactId>
      <version>1.1.0</version>
      <classifier>javadoc</classifier>
    </dependency>

### usage method

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
    
 
 

