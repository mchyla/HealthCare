package com.example.marcin.healthcare.repository;

import android.content.Context;

import com.example.marcin.healthcare.database.OrmLiteDatabaseHelper;
import com.example.marcin.healthcare.model.Kid;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mchyl on 10/04/2017.
 */

public class OrmLiteKidRepository {

    public static List<Kid> findAll(Context context) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getKidDaoR().queryForAll();
    }

    public static Kid findById(Context context, int kidId) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getKidDaoR().queryForId(kidId);
    }

    public static void addKid(Context context, Kid kid) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        //ormLiteDatabaseHelper.getWritableDatabase();
        ormLiteDatabaseHelper.getKidDaoR().create(kid);
    }

    public static void updateKid(Context context, Kid kid) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getKidDaoR().update(kid);
    }

    public static void deleteKid(Context context, Kid kid) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getKidDaoR().delete(kid);
    }


}
