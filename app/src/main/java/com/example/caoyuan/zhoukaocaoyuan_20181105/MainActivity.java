package com.example.caoyuan.zhoukaocaoyuan_20181105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarView;
    private LuckDraw draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //初始化变量
        toolbarView = findViewById(R.id.toolbarView);
        draw = findViewById(R.id.draw);
        //设置替换ActrivityBar
        setSupportActionBar(toolbarView);
        //添加一个返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置toolbar监听
        toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              MainActivity.this.finish();
            }
        });
        //接口回调
        draw.clickLine(new LuckDraw.ClickLine() {
            @Override
            public void Click(View v) {
                Toast.makeText(MainActivity.this,"旋转",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
