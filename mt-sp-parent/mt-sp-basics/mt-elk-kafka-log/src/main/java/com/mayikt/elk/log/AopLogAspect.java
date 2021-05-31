package com.mayikt.elk.log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 作者：余胜军
 * elk+kafka采集
 */
@Aspect
@Component
public class AopLogAspect {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private LogContainer logContainer;
    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(* com.mayikt.api.impl.*.*.*(..))")
    private void serviceAspect() {
    }



    //
    // 请求method前打印内容
    @Before(value = "serviceAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        jsonObject.put("request_time", df.format(new Date()));
        jsonObject.put("request_url", request.getRequestURL().toString());
        jsonObject.put("request_method", request.getMethod());
        jsonObject.put("signature", joinPoint.getSignature());
        jsonObject.put("request_args", Arrays.toString(joinPoint.getArgs()));
        // IP地址信息
        jsonObject.put("ip_addres", getIpAddr(request) + ":" + serverPort);
        JSONObject requestJsonObject = new JSONObject();
        requestJsonObject.put("request", jsonObject);
        jsonObject.put("request_time", df.format(new Date()));
        jsonObject.put("log_type", "info");
        // 将日志信息投递到kafka中
        String log = requestJsonObject.toJSONString();
        logContainer.putLog(log);

    }

    //
    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "serviceAspect()")
    public void methodAfterReturing(Object o) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        JSONObject respJSONObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        jsonObject.put("response_time", df.format(new Date()));
        jsonObject.put("response_content", JSONObject.toJSONString(o));
        // IP地址信息
        jsonObject.put("ip_addres", getIpAddr(request) + ":" + serverPort);
        jsonObject.put("log_type", "info");
        respJSONObject.put("response", jsonObject);
        // 将日志信息投递到kafka中
//      kafkaTemplate.send("mayikt-log",respJSONObject.toJSONString());
//        logContainer.put(respJSONObject.toJSONString());
    }
//
//
    /**
     * 异常通知
     *
     * @param point
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void serviceAspect(JoinPoint point, Exception e) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        jsonObject.put("request_time", df.format(new Date()));
        jsonObject.put("request_url", request.getRequestURL().toString());
        jsonObject.put("request_method", request.getMethod());
        jsonObject.put("signature", point.getSignature());
        jsonObject.put("request_args", Arrays.toString(point.getArgs()));
        jsonObject.put("error", e.toString());
        // IP地址信息
        jsonObject.put("ip_addres", getIpAddr(request) + ":" + serverPort);
        jsonObject.put("log_type", "error");
        JSONObject requestJsonObject = new JSONObject();
        requestJsonObject.put("request", jsonObject);
        // 将日志信息投递到kafka中
        String log = requestJsonObject.toJSONString();
        logContainer.putLog(log);
    }
//
    public static String getIpAddr(HttpServletRequest request) {
        //X-Forwarded-For（XFF）是用来识别通过HTTP代理或负载均衡方式连接到Web服务器的客户端最原始的IP地址的HTTP请求头字段。
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}