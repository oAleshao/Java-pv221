package itstep.learning.db;

import javax.inject.Inject;
import java.sql.*;

public class DbDemo {

    @Inject
    private Connection connection;

    public void run(){
        System.out.println("Db demo");
        try{
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SHOW DATABASES");
            while (res.next()){
                System.out.println(res.getString(1)); // !!
                // JDBC starts counting from 1
            }
            res.close();
            statement.close();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}

/*
* JDBC - Java DB Connectivity - технологія доступу до данних, еквівалентна ADO.NET або PDO (PHP)
* - попередньо створюємо БД та користувача для неї
* - підбираємо конектор (драйвер БД) для відповідної СУБД (MySQL), додаємо його до
* проєкту (pom.xml)
*
*
*
*
*
* */