package cn.ismartv.sakura.utils;

public class StringUtilities {
    private static final String NORTH_CHINA = "北京市, 天津市, 河北省, 山西省, 内蒙古自治区";
    private static final String EAST_CHINA = "上海市,江苏省,安徽省,浙江省,福建省,江西省,山东省";
    private static final String SOUTH_CHINA = "广东省,广西壮族自治区,海南省,香港特区,澳门特区";
    private static final String CENTRAL_CHINA = "河南省,湖北省,湖南省";


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