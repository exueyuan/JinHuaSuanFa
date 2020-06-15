package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by exueyuan on 2018/4/11.
 */

public class QunTiList {
    public int geTiNum = 1000;
    public int saiXuanNum = geTiNum / 2;
    public int jiaoChaNum = geTiNum / 2;
    public int bianYiNum = geTiNum / 2;

    public List<GeTi> geTiList = new ArrayList<>();
    public List<GeTi> saixuanGeTiList = new ArrayList<>();


    public void init(int getiNum) {
        this.geTiNum = getiNum;
        this.saiXuanNum = geTiNum / 2;
        this.jiaoChaNum = geTiNum / 2;
        this.bianYiNum = geTiNum / 2;
        for (int i = 0; i < getiNum; i++) {
            GeTi geTi = new GeTi();
            geTi.init();
            geTiList.add(geTi);
        }
    }

    public void xunhuanbianyi() {
        for (int i = 0; i < bianYiNum; i++) {
            bianyi();
        }
    }

    private void bianyi() {
        Random random = new Random();
        GeTi geTi = geTiList.get(random.nextInt(geTiList.size()));
        int weizhi = random.nextInt(geTi.geti.length());
        geTi.geti = replaceIndex(weizhi, geTi.geti, GeTi.getRandomChar());
    }

    public void xunhuanjiaocha() {
        for (int i = 0; i < jiaoChaNum; i++) {
            jiaoCha();
        }
        saixuanGeTiList.clear();
    }

    private void jiaoCha() {
        Random random = new Random();
        //找到一个随机基因
        GeTi geTi1 = saixuanGeTiList.get(random.nextInt(saixuanGeTiList.size()));
        GeTi geTi2 = saixuanGeTiList.get(random.nextInt(saixuanGeTiList.size()));
        //找到交叉点
        int jiaochadian = random.nextInt(GeTi.MUBIAO.length());
        //找到geti1的字符串前半部分,
        String geti1qian = geTi1.geti.substring(0, jiaochadian);
        String geti1hou = geTi1.geti.substring(jiaochadian, geTi1.geti.length());
        String geti2qian = geTi2.geti.substring(0, jiaochadian);
        String geti2hou = geTi2.geti.substring(jiaochadian, geTi2.geti.length());
        GeTi geTi11 = new GeTi(geti1qian + geti2hou);
        GeTi geTi22 = new GeTi(geti2qian + geti1hou);
        geTiList.add(geTi11);
        geTiList.add(geTi22);
    }

    public void xunhuansaixuan() {
        for (int i = 0; i < saiXuanNum; i++) {
            saixuanGeTiList.add(saixuan2());
        }
        geTiList.clear();
    }

    private GeTi saixuan2() {
        Random random = new Random();
        int geTi1Num = random.nextInt(geTiList.size());
        GeTi geTi1 = geTiList.get(geTi1Num);
        int geTi2Num = random.nextInt(geTiList.size());
        GeTi geTi2 = geTiList.get(geTi2Num);
        int geTi3Num = random.nextInt(geTiList.size());
        GeTi geTi3 = geTiList.get(geTi3Num);
        if (geTi1.piPeiDu() >= geTi2.piPeiDu() && geTi1.piPeiDu() >= geTi3.piPeiDu()) {
            geTiList.remove(geTi1);
            return geTi1;
        }
        if (geTi2.piPeiDu() >= geTi1.piPeiDu() && geTi2.piPeiDu() >= geTi3.piPeiDu()) {
            geTiList.remove(geTi2);
            return geTi2;
        }
        if (geTi3.piPeiDu() >= geTi1.piPeiDu() && geTi3.piPeiDu() >= geTi2.piPeiDu()) {
            geTiList.remove(geTi3);
            return geTi3;
        }
        return geTi1;
    }

    private GeTi saixuan() {
        int pipeiSum = 0;
        for (GeTi geTi : geTiList) {
            pipeiSum = pipeiSum + geTi.piPeiDu();
        }
        int randomNum = 0;
        Random random = new Random();
        if (pipeiSum > 0) {
            randomNum = random.nextInt(pipeiSum);
        }
//        PrintUtils.print("随机数：" + randomNum);

        int pipeiSum2 = 0;
        for (GeTi geTi : geTiList) {
            if (randomNum >= pipeiSum2 && randomNum < pipeiSum2 + geTi.piPeiDu()) {
                geTiList.remove(geTi);
                return geTi;
            }
            pipeiSum2 = pipeiSum2 + geTi.piPeiDu();
        }
        return null;
    }


    public void print(List<GeTi> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
    }

    private static String replaceIndex(int index, String res, String str) {
        return res.substring(0, index) + str + res.substring(index + 1);
    }

}
