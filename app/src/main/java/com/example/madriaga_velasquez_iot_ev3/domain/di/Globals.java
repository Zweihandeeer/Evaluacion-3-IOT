package com.example.madriaga_velasquez_iot_ev3.domain.di;

import android.content.Context;

import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;

public class Globals {
    private static Globals globals;
    private DBhelper dBhelper;

    public static synchronized Globals instance(){
        if(globals==null){
            globals = new Globals();
        }
        return globals;
    }

    public void createDb(Context context){
        dBhelper = new DBhelper(context, Env.dbName, null, 1);
    }

    public DBhelper getDb(){
        return dBhelper;
    }
}
