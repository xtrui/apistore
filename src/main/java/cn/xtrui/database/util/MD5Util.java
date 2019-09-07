package cn.xtrui.database.util;

import org.springframework.util.DigestUtils;

public class MD5Util {
    public static String simpleMD5(String str)
    {
        str = DigestUtils.md5DigestAsHex(str.getBytes());
        return  str;
    }

    public static String doubleMD5(String str)
    {
        str = DigestUtils.md5DigestAsHex(str.getBytes());
        str = DigestUtils.md5DigestAsHex(str.getBytes());
        return str;
    }
}
