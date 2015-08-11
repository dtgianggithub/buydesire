package com.example.giangdam.model;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Giang.Dam on 8/7/2015.
 */
public class CacheManager extends AppCompatActivity{

    public static void createCache(JSONObject jsonObject, String cachefile, File pathCacheDir) throws IOException {
        //File pathCacheDir = getCacheDir();
        File newCacheFile = new File(pathCacheDir,cachefile) ;
        FileOutputStream fileOutputStream = new FileOutputStream(newCacheFile.getAbsolutePath());
        //fileOutputStream.write(jsonObject.toString().getBytes());
        //fileOutputStream.close();
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(jsonObject.toString());
        outputStream.flush();
        outputStream.close();
    }

    public static String readCache(String cachefile, File pathCacheDir) throws IOException, ClassNotFoundException {
       // File pathCacheDir = getCacheDir();
        File newCacheFile = new File(pathCacheDir,cachefile) ;
        FileInputStream fileInputStream = new FileInputStream(newCacheFile.getAbsolutePath());
        //ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(new File(getCacheDir(),"")+cachefile)));

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        String jsonObject = (String) objectInputStream.readObject();
        objectInputStream.close();
        return  jsonObject;
    }


}
