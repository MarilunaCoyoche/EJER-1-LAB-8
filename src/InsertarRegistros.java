import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarRegistros {
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos"; // Cambia esto por tu URL
    private static final String USER = "tu_usuario"; // Cambia esto por tu usuario
    private static final String PASSWORD = "tu_contraseña"; // Cambia esto por tu contraseña

    public static int insertar(String nombre, int edad) {
        String sql = "INSERT INTO empleados (nombre, edad) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.executeUpdate();

            // Obtener el ID generado
            try (var generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retornar el ID del nuevo registro
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el registro: " + e.getMessage());
        }
        return -1; // Retornar -1 si hubo un error
    }
}
