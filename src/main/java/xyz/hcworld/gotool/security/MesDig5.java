package xyz.hcworld.gotool.security;

import java.security.MessageDigest;

/** 
* @classDesc: 功能描述：(32位md5编码生成工具)
* @author : 张红尘
* @date 创建时间：2019年11月28日 下午4:32:36 
* @version 1.0 
*/
public class MesDig5 {
    private static final String[] HEX_DIG_ITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * MD5加密
     * @param origin 字符
     * @param code 编码
     * @return 小写md5字符串
     */
    public static String md5Encode(String origin, String code){
        String resultString = null;
        try{
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(null == code || "".equals(code)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(code)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     *  MD5加密
     * @param origin 要加密的字符串
     * @return 小写md5字符串
     */
    public static String md5Encode(String origin){
       return md5Encode(origin,null);
    }
    /**
     *  MD5加密
     * @param origin 要加密的字符串
     * @return 大写md5字符串
     */
    public static String md5EncodeCapitalize(String origin){
        return md5Encode(origin,null).toUpperCase();
    }
    /**
     *  MD5加密
     * @param origin 要加密的字符串
     * @param code 编码
     * @return 大写md5字符串
     */
    public static String md5EncodeCapitalize(String origin, String code){
        return md5Encode(origin,code).toUpperCase();
    }

    private static String byteArrayToHexString(byte b[]){
        StringBuilder resultSb = new StringBuilder();
        for(int i = 0,j=b.length; i <j ; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIG_ITS[d1] + HEX_DIG_ITS[d2];
    }

}
