package com.lynch.test;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Description jna-func
 * Created by troub on 2021/5/11 14:29
 */
public class MsgCallbackImpl implements IMsgCallback {
    @Override
    public void invoke(int count, PointerByReference reference) {
//        System.out.println("C++回调Java测试，来自C++的内容：" + strArg);
        final Pointer pointer = reference.getPointer();

        final Pointer[] pointerArray = pointer.getPointerArray(0);
        List<String> ips = new ArrayList<>();
        int index = 1;
        for (Pointer item : pointerArray) {
            String result = item.getString(0, "utf-8");
            ips.add(result);
            System.out.println(result);
            index++;

            if (index > count) {
                break;
            }
        }

        System.out.println("well done");
    }
}