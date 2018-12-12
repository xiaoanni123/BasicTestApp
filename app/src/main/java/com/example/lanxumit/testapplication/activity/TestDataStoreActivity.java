package com.example.lanxumit.testapplication.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lanxumit.testapplication.R;
import com.example.lanxumit.testapplication.dao.MySQLite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestDataStoreActivity extends AppCompatActivity {


    private EditText sEditText;
    private MySQLite testDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_save_data);
        sEditText = findViewById(R.id.storeET);
        Button storeBT = findViewById(R.id.storeBT);
        String loadData = loadData();
        if (!TextUtils.isEmpty(loadData)) {
            sEditText.setText(loadData);
            sEditText.setSelection(loadData.length());
            Toast.makeText(this, "this data is from fileStore", Toast.LENGTH_LONG).show();
        }
        testDb = new MySQLite(this, "TestDb", null, 1);
        storeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDb.getReadableDatabase();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String data = sEditText.getText().toString();
        savedData(data);
    }

    //data  this is need store data
    private void savedData(String data) {
        BufferedWriter writer = null;
        try {
            FileOutputStream fileOutputStream = openFileOutput("TestFile", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String loadData() {
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = openFileInput("TestFile");
            reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    //
    private void storeDataBySharedPreferences() {
//        getPreferences(int mode);-- activity
//        PreferenceManager.getDefaultSharedPreferences(this);
//        getSharedPreferences(String fileName,int mode);
        SharedPreferences testSp = getSharedPreferences("TestSp", MODE_PRIVATE);
        SharedPreferences.Editor edit = testSp.edit();
        edit.putString("blKey", "this is string");
        edit.apply();
        edit.commit();
    }
}
