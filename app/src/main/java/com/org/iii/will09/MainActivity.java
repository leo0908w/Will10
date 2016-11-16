package com.org.iii.will09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String, Object>> data;
    private String[] from = {"title", "content", "img"};
    private int[] to = {R.id.item_title, R.id.item_content, R.id.item_img};
    private int[] imgs = {R.drawable.b0, R.drawable.b1, R.drawable.b2, R.drawable.b3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        initListView();
    }

    private  void initListView() {
        data = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> row = new HashMap<>();
            row.put(from[0], "PPAP" + i);
            row.put(from[1], "apple" + i);
            row.put(from[2], imgs[(int)(Math.random() * 4)]);
            data.add(row);
        }

        adapter = new SimpleAdapter(this, data, R.layout.layout_item, from, to);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("will", "onItemClick" + i);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("will", "onItemLongClick" + i);
                delItem(i);
                return false;
            }
        });
    }
    private void delItem(int pos) {
        data.remove(pos);
        adapter.notifyDataSetInvalidated();
    }
}

