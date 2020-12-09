import org.junit.Test;
import xyz.hcworld.gotool.date.DateTimeUtils;
import xyz.hcworld.gotool.net.HttpRequest;
import xyz.hcworld.gotool.net.Ping;
import xyz.hcworld.gotool.random.Random;
import xyz.hcworld.gotool.security.MesDig5;

import static xyz.hcworld.gotool.security.MesDig5.md5Encode;
import static xyz.hcworld.gotool.security.MesDig5.md5EncodeCapitalize;

/**
 * @ClassName: MyTest
 * @Author: 张红尘
 * @Date: 2020/12/9 11:00
 * @Version： 1.0
 */
public class MyTest {

    @Test
    public void go() {

        System.out.println(HttpRequest.sendPost("http://localhost:8080/login","username=123","password=123"));
        System.out.println(HttpRequest.sendGet("https://baidu.com"));

        System.out.println(Ping.ping());
        System.out.println(Ping.ping("baidu.com"));
        System.out.println(Ping.ping(3000));
        System.out.println(Ping.ping("baidu.com",3000));




    }

}
