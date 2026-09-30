import java.util.Scanner;

public class Main {


    private static ListaProductos inventario = new ListaProductos();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Carga inicial de datos
        cargarDatosPrueba();


        menu();

        scanner.close();
    }

    // Menu
    public static void menu() {
        int opcion = 0;

        do {
            System.out.println("==================================================");
            System.out.println("   SISTEMA DE GESTION DE INVENTARIOS - Grupo 1  ");
            System.out.println("==================================================");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Modificar producto existente");
            System.out.println("5. Agregar ruta de imagen a un producto");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Generar reporte de costos de inventario");
            System.out.println("8. Mostrar catalogo completo");
            System.out.println("9. Salir del sistema");
            System.out.println("==================================================");
            System.out.print("Seleccione una opcion: ");


            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\n[Error]: Debe ingresar un numero valido.\n");
                continue;
            }

            System.out.println();

            switch (opcion) {
                case 1:
                    registrarProducto(true);
                    break;
                case 2:
                    registrarProducto(false);
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    modificarProducto();
                    break;
                case 5:
                    agregarImagenProducto();
                    break;
                case 6:
                    eliminarProducto();
                    break;
                case 7:
                    inventario.mostrarReporteCostos();
                    break;
                case 8:
                    inventario.mostrarLista();
                    break;
                case 9:
                    System.out.println("Te esperamos de nuevo ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.\n");
                    break;
            }

        } while (opcion != 9);
    }

    // Captura datos producto
    private static void registrarProducto(boolean alInicio) {
        System.out.println(alInicio ? "--- INSERTAR AL INICIO ---" : "--- INSERTAR AL FINAL ---");

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Precio unitario (en colones): ");
        double precio = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine().trim();

        System.out.print("Fecha de vencimiento (o 'N/A' si no aplica): ");
        String fechaVencimiento = scanner.nextLine().trim();

        System.out.print("Cantidad en inventario: ");
        int cantidad = Integer.parseInt(scanner.nextLine().trim());

        if (alInicio) {
            inventario.insertarInicio(nombre, precio, categoria, fechaVencimiento, cantidad);
        } else {
            inventario.insertarFin(nombre, precio, categoria, fechaVencimiento, cantidad);
        }

        System.out.println("Producto registrado con exito.\n");
    }

    // Buscar procucto
    private static void buscarProducto() {
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombre = scanner.nextLine().trim();

        ListaProductos.NodoProducto resultado = inventario.buscar(nombre);
        if (resultado != null) {
            System.out.println("\nProducto encontrado:" + resultado.toString());
        } else {
            System.out.println("\nEl producto '" + nombre + "' no existe en el inventario.\n");
        }
    }

    // Modificar producto
    private static void modificarProducto() {
        System.out.print("Ingrese el nombre del producto que desea modificar: ");
        String nombre = scanner.nextLine().trim();

        ListaProductos.NodoProducto actual = inventario.buscar(nombre);
        if (actual == null) {
            System.out.println("El producto no existe. Operacion cancelada.\n");
            return;
        }

        System.out.println("Ingrese los nuevos valores para el producto:");
        System.out.print("Nuevo precio: ");
        double nuevoPrecio = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Nueva categoria: ");
        String nuevaCategoria = scanner.nextLine().trim();

        System.out.print("Nueva fecha de vencimiento: ");
        String nuevaFecha = scanner.nextLine().trim();

        System.out.print("Nueva cantidad: ");
        int nuevaCantidad = Integer.parseInt(scanner.nextLine().trim());

        inventario.modificarProducto(nombre, nuevoPrecio, nuevaCategoria, nuevaFecha, nuevaCantidad);
    }

    // Agregar una ruta a la imagen
    private static void agregarImagenProducto() {
        System.out.print("Ingrese el nombre del producto al cual desea asociar una imagen: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese la ruta relativa de la imagen (ej: imagenes/leche.png): ");
        String ruta = scanner.nextLine().trim();

        inventario.agregarImagenAProducto(nombre, ruta);
    }

    // Eliminar
    private static void eliminarProducto() {
        System.out.print("Ingrese el nombre del producto que desea eliminar: ");
        String nombre = scanner.nextLine().trim();

        inventario.eliminar(nombre);
    }

    // datos de prueba
    private static void cargarDatosPrueba() {
        inventario.insertarFin("Leche Semidescremada", 950.0, "Lacteos", "2026-10-15", 40);
        inventario.insertarFin("Arroz Grano Entero", 1350.0, "Granos", "2027-01-20", 25);
        inventario.insertarInicio("Manzanas Gala", 450.0, "Frutas", "2026-10-05", 80);

        // estas son rutas de prueba con imagenes que existen
        inventario.agregarImagenAProducto("Leche Semidescremada", "imagenes/leche.png");
        inventario.agregarImagenAProducto("Arroz Grano Entero", "imagenes/arroz.jpg");
    }
}