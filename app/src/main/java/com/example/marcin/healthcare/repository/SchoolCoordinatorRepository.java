package com.example.marcin.healthcare.repository;

import android.content.Context;

import com.example.marcin.healthcare.database.OrmLiteDatabaseHelper;
import com.example.marcin.healthcare.model.SchoolCoordinator;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mchyl on 10/04/2017.
 */

public class SchoolCoordinatorRepository {

    public static List<SchoolCoordinator> findAll(Context context) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getSchoolCoordinatorDao().queryForAll();
    }

    public static SchoolCoordinator findById(Context context, int schoolCoorinatorId) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return ormLiteDatabaseHelper.getSchoolCoordinatorDao().queryForId(schoolCoorinatorId);
    }

    public static void addSchoolCoordinator(Context context, SchoolCoordinator schoolCoorinator) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getSchoolCoordinatorDao().create(schoolCoorinator);
    }

    public static void updateSchoolCoordinator(Context context, SchoolCoordinator schoolCoorinator) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getSchoolCoordinatorDao().update(schoolCoorinator);
    }

    public static void deleteSchoolCoordinator(Context context, SchoolCoordinator schoolCoorinator) throws SQLException {
        OrmLiteDatabaseHelper ormLiteDatabaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        ormLiteDatabaseHelper.getSchoolCoordinatorDao().delete(schoolCoorinator);
    }

}
