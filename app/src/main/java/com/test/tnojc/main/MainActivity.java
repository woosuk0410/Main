package com.test.tnojc.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream inputStream = getAssets().open("sub.apk");
            FileOutputStream fileOutputStream = new FileOutputStream(new File(getExternalFilesDir(null) + "/sub.apk"));
            int length = inputStream.available();
            byte[] buffer = new byte[length];
            inputStream.read(buffer, 0, length);
            fileOutputStream.write(buffer, 0, length);
            inputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DexClassLoader dexClassLoader = new DexClassLoader((getExternalFilesDir(null) + "/sub.apk"), "", null, getClass().getClassLoader());
            Class<?> _class;
            Object object = null;
            try {
                _class = dexClassLoader.loadClass("com.test.tnojc.sub.MyFragment");
                object = _class.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            getFragmentManager().beginTransaction().add(R.id.linear_layout, (android.app.Fragment) object, null).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
