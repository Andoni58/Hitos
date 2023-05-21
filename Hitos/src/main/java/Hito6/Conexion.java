package Hito6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Conexion {
    static PreparedStatement ps;
    static Connection conn;
    static Statement stat;
    static ResultSet rs;

    public Conexion() {}
    public void AbrirConexion() {
        String usuario = "root";
        String contraseña = "root";
        String url = "jdbc:mysql://localhost:3306/hito";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Error conexion: " + e.getMessage());
        }
    }


    public ArrayList tenerFotografos() {
        ArrayList<String> matrizFotografos = new ArrayList<>();
        try {
            AbrirConexion();
            String sql = "SELECT nombre FROM fotografos";
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                matrizFotografos.add(nombre);
            }
            rs.close();
            stat.close();
        } catch (Exception e) {
            System.err.println("Error fotógrafos: " + e.getMessage());
        }
        return matrizFotografos;
    }


    public ArrayList tenerTitulos(String real) {
        ArrayList<String> matrizTitulos = new ArrayList<>();
        try {
            AbrirConexion();
            String sql = "SELECT titulo FROM fotos WHERE idfotografo in (SELECT idfotografo FROM fotografos WHERE nombre = ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, real);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("titulo");
                matrizTitulos.add(nombre);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println("Error títulos: " + e.getMessage());
        }
        return matrizTitulos;
    }

    public ArrayList tenerArchivos(String real) {
        ArrayList<String> matrizArchivos = new ArrayList<>();
        try {
            AbrirConexion();
            String sql = "SELECT archivo FROM fotos WHERE idfotografo in (SELECT idfotografo FROM fotografos WHERE nombre = ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,real);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("archivo");
                matrizArchivos .add(nombre);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println("Error archivos: " + e.getMessage());
        }
        return matrizArchivos;
    }

    public ArrayList tener(String real, Date fecha) {
        ArrayList<String> matrizTener = new ArrayList<>();
        try {
            AbrirConexion();
            String sql = "SELECT titulo FROM fotos where idfotografo in(select idfotografo from fotografos where nombre = ?) and fecha = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, real);
            java.util.Date fechaUtil = new java.util.Date();
            java.sql.Date fechaBD = new java.sql.Date(fechaUtil.getTime());
            ps.setDate(2, new java.sql.Date(fecha.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("titulo");
                matrizTener .add(nombre);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
        return matrizTener;
    }

    public void Mas(String titulo ){
        try {
            AbrirConexion();
            String sql = "UPDATE fotos SET visitas = visitas + 1 WHERE titulo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, titulo);
            int ac = ps.executeUpdate();
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}