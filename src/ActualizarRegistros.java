import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarRegistros {
    static final String URL = "jdbc:mysql://localhost:3309/mi_empresa_db"; // URL a la base de datos MySQL
    static final String USER = "root"; // Reemplaza con tu usuario
    static final String PASS = "mariluna"; // Reemplaza con tu contraseña

    public static void actualizar(int id, String nombre, int edad) {
        String sql = "UPDATE empleados SET nombre = ?, edad = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Empleado con ID " + id + " actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}