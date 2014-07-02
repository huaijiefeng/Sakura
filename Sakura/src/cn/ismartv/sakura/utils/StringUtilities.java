package cn.ismartv.sakura.utils;

public class StringUtilities {
    /**
     * string is empty
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

}