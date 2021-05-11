package com.lynch.test.dns;

import com.lynch.test.util.OSinfo;
import com.sun.jna.*;
import com.sun.jna.ptr.PointerByReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Description jna-func
 * Created by troub on 2021/5/11 12:30
 */
//@Slf4j
public class DnsTest {
    public interface DnsLibrary extends Library {
        // JNA 为 dll 名称
        DnsTest.DnsLibrary INSTANCE = Native.load("lib-dns", DnsTest.DnsLibrary.class);

        void resolve(String hostName, DnsCallback callBack);

        interface DnsCallback extends Callback {
            void invoke(int errCode, int type, int count, int ttl, String originHost, PointerByReference arrReference);
        }

    }

    public static void main(String[] args) {
        if(OSinfo.isWindows()) {
            // 加载 libevent
            System.loadLibrary("event_core");
            System.loadLibrary("event_extra");
        }
        if(OSinfo.isLinux()){

        }

        // 对于数组类型只能通过 xxxByReference 获取
        DnsLibrary.INSTANCE.resolve("www.baidu.com", //new IDnsCallbackImpl());
              (int errCode, int type, int count,int ttl, String originHost,PointerByReference arrReference)->{
           System.out.println("errcode: "+ errCode);
           if(errCode!=0){
               System.out.println("resolve dns take error: "+ originHost);
               return ;
           }
            final Pointer pointer = arrReference.getPointer();
            final Pointer[] pointerArray = pointer.getPointerArray(0);
            List<String> ips = new ArrayList<>();
            int index = 1;
            for (Pointer item : pointerArray) {
                String result = item.getString(0, "utf-8");
                ips.add(result);
                index++;

                if (index > count) {
                    break;
                }
            }
            ips.forEach(System.out::println);
        });
    }
}
