package componente.servicios;

import componente.Componente;

public interface Servicio<E, S> extends Componente {

    S procesar(E entrada) throws Exception;

    boolean estaDisponible();

}
