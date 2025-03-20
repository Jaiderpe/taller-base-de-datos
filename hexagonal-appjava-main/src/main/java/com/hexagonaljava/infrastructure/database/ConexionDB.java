package com.hexagonaljava.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/futbol";
    private static final String USER = "root"; // Cambia esto si usas otro usuario
    private static final String PASSWORD = "1093907566@"; // Cambia esto si tienes contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}