package pl.website.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return getDS().getConnection();
    }

    public static DataSource getDS() {
        if (dataSource == null){
            try {
                Context initialContent = new InitialContext();
                Context envContext = (Context) initialContent.lookup("java:comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/website");
                dataSource = ds;
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return dataSource;
    }
}
