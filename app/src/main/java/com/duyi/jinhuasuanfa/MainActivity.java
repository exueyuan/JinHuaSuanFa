package com.duyi.jinhuasuanfa;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import example.GeTi;
import example.QunTiList;

public class MainActivity extends AppCompatActivity {
    Button bt_jinhua;
    EditText et_zifuchuan;
    TextView tv_text;
    QunTiList qunTiList = new QunTiList();
    Handler handler = new Handler();
    RadioGroup rg_daishu;
    Button bt_stop;
    Button bt_start;
    int daishu = 1000;

    boolean stopJinhua = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_zifuchuan = (EditText) findViewById(R.id.et_zifuchuan);
        bt_jinhua = (Button) findViewById(R.id.bt_jinhua);
        tv_text = (TextView) findViewById(R.id.tv_text);
        rg_daishu = (RadioGroup) findViewById(R.id.rg_daishu);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);

        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopJinhua = true;
            }
        });


        rg_daishu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_50) {
                    daishu = 50;
                } else if (checkedId == R.id.rb_100) {
                    daishu = 100;
                } else if (checkedId == R.id.rb_500) {
                    daishu = 500;
                } else if (checkedId == R.id.rb_1000) {
                    daishu = 1000;
                } else if (checkedId == R.id.rb_2000) {
                    daishu = 2000;
                }
            }
        });

        bt_jinhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_zifuchuan.getText().toString())) {
                    GeTi.MUBIAO = et_zifuchuan.getText().toString();
                }
                stopJinhua = false;
                new Thread() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                bt_jinhua.setEnabled(false);
                            }
                        });
                        qunTiList.geTiList.clear();
                        qunTiList.saixuanGeTiList.clear();
                        qunTiList.init();
                        final int daishuu = daishu;
                        for (int i = 0; i < daishuu; i++) {
                            qunTiList.xunhuansaixuan();
                            qunTiList.xunhuanjiaocha();
                            qunTiList.xunhuanbianyi();

                            String allText = "";
                            for (int j = 0; j < qunTiList.geTiList.size(); j++) {
                                String text = qunTiList.geTiList.get(j).getString();
                                allText = allText + text;
                            }
                            final String finalAllText = allText;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_text.setText(finalAllText);
                                }
                            });
                            if (stopJinhua) {
                                break;
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                bt_jinhua.setEnabled(true);
                            }
                        });
                    }
                }.start();

            }
        });

    }
}
