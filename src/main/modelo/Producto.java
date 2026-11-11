package modelo;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Clase que representa un producto en la tienda. Cada producto tiene un
 * código de barras único, un nombre, un departamento al que pertenece,
 * un precio y una cantidad disponible en inventario.
 * Producto encapsula la información esencial de un árticulo que se puede comprar
 * en la tienda al cliente. Esta clase es serializable para permitir su transmisión a través de la red.
 */
public class Producto implements Serializable {

    /**
     * Número de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    private final String codigoBarras;

    private final String nombre;

    private final Departamento departamento;

    private final BigDecimal precio;

    private int cantidad;

    /**
     * Constructor de la clase Producto para un encapsulamiento completo de la información del producto
     * que nos servirá para funciones esenciales de la tienda.
     * @param codigoBarras El código de barras único del producto.
     * @param nombre El nombre del producto.
     * @param departamento El departamento al que pertenece el producto.
     * @param precio El precio del producto. Se utiliza BigDecimal para evitar problemas de precisión con los números decimales.
     * @param cantidad La cantidad disponible en inventario.
     */
    public Producto(String codigoBarras, String nombre, Departamento departamento, BigDecimal precio, int cantidad) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.departamento = departamento;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el código de barras únicodel producto.
     * @return El código de barras del producto.
     */
    public String getCodigoBarras(){
        return codigoBarras;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el departamento al que pertenece el producto.
     * @return El departamento del producto.
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad disponible en inventario.
     * @return La cantidad disponible en inventario.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad disponible en inventario.
     * @param cantidad La nueva cantidad disponible en inventario.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
