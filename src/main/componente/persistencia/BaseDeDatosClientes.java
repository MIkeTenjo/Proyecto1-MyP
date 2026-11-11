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
import modelo.Cliente;
import sistema.Sistema;

public class BaseDeDatosClientes implements BaseDeDatos {

    private static final String ARCHIVO_CLIENTES = "clientes.dat";
    private Map<String, Cliente> clientes;

    public BaseDeDatosClientes() {
        this.clientes = cargarColeccion(ARCHIVO_CLIENTES);
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
                    "Error al cargar los clientes: " + e.getMessage(), e
            );
        }
    }


    private synchronized void guardarColeccion(String ruta, Object dato) {

        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeObject(dato);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Error al guardar los clientes: " + e.getMessage(), e
            );
        }
    }

    @Override
    public synchronized void guardar(Object obj) {

        if (!(obj instanceof Cliente)) {
            throw new IllegalArgumentException(
                    "El objeto debe ser una instancia de Cliente."
            );
        }

        Cliente cliente = (Cliente) obj;

        String identificador = cliente.getId().toString();

        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El identificador no puede estar vacío."
            );
        }

        clientes.put(identificador, cliente);

        guardarColeccion(ARCHIVO_CLIENTES, clientes);
    }

    @Override
    public Object cargar(String identificador) {
        return clientes.get(identificador);
    }

    @Override
    public synchronized void eliminar(String identificador) {

        if (clientes.remove(identificador) != null) {
            guardarColeccion(ARCHIVO_CLIENTES, clientes);
        }
    }

    @Override
    public boolean existe(String identificador) {
        return clientes.containsKey(identificador);
    }

    @Override
    public synchronized void actualizar(String identificador, Object obj) {

        if (!(obj instanceof Cliente)) {
            throw new IllegalArgumentException(
                    "El objeto debe ser una instancia de Cliente."
            );
        }

        Cliente cliente = (Cliente) obj;

        if (!clientes.containsKey(identificador)) {
            throw new IllegalArgumentException(
                    "No existe un cliente con ese identificador."
            );
        }

        if (!identificador.equals(cliente.getId().toString())) {
            throw new IllegalArgumentException(
                    "El identificador debe coincidir con el ID del cliente."
            );
        }

        clientes.put(identificador, cliente);

        guardarColeccion(ARCHIVO_CLIENTES, clientes);
    }

    @Override public void actualizar(Sistema sistema){
        this.clientes = cargarColeccion(ARCHIVO_CLIENTES);
    }

    @Override public Collection<?> obtenerObjetos(){
        return clientes.values();
    }

}
