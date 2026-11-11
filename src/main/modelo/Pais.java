package modelo;

/**
 * Enum que representa los países disponibles en el sistema.
 * Pais tiene un nombre y un código asociado para diferentes funcionalidades del sistema.
 */
public enum Pais {

    MEXICO("México", "MX"),
    ESTADOS_UNIDOS("Estados Unidos", "US"),
    ITALIA("Italia", "IT");

    private final String nombre;

    private final String codigo;

    /**
     * Constructor privado para inicializar los atributos de cada país.
     * @param nombre El nombre del país.
     * @param codigo El código único del país.
     */
    private Pais(String nombre, String codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del país.
     * @return El nombre del país.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el código único del país.
     * @return El código único del país.
     */
    public String getCodigo() {
        return codigo;
    }
}
