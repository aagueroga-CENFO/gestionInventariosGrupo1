import java.util.ArrayList;

public class ListaProductos {

    // Atributos
    private NodoProducto primero;

    // Métodos

    // Constructor
    public ListaProductos() {
        primero = null;
    }

    // Getters y Setters
    public NodoProducto getPrimero() {
        return primero;
    }

    public void setPrimero(NodoProducto primero) {
        this.primero = primero;
    }

    // Operaciones
    public boolean estaVacia() {
        return primero == null;
    }

    // Inserción al inicio
    public void insertarInicio(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        NodoProducto nodo = new NodoProducto(nombre, precio, categoria, fechaVencimiento, cantidad);
        nodo.setSiguiente(primero);
        setPrimero(nodo);
    }

    // Inserción al final
    public void insertarFin(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        NodoProducto nodo = new NodoProducto(nombre, precio, categoria, fechaVencimiento, cantidad);

        if (estaVacia()) {
            setPrimero(nodo);
            return;
        }

        NodoProducto actual = primero;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nodo);
    }

    // Búsqueda secuencial por nombre
    public NodoProducto buscar(String nombre) {
        if (estaVacia() || nombre == null) {
            return null;
        }

        NodoProducto temp = primero;
        while (temp != null) {
            if (nombre.equalsIgnoreCase(temp.getNombre())) {
                return temp;
            }
            temp = temp.getSiguiente();
        }

        return null;
    }

    // Modificación de producto existente
    public boolean modificarProducto(String nombreActual, double nuevoPrecio, String nuevaCategoria, String nuevaFecha, int nuevaCantidad) {
        NodoProducto producto = buscar(nombreActual);
        if (producto == null) {
            System.out.println("No se encontró el producto a modificar.\n");
            return false;
        }

        producto.setPrecio(nuevoPrecio);
        producto.setCategoria(nuevaCategoria);
        producto.setFechaVencimiento(nuevaFecha);
        producto.setCantidad(nuevaCantidad);
        System.out.println("Producto modificado correctamente.\n");
        return true;
    }

    // Agregar imagen a la lista de un producto
    public boolean agregarImagenAProducto(String nombre, String rutaImagen) {
        NodoProducto producto = buscar(nombre);
        if (producto == null) {
            System.out.println("No se encontró el producto para adjuntar la imagen.\n");
            return false;
        }

        producto.getListaImagenes().add(rutaImagen);
        System.out.println("Imagen agregada exitosamente al producto.\n");
        return true;
    }

    // Eliminación de un producto por nombre
    public NodoProducto eliminar(String nombre) {
        if (estaVacia() || nombre == null) {
            System.out.println("La lista está vacía. No hay elementos para eliminar.\n");
            return null;
        }

        // El producto a eliminar es la cabeza
        if (nombre.equalsIgnoreCase(primero.getNombre())) {
            NodoProducto eliminado = primero;
            setPrimero(primero.getSiguiente());
            eliminado.setSiguiente(null);
            System.out.println("Se eliminó el producto: " + nombre + "\n");
            return eliminado;
        }

        // El producto está en el cuerpo o final
        NodoProducto anterior = primero;
        NodoProducto temp = primero.getSiguiente();

        while (temp != null && !nombre.equalsIgnoreCase(temp.getNombre())) {
            anterior = temp;
            temp = temp.getSiguiente();
        }

        if (temp != null) {
            anterior.setSiguiente(temp.getSiguiente());
            temp.setSiguiente(null);
            System.out.println("Se eliminó el producto: " + nombre + "\n");
            return temp;
        } else {
            System.out.println("El producto '" + nombre + "' no fue encontrado en la lista.\n");
            return null;
        }
    }

    // Reporte de costos totales individuales y acumulados
    public void mostrarReporteCostos() {
        if (estaVacia()) {
            System.out.println("No hay productos en inventario para generar el reporte.\n");
            return;
        }

        NodoProducto actual = primero;
        double costoTotalAcumulado = 0.0;
        int contador = 1;

        System.out.println("================================================================================");
        System.out.println("                   REPORTE DE COSTOS DE INVENTARIO                              ");
        System.out.println("================================================================================");

        while (actual != null) {
            double costoSubtotal = actual.getPrecio() * actual.getCantidad();
            costoTotalAcumulado += costoSubtotal;

            System.out.println("Producto #" + contador + ": " + actual.getNombre());
            System.out.println("   Categoría: " + actual.getCategoria());
            System.out.println("   Vencimiento: " + actual.getFechaVencimiento());
            System.out.println("   Cantidad en inventario: " + actual.getCantidad());
            System.out.println("   Precio unitario: ₡" + actual.getPrecio());
            System.out.println("   Costo total del producto: ₡" + costoSubtotal);
            System.out.println("   Imágenes asociadas: " + actual.getListaImagenes().toString());
            System.out.println("--------------------------------------------------------------------------------");

            actual = actual.getSiguiente();
            contador++;
        }

        System.out.println("COSTO TOTAL ACUMULADO DEL INVENTARIO: ₡" + costoTotalAcumulado);
        System.out.println("================================================================================\n");
    }

    // Mostrar lista
    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista de productos está vacía.\n");
            return;
        }

        NodoProducto aux = primero;
        int indice = 1;

        System.out.println("========================================");
        System.out.println("      CATÁLOGO DE PRODUCTOS             ");
        System.out.println("========================================");

        while (aux != null) {
            System.out.println("Producto [" + indice + "]:" + aux.toString());
            aux = aux.getSiguiente();
            indice++;
        }

        System.out.println("========================================\n");
    }

    // Clase Nodo
    public class NodoProducto {

        // Atributos
        private String nombre;
        private double precio;
        private String categoria;
        private String fechaVencimiento;
        private int cantidad;
        private ArrayList<String> listaImagenes;
        private NodoProducto siguiente;

        // Métodos
        // Constructor
        public NodoProducto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
            this.nombre = nombre;
            this.precio = precio;
            this.categoria = categoria;
            this.fechaVencimiento = fechaVencimiento;
            this.cantidad = cantidad;
            this.listaImagenes = new ArrayList<>();
            this.siguiente = null;
        }

        // Getters
        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public String getCategoria() {
            return categoria;
        }

        public String getFechaVencimiento() {
            return fechaVencimiento;
        }

        public int getCantidad() {
            return cantidad;
        }

        public ArrayList<String> getListaImagenes() {
            return listaImagenes;
        }

        public NodoProducto getSiguiente() {
            return siguiente;
        }

        // Setters
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public void setFechaVencimiento(String fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public void setListaImagenes(ArrayList<String> listaImagenes) {
            this.listaImagenes = listaImagenes;
        }

        public void setSiguiente(NodoProducto siguiente) {
            this.siguiente = siguiente;
        }

        // toString()
        @Override
        public String toString() {
            return "\nNombre: " + nombre +
                    "\nPrecio: ₡" + precio +
                    "\nCategoría: " + categoria +
                    "\nFecha de Vencimiento: " + fechaVencimiento +
                    "\nCantidad: " + cantidad +
                    "\nImágenes: " + listaImagenes.toString() + "\n";
        }
    }
}
