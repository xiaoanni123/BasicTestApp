package com.example.lanxumit.testapplication.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lanxumit.testapplication.R;
import com.example.lanxumit.testapplication.adapter.TestLearnAdapter;
import com.example.lanxumit.testapplication.entity.TestEntity_1;

import java.util.ArrayList;
import java.util.List;

public class TestUseUiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestUseUiActivity";

    private EditText leEditText;

    private String[] data = {"Apple", "Banana", "Android", "Iphone", "XiaoMi", "HuaWei", "MeiZu", "Oppo", "Vivio"
            , "Android", "Iphone", "XiaoMi", "HuaWei", "Iphone", "XiaoMi", "HuaWei", "MeiZu", "Apple", "Banana", "Android", "Iphone", "XiaoMi", "HuaWei", "MeiZu", "Oppo", "Vivio"
            , "Android", "Iphone", "XiaoMi", "HuaWei", "Iphone", "XiaoMi", "HuaWei", "MeiZu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_use_ui);
        init();
    }

    private void init() {
        TextView leTextView = findViewById(R.id.learnUi_tv);
        Button leButton = findViewById(R.id.learnUi_bt);
        leButton.setOnClickListener(this);
        leEditText = findViewById(R.id.learnUi_et);
        basicWayUseListView();
    }

    private void basicWayUseListView() {
//        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(TestUseUiActivity.this,
//                android.R.layout.simple_gallery_item,data);

        //
        List<TestEntity_1> mList = new ArrayList<>();
        for (int i = 0; i < data.length - 1; i++) {
            TestEntity_1 testEntity_1 = new TestEntity_1(data[i], R.mipmap.ic_launcher);
            mList.add(testEntity_1);
        }
        TestLearnAdapter testLearnAdapter = new TestLearnAdapter(this,R.layout.item_basic_lv,mList);
        ListView leListView = findViewById(R.id.learnUse_lv);
        leListView.setAdapter(testLearnAdapter);
        leListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.learnUi_bt:
                leEditText.getText().toString();
                initProgressBar();
                break;
            default:
                break;
        }
    }

    private void initProgressBar() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("this is title");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
