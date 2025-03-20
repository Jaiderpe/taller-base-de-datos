package com.hexagonaljava.infrastructure.database;

import com.hexagonaljava.infrastructure.persistence.Entrenador;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorDAO {
    public List<Entrenador> getAllEntrenadores() {
        List<Entrenador> entrenadores = new ArrayList<>();
        String query = "SELECT * FROM entrenadores";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Entrenador entrenador = new Entrenador();
                entrenador.setId(rs.getInt("id"));
                entrenador.setName(rs.getString("name"));
                entrenador.setNationality(rs.getString("nationality"));
                entrenador.setAge(rs.getInt("age"));
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }

    public void addEntrenador(Entrenador entrenador) {
        String query = "INSERT INTO entrenadores (name, nationality, age) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, entrenador.getName());
            pstmt.setString(2, entrenador.getNationality());
            pstmt.setInt(3, entrenador.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEntrenador(Entrenador entrenador) {
        String query = "UPDATE entrenadores SET name = ?, nationality = ?, age = ? WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, entrenador.getName());
            pstmt.setString(2, entrenador.getNationality());
            pstmt.setInt(3, entrenador.getAge());
            pstmt.setInt(4, entrenador.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEntrenador(int id) {
        String query = "DELETE FROM entrenadores WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}