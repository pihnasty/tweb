package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {
    
    private static Connection conn;
    private static InitialContext ic;
    private static DataSource ds;
    
    public static Connection getConnection() {
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/Flow_production");
            if (conn==null) {
                conn = ds.getConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }

    public static Connection getConnectionForTest() {
        try {
            Hashtable<String,String> environment = new Hashtable<>();
            environment.put("java.naming.factory.initial","com.sun.enterprise.naming.impl.SerialInitContextFactory");
            environment.put("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            environment.put("java.naming.factory.url.pkgs","com.sun.enterprise.naming");

            ic = new InitialContext(environment);
            ds = (DataSource) ic.lookup("jdbc/Flow_production");
            if (conn==null) {
                conn = ds.getConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

}
