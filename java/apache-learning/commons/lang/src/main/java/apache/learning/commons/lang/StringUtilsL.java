package apache.learning.commons.lang;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringUtilsL {
    public static void main(String[] args) {
        splitTest();
    }

    public static void splitTest() {
        String[] split = StringUtils.split("/web/http/servlet", '/');
        System.out.println(Arrays.toString(split));
        System.out.println(Arrays.toString("/web/http/servlet".split("/")));
    }
}
