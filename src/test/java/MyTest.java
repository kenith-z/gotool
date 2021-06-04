
import org.junit.Test;

import xyz.hcworld.gotool.random.Random;
import xyz.hcworld.gotool.security.AesRsaMixedUtils;
import xyz.hcworld.gotool.security.AesUtils;
import xyz.hcworld.gotool.security.RsaUtils;
import xyz.hcworld.gotool.security.entity.DecryptInfo;
import xyz.hcworld.gotool.security.entity.EncryptedInfo;
import xyz.hcworld.gotool.sort.FullPermutation;

import java.util.*;


/**
 * @ClassName: MyTest
 * @Author: 张红尘
 * @Date: 2020/12/9 11:00
 * @Version： 1.0
 */
public class MyTest {

    @Test
    public void go() {

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

    }

}
