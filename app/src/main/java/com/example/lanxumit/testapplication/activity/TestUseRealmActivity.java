package com.example.lanxumit.testapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import android.os.Bundle;

import com.example.lanxumit.testapplication.R;
import com.example.lanxumit.testapplication.entity.TestRealm_book;

public class TestUseRealmActivity extends AppCompatActivity {

    private Realm mRealmInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_use_realm);

        init();
    }

    private void init() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("TestRealm.realm")
                .schemaVersion(1)
                .build();
        mRealmInstance = Realm.getInstance(configuration);
        mRealmInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TestRealm_book object = realm.createObject(TestRealm_book.class);
                object.setId(1);
                object.setAuthor("Tom");
                object.setPage(500);
                object.setPrice(49);
            }
        });
        RealmResults<TestRealm_book> all = mRealmInstance.where(TestRealm_book.class).findAll();
        mRealmInstance.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
            }
        });
        new Thread(() -> {

        }).start();
    }
}
