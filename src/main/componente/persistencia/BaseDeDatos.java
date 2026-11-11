package componente.persistencia;

import java.util.Collection;

import componente.Componente;
public interface BaseDeDatos extends Componente{

    void guardar(Object obj);
    Object cargar(String identificador);
    void eliminar(String identificador);
    boolean existe(String identificador);
    void actualizar(String identificador, Object obj);
    Collection<?> obtenerObjetos();

}
