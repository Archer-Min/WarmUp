package com.example.warmup;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

public class ToDoListViewModel extends AndroidViewModel {
    private final SavedStateHandle handle;
    private final String key = "ITEM_KEY";
    private final String shpName = "SHP_NAME";

    public ToDoListViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        //如果handle里没有我要的数据，就需要从sharedPreference里面读取
        if (!(handle.contains(key))) {
            load();
        }
    }

    public LiveData<String> getItem() {
        return handle.getLiveData(key);
    }

    public void setItem(String s) {
        handle.set(key, s);
    }

    //读取
    void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        String s = shp.getString(key, "sha");
        handle.set(key, s);
    }

    void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString(key, getItem().toString());
        editor.apply();
    }
}
