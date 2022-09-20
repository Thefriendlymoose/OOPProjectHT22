package model.customer;

import com.mongodb.client.MongoDatabase;
import database.DataBaseConnection;


public class TestClass {

    private MongoDatabase db;

    TestClass(){
        this.db = DataBaseConnection.getInstance().getDataBase();
    }

    public void test(){

    }
}
