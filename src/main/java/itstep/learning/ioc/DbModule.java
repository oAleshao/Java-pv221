package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.sql.*;

public class DbModule extends AbstractModule {
   private Connection connection = null;
   private Driver mysqlDriver = null;

   @Override
    protected void configure() {

    }

    @Provides
    private Connection getConnection() {
        if (this.connection == null) {
            try{
                // create new driver DBMS
                this.mysqlDriver = new com.mysql.cj.jdbc.Driver();
                // register it
                DriverManager.registerDriver(this.mysqlDriver);

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3308/java_pv222" +
                                "?useUnicode=true&characterEncoding=utf8",
                        "user222",
                        "pass222");
                // different between ADO and JDBC connected when create
            }
            catch (SQLException ex){
                System.out.println("DbModule::getConnection " + ex.getMessage());
            }
        }
        return  this.connection;
    }
}
