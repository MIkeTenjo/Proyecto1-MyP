package componente.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import modelo.Producto;
import sistema.Sistema;

public class BaseDeDatosProductos implements BaseDeDatos {

    private static final String ARCHIVO_PRODUCTOS = "productos.dat";
    private Map<String, Producto> productos;

    public BaseDeDatosProductos() {
        this.productos = cargarColeccion(ARCHIVO_PRODUCTOS);
    }


    @SuppressWarnings("unchecked")
    private <K, V> Map<K, V> cargarColeccion(String ruta) {

        File archivo = new File(ruta);

        if (!archivo.exists()) {
            return new ConcurrentHashMap<>();
        }

        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(archivo))) {

            Object objeto = ois.readObject();

            if (!(objeto instanceof Map)) {
                throw new IOException("El archivo no contiene una colección válida.");
            }

            return new ConcurrentHashMap<>((Map<K, V>) objeto);

        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException(
                    "Error al cargar los productos: " + e.getMessage(), e
            );
        }
    }


    private synchronized void guardarColeccion(String ruta, Object dato) {

        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeObject(dato);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Error al guardar los productos: " + e.getMessage(), e
            );
        }
    }

    @Override
    public synchronized void guardar(Object obj) {

        if (!(obj instanceof Producto)) {
            throw new IllegalArgumentException(
                    "El objeto debe ser una instancia de Producto."
            );
        }

        Producto producto = (Producto) obj;

        String codigo = producto.getCodigoBarras();

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El código de barras no puede estar vacío."
            );
        }

        productos.put(codigo, producto);

        guardarColeccion(ARCHIVO_PRODUCTOS, productos);
    }

    @Override
    public Object cargar(String identificador) {
        return productos.get(identificador);
    }

    @Override
    public synchronized void eliminar(String identificador) {

        if (productos.remove(identificador) != null) {
            guardarColeccion(ARCHIVO_PRODUCTOS, productos);
        }
    }

    @Override
    public boolean existe(String identificador) {
        return productos.containsKey(identificador);
    }

    @Override
    public synchronized void actualizar(String identificador, Object obj) {

        if (!(obj instanceof Producto)) {
            throw new IllegalArgumentException(
                    "El objeto debe ser una instancia de Producto."
            );
        }

        Producto producto = (Producto) obj;

        if (!productos.containsKey(identificador)) {
            throw new IllegalArgumentException(
                    "No existe un producto con ese identificador."
            );
        }

        if (!identificador.equals(producto.getCodigoBarras())) {
            throw new IllegalArgumentException(
                    "El identificador debe coincidir con el código de barras."
            );
        }

        productos.put(identificador, producto);

        guardarColeccion(ARCHIVO_PRODUCTOS, productos);
    }

    @Override
    public void actualizar(Sistema sistema) {
        this.productos = cargarColeccion(ARCHIVO_PRODUCTOS);
    }

    @Override
    public Collection<?> obtenerObjetos() {
        return productos.values();
    }

}
