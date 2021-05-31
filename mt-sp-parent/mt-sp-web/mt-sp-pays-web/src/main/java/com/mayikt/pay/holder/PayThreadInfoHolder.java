package com.mayikt.pay.holder;

import java.util.Map;

public class PayThreadInfoHolder  {
    private  static ThreadLocal<Map<String, String>> threadLocal=new ThreadLocal<>();
    public static void add(Map<String, String> params){
        threadLocal.set(params);
    }
    public static void remove(){
        threadLocal.remove();
    }
}
