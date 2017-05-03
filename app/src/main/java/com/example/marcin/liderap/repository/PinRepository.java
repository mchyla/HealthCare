package com.example.marcin.liderap.repository;

import android.content.Context;

import com.example.marcin.liderap.database.OrmLiteDatabaseHelper;
import com.example.marcin.liderap.model.Pin;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mchyl on 27/04/2017.
 */

public class PinRepository {
    //TODO: add user or login needed
    public static List<Pin> findAll(Context context) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getPinDaoR().queryForAll();
    }

    public static void addPin(Context context, Pin pin) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getPinDaoR().create(pin);
    }

    public static void updatePin(Context context, Pin pin) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getPinDaoR().update(pin);
    }
}
