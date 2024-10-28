import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecuperarRegistros {
    static final String URL = "jdbc:mysql://localhost:3309/mi_empresa_db"; // URL a la base de datos MySQL
    static final String USER = "root"; // Reemplaza con tu usuario
    static final String PASS = "mariluna"; // Reemplaza con tu contraseña

    public static void recuperar() {
        String sql = "SELECT * FROM empleados";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de empleados:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Edad: " + rs.getInt("edad"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}