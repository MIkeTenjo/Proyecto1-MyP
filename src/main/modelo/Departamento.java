package modelo;

/**
 * Enumeración que representa los diferentes departamentos de productos disponibles en la tienda.
 * Cada departamento tiene un nombre y un código único asociado.
 * Departamento solo distingue los diferentes tipos de productos que se pueden encontrar en la tienda.
 */
public enum Departamento {

    ELECTRONICA("Electrónica", "0157"),
    ROPA_Y_MODA("Ropa y Moda", "0347"),
    CALZADO("Calzado", "7456"),
    HOGAR_Y_MUEBLES("Hogar y Muebles", "1331"),
    ALIMENTOS_Y_BEBIDAS("Alimentos y Bebidas", "9876"),
    BELLEZA_Y_CUIDADO_PERSONAL("Belleza y Cuidado Personal", "4567"),
    DEPORTES("Deportes", "1010"),
    JUGUETERIA("Juguetería", "7771"),
    AUTOMOTRIZ("Automotriz", "1478"),
    PAPELERIA_Y_OFICINA("Papelería y Oficina", "5555"),
    MASCOTAS("Mascotas", "2684"),
    SALUD_Y_BIENESTAS("Salud y Bienestar", "0001"),
    VIDEOJUEGOS("Videojuegos", "9999"),
    LIBROS_Y_REVISTAS("Libros y Revistas", "0880"),
    JARDINERIA("Jardinería", "1117"),
    OTROS("Otros", "0000");

    private final String nombre;

    private final String codigo;

    /**
     * Constructor privado para inicializar los atributos de cada departamento.
     * @param nombre El nombre del departamento.
     * @param codigo El código único del departamento.
     */
    private Departamento(String nombre, String codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del departamento.
     * @return El nombre del departamento.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene el código único del departamento.
     * @return El código único del departamento.
     */
    public String getCodigo() {
        return codigo;
    }

}
