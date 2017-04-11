package com.example.marcin.healthcare.repository;

import android.content.Context;

import com.example.marcin.healthcare.database.OrmLiteDatabaseHelper;
import com.example.marcin.healthcare.model.College;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mchyl on 10/04/2017.
 */

public class CollegeRepository {

    public static List<College> findAll(Context context) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getCollegeDaoR().queryForAll();
    }

    public static College findById(Context context, int collegeId) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getCollegeDaoR().queryForId(collegeId);
    }

    public static void addCollege(Context context, College college) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getCollegeDaoR().create(college);
    }

    public static void updateCollege(Context context, College college) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getCollegeDaoR().update(college);
    }

    public static void deleteCollege(Context context, College college) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getCollegeDaoR().delete(college);
    }

}
