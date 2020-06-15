package com.duyi.jinhuasuanfa

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import example.GeTi
import example.QunTiList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var qunTiList = QunTiList()
    internal var handler = Handler()
    internal var daishu = 1000

    internal var stopJinhua = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_stop.setOnClickListener { stopJinhua = true }


        rg_daishu.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_50 -> daishu = 50
                R.id.rb_100 -> daishu = 100
                R.id.rb_500 -> daishu = 500
                R.id.rb_1000 -> daishu = 1000
                R.id.rb_2000 -> daishu = 2000
            }
        }

        bt_jinhua.setOnClickListener {
            if (!TextUtils.isEmpty(et_zifuchuan.text.toString())) {
                GeTi.MUBIAO = et_zifuchuan.text.toString()
            }
            stopJinhua = false
            object : Thread() {
                override fun run() {
                    handler.post { bt_jinhua.isEnabled = false }
                    qunTiList.geTiList.clear()
                    qunTiList.saixuanGeTiList.clear()
                    qunTiList.init()
                    val daishuu = daishu
                    for (i in 0 until daishuu) {
                        qunTiList.xunhuansaixuan()
                        qunTiList.xunhuanjiaocha()
                        qunTiList.xunhuanbianyi()

                        var allText = ""
                        for (j in qunTiList.geTiList.indices) {
                            val text = qunTiList.geTiList[j].string
                            allText = allText + text
                        }
                        val finalAllText = allText
                        handler.post { tv_text.text = finalAllText }
                        if (stopJinhua) {
                            break
                        }
                    }

                    handler.post { bt_jinhua.isEnabled = true }
                }
            }.start()
        }

    }
}