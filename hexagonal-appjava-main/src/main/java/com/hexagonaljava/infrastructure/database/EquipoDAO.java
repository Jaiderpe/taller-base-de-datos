package com.hexagonaljava.infrastructure.database;

import com.hexagonaljava.infrastructure.persistence.Equipo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {
    public List<Equipo> getAllEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        String query = "SELECT * FROM equipos";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getInt("id"));
                equipo.setName(rs.getString("name"));
                equipo.setYearfoundation(rs.getInt("yearfoundation"));
                equipo.setCoachId(rs.getInt("coach_id"));
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    public void addEquipo(Equipo equipo) {
        String query = "INSERT INTO equipos (name, yearfoundation, coach_id) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, equipo.getName());
            pstmt.setInt(2, equipo.getYearfoundation());
            pstmt.setInt(3, equipo.getCoachId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEquipo(Equipo equipo) {
        String query = "UPDATE equipos SET name = ?, yearfoundation = ?, coach_id = ? WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, equipo.getName());
            pstmt.setInt(2, equipo.getYearfoundation());
            pstmt.setInt(3, equipo.getCoachId());
            pstmt.setInt(4, equipo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEquipo(int id) {
        String query = "DELETE FROM equipos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}