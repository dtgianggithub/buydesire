package com.example.giangdam.buydesireex1.daomanager;

import com.example.giangdam.buydesireex1.dao.DBUser;

import java.util.List;

/**
 * Created by Giang.Dam on 8/28/2015.
 */
public interface IDatabaseManager {

    /**
     *Closing availble connections
     */
    public void closeDbConnections();


    /**
     * Delete all tables and content from our database
     */
    public void dropDatabase();


    /**
     * Insert the user into database
     */
    public DBUser insertUser(DBUser user);


    /**
     * Get all users from databse
     */
    public List<DBUser> listUsers();

    /**
     * Update User from database
     */
    public void updateUser(DBUser user);


    /**
     * Delete all users with certain email from database
     */
    public void deleteUserByEmail(String email);

    /**
     * Delete users with certain id from database
     */
    public boolean deleteUserById(Long userId);


    /**
     * Delete all users from database
     */
    public void deleteUsers();
}
