package edu.epn.conexion;

import java.sql.*;

public class Conexion {
    private Connection connection;
    private String url="jdbc:mysql://localhost:3306/primerabasep";
    private String usuario="Christian";
    private String contrasenia="liajosepaul";
    
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void establecerConexion(){
        try {
            connection = DriverManager.getConnection(url, usuario, contrasenia);
            System.out.println("Estoy dentro....\n");
            connection.setAutoCommit(true);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
    public void cerrarConexion(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
