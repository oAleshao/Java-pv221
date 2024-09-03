package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import itstep.learning.fs.FileDemo;

import java.sql.*;
import java.util.Map;

public class DbModule extends AbstractModule {
   private Connection connection = null;
   private Driver mysqlDriver = null;
   private FileDemo fileDemo = new FileDemo();

   @Override
    protected void configure() {

    }

    @Provides
    private Connection getConnection() {
        if (this.connection == null) {
            Map<String, String> dataDbIni = fileDemo.getDbIniData();
            try{
                // create new driver DBMS
                this.mysqlDriver = new com.mysql.cj.jdbc.Driver();
                // register it
                DriverManager.registerDriver(this.mysqlDriver);

                connection = DriverManager.getConnection(
                        "jdbc:" + dataDbIni.get("dbms") + "://" + dataDbIni.get("host") + ":" + dataDbIni.get("port") + "/" + dataDbIni.get("schema") +
                                "?useUnicode=true&characterEncoding=" + dataDbIni.get("utf"),
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
