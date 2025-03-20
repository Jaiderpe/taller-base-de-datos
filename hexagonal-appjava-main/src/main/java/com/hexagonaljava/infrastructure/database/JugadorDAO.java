package com.hexagonaljava.infrastructure.database;

import com.hexagonaljava.infrastructure.persistence.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {

    // Método para obtener todos los jugadores
    public List<Jugador> getAllJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        String query = "SELECT * FROM jugadores";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(rs.getInt("id"));
                jugador.setEquipoId(rs.getInt("equipo_id"));
                jugador.setDorsal(rs.getInt("dorsal"));
                jugador.setName(rs.getString("name"));
                jugador.setNationality(rs.getString("nationality"));
                jugador.setAge(rs.getInt("age"));
                jugador.setHeight(rs.getInt("height"));
                jugador.setWeight(rs.getInt("weight"));
                jugador.setPosition(rs.getString("position"));
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    // Método para agregar un jugador
    public void addJugador(Jugador jugador) {
        String query = "INSERT INTO jugadores (equipo_id, dorsal, name, nationality, age, height, weight, position) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, jugador.getEquipoId());
            pstmt.setInt(2, jugador.getDorsal());
            pstmt.setString(3, jugador.getName());
            pstmt.setString(4, jugador.getNationality());
            pstmt.setInt(5, jugador.getAge());
            pstmt.setInt(6, jugador.getHeight());
            pstmt.setInt(7, jugador.getWeight());
            pstmt.setString(8, jugador.getPosition());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un jugador
    public void updateJugador(Jugador jugador) {
        String query = "UPDATE jugadores SET equipo_id = ?, dorsal = ?, name = ?, nationality = ?, age = ?, height = ?, weight = ?, position = ? WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, jugador.getEquipoId());
            pstmt.setInt(2, jugador.getDorsal());
            pstmt.setString(3, jugador.getName());
            pstmt.setString(4, jugador.getNationality());
            pstmt.setInt(5, jugador.getAge());
            pstmt.setInt(6, jugador.getHeight());
            pstmt.setInt(7, jugador.getWeight());
            pstmt.setString(8, jugador.getPosition());
            pstmt.setInt(9, jugador.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un jugador
    public void deleteJugador(int id) {
        String query = "DELETE FROM jugadores WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para validar si el equipo existe
    public boolean equipoExiste(int equipoId) {
        String query = "SELECT COUNT(*) FROM equipos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, equipoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si el equipo existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}