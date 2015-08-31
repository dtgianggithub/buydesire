package com.example.giangdam.buydesireex1;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    //get base of non-absolute path
    //get base of non-absolut path
    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";


    public static void main(String args[]) throws IOException {
        Schema schema = new Schema(1,"com.example.giangdam.buydesireex1.dao");

        //add table
        addTable(schema);
        //generate
        try {
            new DaoGenerator().generateAll(schema,OUT_DIR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTable(Schema schema) {
        //Etities ---- table
        Entity user = addUser(schema);
    }

    private static Entity addUser(Schema schema) {
        Entity user = schema.addEntity("DBUser");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("email").notNull().unique();
        user.addStringProperty("password").notNull();
        user.addStringProperty("firstname").notNull();
        user.addStringProperty("lastname").notNull();
        user.addStringProperty("gender");
        user.addStringProperty("profilename");
        user.addStringProperty("aboutme");
        user.addStringProperty("websiteurl");

        return user;

    }
}
