package modelo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa una oferta de descuento en un producto específico.
 * Cada oferta tiene un identificador único, un producto asociado, un país donde es aplicable
 * la oferta, un porcentaje de descuento, una fecha de inicio y una fecha de fin.
 * Oferta es serializable para permitir su transmisión a través de la red.
 */
public class Oferta implements Serializable {

    /**
     * Número de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    private final String idOferta;

    private final Producto producto;

    private final Pais paisAplicable;

    /**
     * Porcentaje de descuento aplicado al producto durante la oferta.
     * Utiliza {@link BigDecimal} para representar valores decimales con precisión.
     */
    private final BigDecimal porcentajeDescuento;

    /**
     * Fecha de inicio de la oferta. Utiliza {@link LocalDate} para representar 
     * la fecha sin información de tiempo.
     */
    private final LocalDate fechaInicio;

    /**
     * Fecha de finalización de la oferta. Utiliza {@link LocalDate} para representar 
     * la fecha sin información de tiempo.
     */
    private final LocalDate fechaFin;

    private boolean activa;

    /**
     * Constructor de la clase Oferta.
     * Inicializa una nueva oferta con la información proporcionada. Los parámetros son
     * validados contra valores nulos para garantizar la integridad de la oferta.
     * @param idOferta Identificador único de la oferta.
     * @param producto El producto al que se aplica la oferta.
     * @param paisAplicable El país donde la oferta es válida.
     * @param porcentajeDescuento El porcentaje de descuento aplicado al producto.
     * @param fechaInicio La fecha de inicio de la oferta.
     * @param fechaFin La fecha de finalización de la oferta.
     * @throws NullPointerException Si alguno de los parámetros es nulo.
     * @throws IllegalArgumentException Si el identificador está vacío, el porcentaje de descuento
     * no está en el rango válido (0-100), o la fecha de finalización es anterior a la fecha de inicio.
     */
    public Oferta(String idOferta,Producto producto,Pais paisAplicable,
                  BigDecimal porcentajeDescuento, LocalDate fechaInicio, LocalDate fechaFin) {

        this.idOferta = Objects.requireNonNull(idOferta);
        this.producto = Objects.requireNonNull(producto);
        this.paisAplicable = Objects.requireNonNull(paisAplicable);
        this.porcentajeDescuento =
                Objects.requireNonNull(porcentajeDescuento);

        this.fechaInicio = Objects.requireNonNull(fechaInicio);
        this.fechaFin = Objects.requireNonNull(fechaFin);

        if (idOferta.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El identificador no puede estar vacío.");
        }

        if (porcentajeDescuento.compareTo(BigDecimal.ZERO) <= 0
                || porcentajeDescuento.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException(
                    "El descuento debe ser mayor que 0 y menor o igual a 100.");
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException(
                    "La fecha de finalización no puede ser anterior al inicio.");
        }

        this.activa = true;
    }

    /**
     * Obtiene el identificador único de la oferta.
     * @return El identificador único de la oferta.
     */
    public String getIdOferta() {
        return idOferta;
    }

    /**
     * Obtiene el producto asociado a la oferta.
     * @return El productos asociado a la oferta.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene el Pais al que es aplicado la oferta nacionalmente.
     * @return El Pais asociado a la oferta.
     */
    public Pais getPaisAplicable() {
        return paisAplicable;
    }

    /**
     * Obtiene el porcentaje de descuento de la oferta
     * representado en un objeto {@link BidDecimal} para evitar 
     * problemas con decimales.
     * @return El porcentaje de descuento de la oferta.
     */
    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * Obtiene la fecha de inicio en el que empezo a darse
     * la oferta.
     * @return La fecha de inicio en el que empezo a darse la oferta.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Obtiene la fecha de finalización de la oferta.
     * @return La fecha de finalización que tiene la oferta.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Retorna un valor booleano que nos diga si la oferta sigue
     * activa o no.
     * @return Un valor verdadero si la oferta sigue activa. Un valor
     * falso si la oferta no esta activa.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Activa la oferta para los usuarios de la región.
     */
    public void activar() {
        this.activa = true;
    }

    /**
     * Desactiva la oferta  para los usuarios de la región.
     */
    public void desactivar() {
        this.activa = false;
    }

    /**
     * Nos dice si la oferta todavía es vigente en el sistema.
     * @return Un valor verdadero si la oferta esta vigente. 
     * Un valor falso en otro caso.
     */
    public boolean estaVigente(LocalDate fecha) {
        return activa
                && !fecha.isBefore(fechaInicio)
                && !fecha.isAfter(fechaFin);
    }

    /**
     * Obtiene el precio de la oferta del producto.
     * @return El precio de la oferta del producto.
     */
    public BigDecimal calcularPrecioOferta() {
        BigDecimal descuento = porcentajeDescuento
                .divide(new BigDecimal("100"));

        return producto.getPrecio()
                .multiply(BigDecimal.ONE.subtract(descuento))
                .setScale(2, RoundingMode.HALF_UP);
    }

}

