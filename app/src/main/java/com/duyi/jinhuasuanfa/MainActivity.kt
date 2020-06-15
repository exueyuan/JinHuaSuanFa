package com.duyi.jinhuasuanfa

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import example.GeTi
import example.QunTiList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var qunTiList = QunTiList()
    internal var handler = Handler()
    internal var daishu = 1000
    internal var geTiNum = 1000

    internal var stopJinhua = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_stop.setOnClickListener { stopJinhua = true }

        rb_1000.isChecked = true
        rg_daishu.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_50 -> daishu = 50
                R.id.rb_100 -> daishu = 100
                R.id.rb_500 -> daishu = 500
                R.id.rb_1000 -> daishu = 1000
                R.id.rb_2000 -> daishu = 2000
            }
        }

        rb_zhongqun_1000.isChecked = true
        rg_zhongqun.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rb_zhongqun_10 -> geTiNum = 10
                R.id.rb_zhongqun_50 -> geTiNum = 50
                R.id.rb_zhongqun_100 -> geTiNum = 100
                R.id.rb_zhongqun_500 -> geTiNum = 500
                R.id.rb_zhongqun_1000 -> geTiNum = 1000
            }
        }

        bt_jinhua.setOnClickListener {
            if (!TextUtils.isEmpty(et_zifuchuan.text.toString())) {
                GeTi.MUBIAO = et_zifuchuan.text.toString()
            } else {
                Toast.makeText(this, "输入不能为空!!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            stopJinhua = false
            object : Thread() {
                override fun run() {
                    handler.post { bt_jinhua.isEnabled = false }
                    qunTiList.geTiList.clear()
                    qunTiList.saixuanGeTiList.clear()
                    qunTiList.init(geTiNum)
                    val daishuu = daishu
                    for (i in 0 until daishuu) {
                        qunTiList.xunhuansaixuan()
                        qunTiList.xunhuanjiaocha()
                        qunTiList.xunhuanbianyi()

                        var allText = ""
                        for (j in qunTiList.geTiList.indices) {
                            val text = qunTiList.geTiList[j].string
                            allText += text
                        }
                        val finalAllText = allText
                        handler.post {
                            tv_text.text = finalAllText
                            tv_tip.text = "进化代数:$i, 最大匹配度:${GeTi.MUBIAO.length}"
                        }
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