package componente.servicios.serviciosLocales;

import componente.servicios.FabricaServicios;
import componente.servicios.Servicio;
import componente.servicios.serviciosLocales.servicioInicialLocal.ServicioInicializacionLocal;
import sistema.SistemaLocal;

public class FabricaServiciosLocales implements FabricaServicios{

    private final SistemaLocal sistemaLocal;

    public FabricaServiciosLocales(SistemaLocal sistemaLocal){
        this.sistemaLocal = sistemaLocal;
    }

    @Override public Servicio<?, ?> crearServicioInicializacion(){
        return new ServicioInicializacionLocal(sistemaLocal);
    }


}
