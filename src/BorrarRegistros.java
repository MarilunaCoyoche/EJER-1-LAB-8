import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrarRegistros {
    static final String URL = "jdbc:mysql://localhost:3309/mi_empresa_db"; // URL a la base de datos MySQL
    static final String USER = "root"; // Reemplaza con tu usuario
    static final String PASS = "mariluna"; // Reemplaza con tu contraseña

    public static void borrar(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Empleado con ID " + id + " borrado exitosamente.");
            } else {
                System.out.println("No se encontró un empleado con ID " + id + ".");
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}