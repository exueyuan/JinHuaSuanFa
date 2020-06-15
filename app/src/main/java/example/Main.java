package example;


import example.utils.PrintUtils;

public class Main {
    public static final int DaiShu = 1000;

    public static void main(String[] arg) {
        QunTiList qunTiList = new QunTiList();
        for (int i = 0; i < DaiShu; i++) {
            qunTiList.xunhuansaixuan();
            qunTiList.xunhuanjiaocha();
            qunTiList.xunhuanbianyi();
            qunTiList.print(qunTiList.geTiList);
        }

        PrintUtils.print("数目:"+qunTiList.geTiList.size());
    }
}
