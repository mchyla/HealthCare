package com.example.marcin.healthcare.repository;

import android.content.Context;

import com.example.marcin.healthcare.database.OrmLiteDatabaseHelper;
import com.example.marcin.healthcare.model.Leader;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mchyl on 10/04/2017.
 */

public class LeaderRepository {

    public static List<Leader> findAll(Context context) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getLeaderDao().queryForAll();
    }

    public static Leader findById(Context context, int leaderId) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getLeaderDao().queryForId(leaderId);
    }

    public static void addLeader(Context context, Leader leader) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getLeaderDao().create(leader);
    }

    public static void updateLeader(Context context, Leader leader) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getLeaderDao().update(leader);
    }

    public static void deleteLeader(Context context, Leader leader) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getLeaderDao().delete(leader);
    }


}
