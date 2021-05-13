package com.dns.demo;

public class TestDnsResolve {
    static native void resolveDns(String hostName, DnsCallback callBack);

    public static void main(String []args) {
        System.out.println (System.getProperty ("java.library.path"));
        System.loadLibrary("event_core");
        System.loadLibrary("event_extra");
        System.loadLibrary("lib-dns");
        resolveDns("www.baidu.com", (res) -> {
            if(res.getErrCode() == 0){
                if(res.getIps()!=null){
                    for(String item : res.getIps()){
                        System.out.println("callback result: " + item);
                    }
                }
            }

        });
    }

}


