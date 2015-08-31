package com.example.giangdam.buydesireex1.daomanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.giangdam.buydesireex1.dao.DBUser;
import com.example.giangdam.buydesireex1.dao.DBUserDao;
import com.example.giangdam.buydesireex1.dao.DaoMaster;
import com.example.giangdam.buydesireex1.dao.DaoSession;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Giang.Dam on 8/28/2015.
 */
public class DatabaseManager implements IDatabaseManager, AsyncOperationListener {

    /**
     * Class tag. Used for debug.
     */
    private static final String TAG = DatabaseManager.class.getCanonicalName();


    /**
     * Instance of DatabaseManager
     */
    private static DatabaseManager instance;


    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    private Context context;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private AsyncSession asyncSession;
    private List<AsyncOperation> completedOperations;


    /**
     * Constructs a new DatabaseManager with the specified arguments.
     */
    public DatabaseManager(final Context context){
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(this.context,"buydesire-database",null);
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
    }


    /**
     * @return this.instance
     */
    public static DatabaseManager getInstance(Context context){
        if(instance == null){
            instance = new DatabaseManager(context);
        }
        return instance;
    }



    /**
     * Query for readable DB
     */
    public void openReadableDb() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener( this);
    }


    /**
     * Query for writable DB
     */
    public void openWritableDb() throws SQLiteException {
        database = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);
    }

    private void assertWaitForCompletion1Sec() {
        asyncSession.waitForCompletion(1000);
        asyncSession.isCompleted();
    }



    @Override
    public void closeDbConnections() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
        if (instance != null) {
            instance = null;
        }
    }




    @Override
    public synchronized void dropDatabase() {
        try {
            openWritableDb();
            DaoMaster.dropAllTables(database, true); // drops all tables
            mHelper.onCreate(database);              // creates the tables
            asyncSession.deleteAll(DBUser.class);    // clear all elements from a table
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public  synchronized DBUser insertUser(DBUser user) {
        try {
            if (user != null) {
                openWritableDb();
                DBUserDao userDao = daoSession.getDBUserDao();
                userDao.insert(user);
                Log.d(TAG, "Inserted user: " + user.getEmail() + " to the schema.");
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public synchronized List<DBUser> listUsers() {
        List<DBUser> users = null;
        try {
            openReadableDb();
            DBUserDao userDao = daoSession.getDBUserDao();
            users = userDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public synchronized void updateUser(DBUser user) {
        try {
            if (user != null) {
                openWritableDb();
                daoSession.update(user);
                Log.d(TAG, "Updated user: " + user.getEmail() + " from the schema.");
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void deleteUserByEmail(String email) {
        try {
            openWritableDb();
            DBUserDao userDao = daoSession.getDBUserDao();
            QueryBuilder<DBUser> queryBuilder = userDao.queryBuilder().where(DBUserDao.Properties.Email.eq(email));
            List<DBUser> userToDelete = queryBuilder.list();
            for (DBUser user : userToDelete) {
                userDao.delete(user);
            }
            daoSession.clear();
            Log.d(TAG, userToDelete.size() + " entry. " + "Deleted user: " + email + " from the schema.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized boolean deleteUserById(Long userId) {
        try {
            openWritableDb();
            DBUserDao userDao = daoSession.getDBUserDao();
            userDao.deleteByKey(userId);
            daoSession.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public synchronized void deleteUsers() {
        try {
            openWritableDb();
            DBUserDao userDao = daoSession.getDBUserDao();
            userDao.deleteAll();
            daoSession.clear();
            Log.d(TAG, "Delete all users from the schema.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
