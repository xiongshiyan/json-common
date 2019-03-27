package top.jfunc.json.kit;

/**
 * @author xiongshiyan at 2019/3/27 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class StrKit {
    /***
     * 判断一个字符串是否是数字
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
