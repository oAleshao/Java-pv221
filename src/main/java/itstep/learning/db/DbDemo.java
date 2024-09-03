package itstep.learning.db;

import javax.inject.Inject;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DbDemo {

    @Inject
    private Connection connection;

    public void run() {
            setAppelTime("Alex_java_pv222");
            showSelect("select * from journal");
    }

    private void showSelect(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                System.out.println(String.format("%s %s", res.getString(1), res.getString(2))); // !!
                // JDBC starts counting from 1
            }
            res.close();
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setAppelTime(String appName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String query = "INSERT INTO journal(appName, timeOfAppel) values(?, ?)";

       try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

           preparedStatement.setString(1, appName);
           preparedStatement.setString(2, LocalDateTime.now().toString());

           preparedStatement.executeUpdate();
       }catch (SQLException ex) {
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