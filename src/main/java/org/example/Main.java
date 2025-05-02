package org.example;

import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Connection conexion = conetcar();

        consulta(conexion);
        insertar(conexion);
        consulta(conexion);

        desconectar(conexion);
    }

    private static Connection conetcar() {
        Connection conexion;
        String host = "jdbc:mariadb://localhost:3307/";
        String user = "root";
        String password = "";
        String db = "instituto";

        try {
            conexion = DriverManager.getConnection(host+db, user, password);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void desconectar(Connection conexion) {
        try {
            conexion.close();
            System.out.println("Desconexion exitosa");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consulta(Connection conexion ) {
        String query = "SELECT * FROM estudiante";

        Statement stmt;
        ResultSet rs;

        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int nia = rs.getInt("nia");
                String nombre = rs.getString("nombre");
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();

                System.out.println("NIA: " + nia + " - Nombre: " + nombre + " - Fecha Nacimiento: " + fechaNacimiento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertar(Connection conexion) {
        String query = "INSERT INTO estudiante (nia, nombre, fecha_nacimiento) VALUES ('55555555','David','2001-05-05')";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeQuery(query);
            System.out.println("Fila insertada correctamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Connection conexion) {
        String query = "UPDATE estudiante set nombre='Patri' where nia=12345678";
        Statement stmt;

        try{
            stmt = conexion.createStatement();
            stmt.executeQuery(query);
            System.out.println("Fila actualizada correctamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Connection conexion) {
        String query = "DELETE FROM estudiante WHERE nia=2345678";
        Statement stmt;

        try{
            stmt = conexion.createStatement();
            stmt.executeQuery(query);
            System.out.println("Fila actualizada correctamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}