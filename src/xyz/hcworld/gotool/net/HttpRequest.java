package xyz.hcworld.gotool.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;


/**
 * 发送Get或Post请求
 * @ClassName: HttpRequest
 * @Author: 张红尘
 * @Date: 2020/12/7 18:12
 * @Version： 1.0
 */
public class HttpRequest {

    public static final String GET = "get";
    public static final String POST = "post";
    private static final String PATTERN = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url    发送请求的URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String... params) {
        return sendHttp(GET, url, params);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url    发送请求的 URL
     * @param params 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String... params) {
        return sendHttp(POST, url, params);
    }


    /**
     * 向指定URL
     *
     * @param url    发送请求的URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private static String sendHttp(String type, String url, String... params) {
        if (url == null || Pattern.matches(PATTERN, url)) {
            return "Illegal url";
        }
        String param = paramsStr(GET.equals(type), params);
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(GET.equals(type) ? url + param : url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
            if (POST.equals(type)) {
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            } else {
                // 建立实际的连接
                conn.connect();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.err.println("发送Http请求出现异常！\n" + e);
        }
        // 关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.err.println("io结束异常\n" + ex);
            }
        }
        return result.toString();
    }

    private static String paramsStr(boolean get, String... params) {
//        String[] strs = params;
        StringBuilder sBu = new StringBuilder(get ? "?" : "");
        if (params.length > 1) {
            for (String string : params) {
                sBu.append(string + '&');
            }
            sBu.deleteCharAt(sBu.length() - 1);
        } else if (params.length == 1) {
            sBu.append(params[0]);
        }
        return sBu.toString();
    }


}