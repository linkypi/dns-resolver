package com.lynch.test;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.util.List;

/**
 * Description jna-func
 * Created by troub on 2021/5/11 14:28
 */
public interface IMsgCallback extends Callback {
    /**
     * C++回调Java代码时会调用到的方法
     * @param reference
     */
    void invoke(int count, PointerByReference reference);
}