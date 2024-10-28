import java.util.Scanner;

public class Main {
    static final String CLAVE_CONFIRMACION = "1234"; // Clave para confirmar cambios

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            CrearBaseDatos.crearTabla();
            
            GestorEmpleados gestorEmpleados = new GestorEmpleados(); // Instancia del gestor

            do {
                System.out.println("\nMenú:");
                System.out.println("1. Insertar Registro");
                System.out.println("2. Mostrar Registros");
                System.out.println("3. Actualizar Registro");
                System.out.println("4. Borrar Registro");
                System.out.println("5. Consultar Registros"); // Nueva opción para consultas
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> { // Insertar Registro
                        System.out.print("Ingrese el nombre: ");
                        String nombre = scanner.next();
                        System.out.print("Ingrese la edad: ");
                        int edad = scanner.nextInt();
                        InsertarRegistros.insertar(nombre, edad);
                    }
                    case 2 -> RecuperarRegistros.recuperar(); // Mostrar Registros
                    case 3 -> { // Actualizar Registro
                        actualizarRegistro(scanner);
                    }
                    case 4 -> { // Borrar Registro
                        borrarRegistro(scanner);
                    }
                    case 5 -> { // Consultar Registros
                        gestorEmpleados.consultarEmpleados(scanner);
                    }
                    case 6 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } while (opcion != 6); // Cambiar a opción 6 para salir
        }
    }

    private static void actualizarRegistro(Scanner scanner) {
        System.out.print("Ingrese el ID del registro a actualizar: ");
        int idActualizar = scanner.nextInt();
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = scanner.next();
        System.out.print("Ingrese la nueva edad: ");
        int nuevaEdad = scanner.nextInt();

        // Confirmación de cambios
        if (confirmarCambios(scanner)) {
            ActualizarRegistros.actualizar(idActualizar, nuevoNombre, nuevaEdad);
        } else {
            System.out.println("Actualización cancelada.");
        }
    }

    private static void borrarRegistro(Scanner scanner) {
        System.out.print("Ingrese el ID del registro a borrar: ");
        int idBorrar = scanner.nextInt();

        // Confirmación de cambios
        if (confirmarCambios(scanner)) {
            BorrarRegistros.borrar(idBorrar);
        } else {
            System.out.println("Borrado cancelado.");
        }
    }

    private static boolean confirmarCambios(Scanner scanner) {
      	System.out.print("Ingrese la clave para confirmar los cambios: ");
      	String claveIngresada = scanner.next();
      	return CLAVE_CONFIRMACION.equals(claveIngresada);
  	}
}