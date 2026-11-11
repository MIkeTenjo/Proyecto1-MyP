package componente.servicios;

import sistema.SistemaLocal;

public class FabricaServiciosLocales implements FabricaServicios{

    private final SistemaLocal sistemaLocal;

    public FabricaServiciosLocales(SistemaLocal sistemaLocal){
        this.sistemaLocal = sistemaLocal;
    }

    @Override public Servicio<?, ?> crearServicioInicializacion(){
        return new ServicioInicializacionLocal(this.sistemLocal);
    }


}
