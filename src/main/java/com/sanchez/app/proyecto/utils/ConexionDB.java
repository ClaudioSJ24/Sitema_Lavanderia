package com.sanchez.app.proyecto.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    //Atributos
    private static String url = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";
    private static String username = "SYSTEM";
    private static String password = "claudio";

    private Connection conn = null;

    //Metodos
    //metodo que establece la conexion al servidor de bd oracle
    public Connection getInstance(){
        try {
            if(this.conn == null){
                ConexionDB c = new ConexionDB();
                c.conn = DriverManager.getConnection(url,username,password);
            }
            else {
                return this.conn;
            }
            return DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
