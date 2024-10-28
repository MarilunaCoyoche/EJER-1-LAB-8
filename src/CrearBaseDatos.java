import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBaseDatos {
    static final String URL = "jdbc:mysql://localhost:3309/"; // Cambia el puerto si es necesario
    static final String USER = "root"; // Reemplaza con tu usuario
    static final String PASS = "mariluna"; // Reemplaza con tu contraseña

    public static void crearTabla() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS mi_empresa_db;");
            stmt.executeUpdate("USE mi_empresa_db;");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS empleados (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100), edad INT);");
            System.out.println("Base de datos 'mi_empresa_db' y Tabla 'empleados' creada exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
