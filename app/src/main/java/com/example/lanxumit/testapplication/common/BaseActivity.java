package com.example.lanxumit.testapplication.common;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToastShort(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
