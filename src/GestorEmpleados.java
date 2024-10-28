import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GestorEmpleados {
    private final List<Empleado> empleados;

    public GestorEmpleados() {
        empleados = new ArrayList<>();
        cargarEmpleados();
    }

    // Método para cargar empleados desde la base de datos
    private void cargarEmpleados() {
        String sql = "SELECT * FROM empleados";
        try (Connection conn = DriverManager.getConnection(CrearBaseDatos.URL + "mi_empresa_db", CrearBaseDatos.USER, CrearBaseDatos.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                empleados.add(new Empleado(id, nombre, edad));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar empleados: " + e.getMessage());
        }
    }

    // Método para realizar consultas sobre los empleados
    public void consultarEmpleados(Scanner scanner) {
        System.out.println("¿Qué campos de la tabla desea mostrar?");
        System.out.println("1. ID");
        System.out.println("2. Nombre");
        System.out.println("3. Edad");

        int campo = scanner.nextInt();

        switch (campo) {
            case 1 -> consultarPorId(scanner);
            case 2 -> consultarPorNombre(scanner);
            case 3 -> consultarPorEdad(scanner);
            default -> System.out.println("Opción no válida.");
        }
    }

    private void consultarPorId(Scanner scanner) {
        System.out.print("¿Desea mostrar todos los IDs? (s/n): ");
        char respuesta = scanner.next().charAt(0);

        if (respuesta == 's') {
            System.out.print("¿Desea limitar la cantidad de registros a mostrarse? (s/n): ");
            char limitar = scanner.next().charAt(0);
            if (limitar == 's') {
                System.out.print("¿Cuántos registros desea ver? ");
                int limite = scanner.nextInt();
                empleados.stream()
                        .sorted(Comparator.comparingInt(Empleado::getId))
                        .limit(limite)
                        .forEach(e -> System.out.println("ID: " + e.getId()));
            } else {
                empleados.stream()
                        .sorted(Comparator.comparingInt(Empleado::getId))
                        .forEach(e -> System.out.println("ID: " + e.getId()));
            }
        } else {
            System.out.print("Ingrese el ID que quiere mostrar: ");
            int idBuscar = scanner.nextInt();
            empleados.stream()
                    .filter(e -> e.getId() == idBuscar)
                    .findFirst()
                    .ifPresentOrElse(e -> 
                        System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre() + ", Edad: " + e.getEdad()),
                        () -> System.out.println("No se encontró un empleado con ID " + idBuscar));
        }
    }

    private void consultarPorNombre(Scanner scanner) {
        System.out.print("¿Desea mostrar todos los nombres? (s/n): ");
        char respuesta = scanner.next().charAt(0);

        if (respuesta == 's') {
            System.out.print("¿Desea ordenar de forma ascendente o descendente? (a/d): ");
            char orden = scanner.next().charAt(0);
            
            if (orden == 'a') {
                empleados.stream()
                        .sorted(Comparator.comparing(Empleado::getNombre))
                        .forEach(e -> System.out.println("Nombre: " + e.getNombre()));
            } else if (orden == 'd') {
                empleados.stream()
                        .sorted(Comparator.comparing(Empleado::getNombre).reversed())
                        .forEach(e -> System.out.println("Nombre: " + e.getNombre()));
            }
        } else {
            System.out.print("Ingrese nombre a mostrar: ");
            String nombre = scanner.next();
            empleados.stream()
                    .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                    .findFirst()
                    .ifPresentOrElse(e ->
                            System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre() + ", Edad: " + e.getEdad()),
                            () -> System.out.println("No se encontró un empleado con nombre " + nombre));
        }
    }

    private void consultarPorEdad(Scanner scanner) {
        System.out.print("¿Desea mostrar todas las edades? (s/n): ");
        char respuesta = scanner.next().charAt(0);

        if (respuesta == 's') {
            System.out.print("¿Desea ordenar de forma ascendente o descendente? (a/d): ");
            char orden = scanner.next().charAt(0);
            
            if (orden == 'a') {
                empleados.stream()
                        .sorted(Comparator.comparingInt(Empleado::getEdad))
                        .forEach(e -> System.out.println("Edad: " + e.getEdad()));
            } else if (orden == 'd') {
                empleados.stream()
                        .sorted(Comparator.comparingInt(Empleado::getEdad).reversed())
                        .forEach(e -> System.out.println("Edad: " + e.getEdad()));
            }
        } else {
            System.out.print("Ingrese edad a mostrar: ");
            int edadBuscar = scanner.nextInt();
            empleados.stream()
                    .filter(e -> e.getEdad() == edadBuscar)
                    .findFirst()
                    .ifPresentOrElse(e ->
                            System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre() + ", Edad: " + e.getEdad()),
                            () -> System.out.println("No se encontró un empleado con edad " + edadBuscar));
        }

        // Preguntar si se desea limitar el número de registros mostrados
        System.out.print("¿Desea limitar la cantidad de registros a mostrarse? (s/n): ");
        char limitar = scanner.next().charAt(0);

        if (limitar == 's') {
            System.out.print("¿Cuántos registros desea ver? ");
            int limite = scanner.nextInt();

            empleados.stream()
                    .limit(limite)
                    .forEach(e -> 
                            System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre() + ", Edad: " + e.getEdad()));
        }
    }
}