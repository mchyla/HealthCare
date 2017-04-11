package com.example.marcin.healthcare.database;

/**
 * Created by mchyl on 10/04/2017.
 */


import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.marcin.healthcare.model.College;
import com.example.marcin.healthcare.model.Kid;
import com.example.marcin.healthcare.model.Leader;
import com.example.marcin.healthcare.model.SchoolCoordinator;
import com.example.marcin.healthcare.model.Volunteer;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {

/*    private static OrmLiteDatabaseHelper instance;
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "helloAndroid.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;*/

    private static OrmLiteDatabaseHelper instance;

    private static final String DATABASE_NAME = "helloAndroidTest.db";
    private static final int DATABASE_VERSION = 9;

    // the DAO object we use to access the SimpleData table
    private Dao<Leader, Integer> simpleDao = null;
    private RuntimeExceptionDao<Leader, Integer> leaderDao = null;

    private Dao<College, Integer> collegeDao = null;
    private RuntimeExceptionDao<College, Integer> collegeDaoR = null;

    private Dao<SchoolCoordinator, Integer> schoolCoordinatorDao = null;
    private RuntimeExceptionDao<SchoolCoordinator, Integer> schoolCoordinatorDaoR = null;

    private Dao<Kid, Integer> kidDao = null;
    private RuntimeExceptionDao<Kid, Integer> kidDaoR = null;


    public OrmLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OrmLiteDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new OrmLiteDatabaseHelper(context);
        }
        return instance;
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(OrmLiteDatabaseHelper.class.getName(), "onCreate!!!!!!!!!!!!!!!!!!!!!!");
            TableUtils.createTableIfNotExists(connectionSource, Leader.class);
            TableUtils.createTableIfNotExists(connectionSource, SchoolCoordinator.class);
            TableUtils.createTableIfNotExists(connectionSource, Volunteer.class);
            TableUtils.createTableIfNotExists(connectionSource, Kid.class);
            TableUtils.createTableIfNotExists(connectionSource, College.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        recreateTables();
    }

    private void recreateTables() {
        try {

            Log.i(OrmLiteDatabaseHelper.class.getName(), "onUPDATE !!!!!!!!!!!!!!!!!!!!!!");
            TableUtils.dropTable(connectionSource, Kid.class, true);
            TableUtils.dropTable(connectionSource, Volunteer.class, true);
            TableUtils.dropTable(connectionSource, SchoolCoordinator.class, true);
            TableUtils.dropTable(connectionSource, College.class, true);
            TableUtils.dropTable(connectionSource, Leader.class, true);

            TableUtils.createTableIfNotExists(connectionSource, Leader.class);
            TableUtils.createTableIfNotExists(connectionSource, SchoolCoordinator.class);
            TableUtils.createTableIfNotExists(connectionSource, Volunteer.class);
            TableUtils.createTableIfNotExists(connectionSource, Kid.class);
            TableUtils.createTableIfNotExists(connectionSource, College.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
   public Dao<Leader, Integer> getLeaderDao() throws SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(Leader.class);
        }
        return simpleDao;
    }

    public Dao<College, Integer> getCollegeDao() throws SQLException {
        if (collegeDao == null) {
            collegeDao = getDao(College.class);
        }
        return collegeDao;
    }

    public Dao<SchoolCoordinator, Integer> getSchoolCoordinatorDao() throws SQLException {
        if (schoolCoordinatorDao == null) {
            schoolCoordinatorDao = getDao(SchoolCoordinator.class);
        }
        return schoolCoordinatorDao;
    }

/*    public Dao<Kid, Integer> getKidDao() throws SQLException {
        if (kidDao == null) {
            kidDao = getDao(Kid.class);
        }
        return kidDao;
    }*/


    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Leader, Integer> getLeaderDaoR() {
        if (leaderDao == null) {
            leaderDao = getRuntimeExceptionDao(Leader.class);
        }
        return leaderDao;
    }

    public RuntimeExceptionDao<College, Integer> getCollegeDaoR() {
        if (collegeDaoR == null) {
            collegeDaoR = getRuntimeExceptionDao(College.class);
        }
        return collegeDaoR;
    }

    public RuntimeExceptionDao<SchoolCoordinator, Integer> getSchoolCoordinatorDaoR() throws SQLException {
        if (schoolCoordinatorDaoR == null) {
            schoolCoordinatorDaoR = getRuntimeExceptionDao(SchoolCoordinator.class);
        }
        return schoolCoordinatorDaoR;
    }

    public RuntimeExceptionDao<Kid, Integer> getKidDaoR() throws SQLException {
        if (kidDaoR == null) {
            kidDaoR = getRuntimeExceptionDao(Kid.class);
        }
        return kidDaoR;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        //simpleDao = null;
        leaderDao = null;
    }

}
