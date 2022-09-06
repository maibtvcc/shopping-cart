package test;

import java.sql.Connection;
import java.sql.SQLException;

import shopping_cart.connection.JDBCUtil;

public class testJDBC {
	public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);
        JDBCUtil.printInfo(connection);
        JDBCUtil.closeConnection(connection);
        System.out.println(connection);
    }
}
