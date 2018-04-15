package com.example.test.multithread.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton3 {

    public static Connection getInstance(){
        return EnumSingleton.SingletonFactory.getConnection();
    }
    public enum EnumSingleton {

        SingletonFactory;

        private Connection connection;

        private EnumSingleton(){
            try {
                Class.forName("com.mysql.jdbc.Driver");

                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull",
                        "root",
                        "123456");
            }catch (SQLException e){

            }catch (ClassNotFoundException e){

            }
        }

        public Connection getConnection(){
            return connection;
        }

    }


}
