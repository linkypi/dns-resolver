package com.dns.demo;


import java.util.List;

public class CallbackResponse {
    private int errCode;
    private int count;
    private int ttl;
    private String originHost;

    public CallbackResponse(){}
    public CallbackResponse(int code, int count , int ttl, String origin, List<String> ips){
        this.errCode = code;
        this.ips = ips;
        this.count = count;
        this.ttl = ttl;
        this.originHost = origin;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getOriginHost() {
        return originHost;
    }

    public void setOriginHost(String originHost) {
        this.originHost = originHost;
    }


    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    private List<String> ips;
}
