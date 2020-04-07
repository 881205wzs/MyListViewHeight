package com.davis.mylistviewheight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private ListView listView;
    private ListCommonAdapter adapter = null;
    private List<String> listDatas = new ArrayList<String>();
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.listview);

        adapter = new ListCommonAdapter(this, listDatas, R.layout.list_item);
        listView.setAdapter(adapter);
    }

    private void itemAdd(){
        if(listDatas == null){
            listDatas = new ArrayList<String>();
        }

        String info = "";
        for(int i=0;i<count;i++){
            info += "简单测试简单测试";
        }

        count++;
        listDatas.add(info);
        adapter.refresh();

        adapter.setListViewHeight(listView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                itemAdd();
                break;
        }
    }
}
