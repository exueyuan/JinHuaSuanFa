package example;


import java.util.Random;

import example.utils.PrintUtils;

/**
 * Created by exueyuan on 2018/4/11.
 */

public class GeTi {
    public static String MUBIAO = "test demo demo";
    public String geti;

    public GeTi() {
//        geti = getRandomString(MUBIAO.length());
//        geti = getRandomHanziString(MUBIAO.length());
    }

    public void init() {
        geti = getRandomString(MUBIAO.length());
    }

    public GeTi(String geti_) {
        geti = geti_;
    }

    public void print() {
        PrintUtils.print("字符串：" + geti + "  匹配度：" + piPeiDu() + "(最大" + (MUBIAO.length()) + ")");
    }

    public int piPeiDu() {
        int pipeiNum = 0;
        for (int i = 0; i < MUBIAO.length(); i++) {
            if (MUBIAO.charAt(i) == geti.charAt(i)) {
                pipeiNum++;
            }
        }
        return pipeiNum;
    }

    public String getString() {
        return "字符串：" + geti + "  匹配度:" + piPeiDu() + "(最大匹配度：" + (MUBIAO.length()) + ")\n";
    }


    public static String getRandomString(int length) {
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890 +-*/!.,";
//        String str = "abcdefghijklmnopqrstuvwxyz";
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            //产生0-61的数字
            int number = random.nextInt(str.length());
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    public static String getRandomChar() {
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890 +-*/!.,";
//        String str = "abcdefghijklmnopqrstuvwxyz";
        //由Random生成随机数
        Random random = new Random();
        //产生0-61的数字
        int number = random.nextInt(str.length());
        //将产生的数字通过length次承载到sb中
        char a = str.charAt(number);
        //将承载的字符转换成字符
        return "" + a;
    }

    public static String getRandomHanZiChar() {
        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))) + "";
    }

    public static String getRandomHanziString(int length) {
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            char a = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
            //将产生的数字通过length次承载到sb中
            sb.append(a);
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
}
