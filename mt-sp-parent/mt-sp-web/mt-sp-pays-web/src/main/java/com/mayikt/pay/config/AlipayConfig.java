package com.mayikt.pay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092100564758";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCRhXHYKxwdEqrT4v1IeMsXqd2fc8uHfw33aSmvNxUa9V0C3NnGzrvBM6uuG6/av6z4BqoeSFSGt6kXg033M+r3CtVM03+BxpXg14XnN7/MNItiwhV4XqQiQAZUZNqXp8rvhbQ3ZfbNaghBXfNIsZByHpBS/v4Px1kqQfRbNipKE+5ESxMH4oiRtJTj+jjzOROg2tD+rY0iT/nszZkTL8+f2rM7NVo8ipiII/tCfv1s8SooG06FhCp8qLOsj9ZRiOAmHbjCwnF0J3t+h7B7ScoAc7MHSmMhfHLa2rutpdzUpnEe/QiwAlcR0tkMChIhwTVbrJYxwzVO1ek8k56hoRNnAgMBAAECggEAYjJqYDS3A+dU3yaNRBo+8fjuVGsPLJ/QDOQp/NJuq4JnAUeGrWFT0UvMzfBg+nvdfIECyuyEHWRR9kvkkKu4Inxs8yrFIA2hldfZw+0av/G1HS9azZ8Pow0ROGHWIABaMMdIlRl5MaQbbgC36pXnoxp8yYTIbF1ZDerzuZkTcGh95DUVj9vr9qYTsgDEuCjepZovMF2TJZ+7jdibkiwf21pnAoxRsQ+CwU5VaUcJBsUPR0cLJzStbpUh8hdtpNmbZMWrAjRceH0haDgyBO/cwVL1mKr4SImt4gwDWLYNyTJ9R9gyggUuQar3Jy4y9gyvk/NyZKafgaWNjE6vYU+YAQKBgQDJi1sXWxQDHwnjYZt9MXGkXbLEUX1SIgxrvErSE9egGo3Fh9WcBXH6GHro2QyZ08y07xQmniGY7MUCtl7UIRKXWiUdjzMyzRZWEb205H6yPmLYTrgHyG8g3adq8mfagIyy1dSsp/ntcYB7qDW5NpvwdblZAZLYKr5/0Kqr5t0XFwKBgQC41wWBwmDiVI28e/QW73RQzKUmjIMydibWKzUbltND2osnsrTV8OI6riwW5/xJNuJioKHop6nziNXjTghxWqeOdLeua7DH1xSzeN3WL/mT9CyxWG3zr7uAC681eVXeBqtNsYIMOhhvJM6EL2A8jWqze2ubhUEC4b8mcmpFEXaYMQKBgDC1lGNVqMWEV+Bzc5/TNlLyLkVSKIaUIgpbrH2PPBq+sCrgRFj72+sExZfG9UusK5pC+czHhPkvqAGaz9pWz+Zya+vz8vzBIjfU+9NfMdukRkFe7Q08fNclmtO3GDBvxxFlqou74SRovROluK41q4R2/z+qqLHDytybMtRu7ForAoGAKYCGCQOW81qe1HJUdQ3g5P9+GXC1SJBE2hOrgf1UhEi+s/QpLFxAv4I5/g6qk9nR7Ok6UV3MCBVySO5NcP403wSP9YbSk5xUVpPN43i0JidOHafIUE3l4LM+7NfIkuiWcARqOcqVj+cIw59LkW0DRzdwCuRZm4cKf1xzRfzMzxECgYBOmpaJ8E1LdUXadakwinUx+TQeCfntLAnyhtzMx+RTPMUO1Z3HSdRDVKaSXkfcLwRQwojwZq6mPtF7UYxmEPQJy1sBONlNcnfJwKL7qIxLikjIHSAVrwfmAHw7nNj90/N+479EaC/qEoIQLdE3FgvjKimFwznrY5Qb3GhtBa5ixg==";
    //对应APPID下的支付宝公钥。
    /**
     * 支付宝将用户支付的结果内容 加密后 发送给商户端 商户端需要通过该支付宝公钥对数据解密
     */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1U7j3Bl5QtyF6ZGouhkUFivy4lA10SQlZFP4CBY9uigwMDljVnhhQ/8yfRCHokJ6ZYaHfRivNWxm0zjtmKCNILN8HNnPtEx7lSChPSLkbwAvCtwhgPgtQFXYDlRvRACDfLw9uetUZXP7z0sd1RH8X7LVJ0d/+KN3ODlPLCcjCWXR08uHW7ys4Yrtv2AePnntvSFNNUtGOLG3mbv+sWW14I/++10KwejhnkBaMiBkx8FLcOWvT4bjArLH8TynT4Gd66GJjbCXY1jHELvKuLguBRWL6Ya22iN1udq99GQY6l8Z5trtQhOoCCj0MiAp3V8DRlwVTvg9Hn2raTENpCiJNQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://mayiktyushengjun.natapp1.cc/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://mayiktyushengjun.natapp1.cc/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

