package com.lynch.test.util;

/**
 * Description jna-func
 * Created by troub on 2021/5/11 17:21
 */
public class OSinfo {
    private static String OS = System.getProperty("os.name").toLowerCase();

    private static OSinfo _instance = new OSinfo();

    private Platform platform;

    private OSinfo(){}

    public static boolean isLinux(){
        return OS.indexOf("linux")>=0;
    }

    public static boolean isMacOS(){
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")<0;
    }

    public static boolean isMacOSX(){
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0;
    }

    public static boolean isWindows(){
        return OS.indexOf("windows")>=0;
    }

    public static boolean isOS2(){
        return OS.indexOf("os/2")>=0;
    }

    public static boolean isSolaris(){
        return OS.indexOf("solaris")>=0;
    }

    public static boolean isSunOS(){
        return OS.indexOf("sunos")>=0;
    }

    public static boolean isMPEiX(){
        return OS.indexOf("mpe/ix")>=0;
    }

    public static boolean isHPUX(){
        return OS.indexOf("hp-ux")>=0;
    }

    public static boolean isAix(){
        return OS.contains("aix");
    }

    public static boolean isOS390(){
        return OS.indexOf("os/390")>=0;
    }

    public static boolean isFreeBSD(){
        return OS.indexOf("freebsd")>=0;
    }

    public static boolean isIrix(){
        return OS.indexOf("irix")>=0;
    }

    public static boolean isDigitalUnix(){
        return OS.indexOf("digital")>=0&&OS.indexOf("unix")>0;
    }

    public static boolean isNetWare(){
        return OS.indexOf("netware")>=0;
    }

    public static boolean isOSF1(){
        return OS.indexOf("osf1")>=0;
    }

    public static boolean isOpenVMS(){
        return OS.indexOf("openvms")>=0;
    }

    /**
     * 获取操作系统名字
     * @return 操作系统名
     */
    public static Platform getOSName(){
        if(isAix()){
            _instance.platform = Platform.AIX;
        }else if (isDigitalUnix()) {
            _instance.platform = Platform.Digital_Unix;
        }else if (isFreeBSD()) {
            _instance.platform = Platform.FreeBSD;
        }else if (isHPUX()) {
            _instance.platform = Platform.HP_UX;
        }else if (isIrix()) {
            _instance.platform = Platform.Irix;
        }else if (isLinux()) {
            _instance.platform = Platform.Linux;
        }else if (isMacOS()) {
            _instance.platform = Platform.Mac_OS;
        }else if (isMacOSX()) {
            _instance.platform = Platform.Mac_OS_X;
        }else if (isMPEiX()) {
            _instance.platform = Platform.MPEiX;
        }else if (isNetWare()) {
            _instance.platform = Platform.NetWare_411;
        }else if (isOpenVMS()) {
            _instance.platform = Platform.OpenVMS;
        }else if (isOS2()) {
            _instance.platform = Platform.OS2;
        }else if (isOS390()) {
            _instance.platform = Platform.OS390;
        }else if (isOSF1()) {
            _instance.platform = Platform.OSF1;
        }else if (isSolaris()) {
            _instance.platform = Platform.Solaris;
        }else if (isSunOS()) {
            _instance.platform = Platform.SunOS;
        }else if (isWindows()) {
            _instance.platform = Platform.Windows;
        }else{
            _instance.platform = Platform.Others;
        }
        return _instance.platform;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(OSinfo.getOSName());// 获取系统类型
        System.out.println(OSinfo.isWindows());// 判断是否为windows系统
    }
}
