package com.example.giangdam.buydesireex1.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.giangdam.buydesireex1.dao.DBUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBUSER".
*/
public class DBUserDao extends AbstractDao<DBUser, Long> {

    public static final String TABLENAME = "DBUSER";

    /**
     * Properties of entity DBUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Email = new Property(1, String.class, "email", false, "EMAIL");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property Firstname = new Property(3, String.class, "firstname", false, "FIRSTNAME");
        public final static Property Lastname = new Property(4, String.class, "lastname", false, "LASTNAME");
        public final static Property Gender = new Property(5, String.class, "gender", false, "GENDER");
        public final static Property Profilename = new Property(6, String.class, "profilename", false, "PROFILENAME");
        public final static Property Aboutme = new Property(7, String.class, "aboutme", false, "ABOUTME");
        public final static Property Websiteurl = new Property(8, String.class, "websiteurl", false, "WEBSITEURL");
    };


    public DBUserDao(DaoConfig config) {
        super(config);
    }
    
    public DBUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBUSER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"EMAIL\" TEXT NOT NULL UNIQUE ," + // 1: email
                "\"PASSWORD\" TEXT NOT NULL ," + // 2: password
                "\"FIRSTNAME\" TEXT NOT NULL ," + // 3: firstname
                "\"LASTNAME\" TEXT NOT NULL ," + // 4: lastname
                "\"GENDER\" TEXT," + // 5: gender
                "\"PROFILENAME\" TEXT," + // 6: profilename
                "\"ABOUTME\" TEXT," + // 7: aboutme
                "\"WEBSITEURL\" TEXT);"); // 8: websiteurl
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBUSER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getEmail());
        stmt.bindString(3, entity.getPassword());
        stmt.bindString(4, entity.getFirstname());
        stmt.bindString(5, entity.getLastname());
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(6, gender);
        }
 
        String profilename = entity.getProfilename();
        if (profilename != null) {
            stmt.bindString(7, profilename);
        }
 
        String aboutme = entity.getAboutme();
        if (aboutme != null) {
            stmt.bindString(8, aboutme);
        }
 
        String websiteurl = entity.getWebsiteurl();
        if (websiteurl != null) {
            stmt.bindString(9, websiteurl);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DBUser readEntity(Cursor cursor, int offset) {
        DBUser entity = new DBUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // email
            cursor.getString(offset + 2), // password
            cursor.getString(offset + 3), // firstname
            cursor.getString(offset + 4), // lastname
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // gender
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // profilename
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // aboutme
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // websiteurl
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEmail(cursor.getString(offset + 1));
        entity.setPassword(cursor.getString(offset + 2));
        entity.setFirstname(cursor.getString(offset + 3));
        entity.setLastname(cursor.getString(offset + 4));
        entity.setGender(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProfilename(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAboutme(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setWebsiteurl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DBUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DBUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
