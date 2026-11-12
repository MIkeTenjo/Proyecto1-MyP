package componente.servicios;

public class Resultado{

    public enum Estado {
        EXITO,
        ADVERTENCIA,        // Falla menor, el sistema puede seguir
        ERROR_RECUPERABLE,  // Requiere llamar al Servicio de Mantenimiento
        ERROR_FATAL         // Imposible continuar, rompe la aplicación
    }

    private final Estado estado;
    private final String mensaje;
    private final Exception excepcion;

    private Resultado(Estado estado, String mensaje, Exception excepcionAsociada) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.excepcion = excepcionAsociada;
    }

    public static Resultado ok(String mensaje) {
        return new Resultado(Estado.EXITO, mensaje, null);
    }

    public static Resultado errorRecuperable(String mensaje, Exception e) {
        return new Resultado(Estado.ERROR_RECUPERABLE, mensaje, e);
    }

    public static Resultado fatal(String mensaje, Exception e) {
        return new Resultado(Estado.ERROR_FATAL, mensaje, e);
    }

    public Estado getestado() { 
        return estado; 
    }
    public String getMensaje() { 
        return mensaje; 
    }
    public Exception getExcepcion() { 
        return excepcion; 
    }
}
