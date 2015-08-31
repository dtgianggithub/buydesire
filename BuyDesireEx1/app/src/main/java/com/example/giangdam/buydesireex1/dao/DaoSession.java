package com.example.giangdam.buydesireex1.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.giangdam.buydesireex1.dao.DBUser;

import com.example.giangdam.buydesireex1.dao.DBUserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dBUserDaoConfig;

    private final DBUserDao dBUserDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dBUserDaoConfig = daoConfigMap.get(DBUserDao.class).clone();
        dBUserDaoConfig.initIdentityScope(type);

        dBUserDao = new DBUserDao(dBUserDaoConfig, this);

        registerDao(DBUser.class, dBUserDao);
    }
    
    public void clear() {
        dBUserDaoConfig.getIdentityScope().clear();
    }

    public DBUserDao getDBUserDao() {
        return dBUserDao;
    }

}
