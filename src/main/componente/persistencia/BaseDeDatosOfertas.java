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
import modelo.Oferta;
import sistema.Sistema;

public class BaseDeDatosOfertas implements BaseDeDatos{

    private static final String ARCHIVO_OFERTAS = "ofertas.dat";
    private Map<String, Oferta> ofertas;

    public BaseDeDatosOfertas() {
        this.ofertas = cargarColeccion(ARCHIVO_OFERTAS);
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
                    "Error al cargar los ofertas: " + e.getMessage(), e
            );
        }
    }


    private synchronized void guardarColeccion(String ruta, Object dato) {

        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeObject(dato);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Error al guardar los ofertas: " + e.getMessage(), e
            );
        }
    }

    @Override
    public synchronized void guardar(Object obj) {

        if (!(obj instanceof Oferta)) {
            throw new IllegalArgumentException(
                    "El objeto debe ser una instancia de Oferta."
            );
        }

        Oferta oferta = (Oferta) obj;

        String identificador = oferta.getIdOferta();

        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El identificador no puede estar vacío."
            );
        }

        ofertas.put(identificador, oferta);

        guardarColeccion(ARCHIVO_OFERTAS, ofertas);
    }

    @Override
    public Object cargar(String identificador) {
        return ofertas.get(identificador);
    }

    @Override
    public synchronized void eliminar(String identificador) {

        if (ofertas.remove(identificador) != null) {
            guardarColeccion(ARCHIVO_OFERTAS, ofertas);
        }
    }

    @Override
    public boolean existe(String identificador) {
        return ofertas.containsKey(identificador);
    }

    @Override
    public synchronized void actualizar(String identificador, Object obj) {

        if (!(obj instanceof Oferta)) {
            throw new IllegalArgumentException(
                    "El objeto debe ser una instancia de Oferta."
            );
        }

        Oferta oferta = (Oferta) obj;

        if (!ofertas.containsKey(identificador)) {
            throw new IllegalArgumentException(
                    "No existe una Oferta con ese identificador."
            );
        }

        if (!identificador.equals(oferta.getIdOferta())) {
            throw new IllegalArgumentException(
                    "El identificador debe coincidir con el ID de la Oferta."
            );
        }

        ofertas.put(identificador, oferta);

        guardarColeccion(ARCHIVO_OFERTAS, ofertas);
    }

    @Override
    public Collection<?> obtenerObjetos() {
        return ofertas.values();
    }

    @Override
    public void actualizar(Sistema sistema) {
        this.ofertas = cargarColeccion(ARCHIVO_OFERTAS);
    }

}
