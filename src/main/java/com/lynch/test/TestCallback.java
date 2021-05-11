package com.lynch.test;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Description jna-func
 * Created by troub on 2021/5/11 14:31
 */
public class TestCallback {
    public static void main(String[] args) {

        //  调用Java接口中的方法，执行C++中的方法体
//        jnaDemo.test1();
//
//        //  向C++类库中传递字符串参数，并得到字符串返回值
//        String cpprt = jnaDemo.test2("FROM Java");
//
//        System.out.println("来自C++动态类库：" + cpprt);
        int max = CallbackLibrary.INSTANCE.max(100, 200);
        // out: 200
        System.out.println(max);
        //  注册消息回调接口
        CallbackLibrary.INSTANCE.regisMsgCallback(new MsgCallbackImpl());

        //  测试回调接口中的回调函数
        CallbackLibrary.INSTANCE.testCallback();
    }

    public interface CallbackLibrary extends Library {

        // JNA 为 dll 名称
        CallbackLibrary INSTANCE = Native.load("JNA", CallbackLibrary.class);

        void regisMsgCallback(IMsgCallback callback);

        void testCallback();

        int max(int a, int b);
    }
}
