package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Clase que representa una cuenta bancaria asociada a un cliente. 
 * Cada cuenta bancaria tiene un número de cuenta único y un saldo. Esta clase es
 * solo administra el saldo de la cuenta y permite realizar operaciones de depósito y retiro.
 * por lo que no tiene acciones elevadas más que en la compra a través de la tienda.
 * CuentaBancaria es serializable para permitir su transmisión a través de la red.
 */
public class CuentaBancaria implements Serializable {

    /**
     * Número de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    private final String numeroCuenta;
    
    private BigDecimal saldo;

    /**
     * Constructor de la clase CuentaBancaria.
     * @param numeroCuenta El número de cuenta único.
     * @param saldoInicial El saldo inicial de la cuenta. Utiliza {@link BigDecimal} 
     * para evitar problemas de precisión con los números decimales.
     */
    public CuentaBancaria(String numeroCuenta, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    /**
     * Obtiene el número de cuenta único.
     * @return El número de cuenta único.
     */
    public String getNumeroCuenta(){
        return numeroCuenta;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     * @return El saldo actual de la cuenta.
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * Deposita una cantidad en la cuenta bancaria.
     * @param cantidad La cantidad a depositar.
     * @throws IllegalArgumentException si la cantidad es menor o igual a cero.
     */
    public void depositar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(cantidad);
        } else {
            throw new IllegalArgumentException("La cantidad a depositar debe ser mayor que cero.");
        }
    }

    /**
     * Retira una cantidad de la cuenta bancaria.
     * @param cantidad La cantidad a retirar.
     * @throws IllegalArgumentException si la cantidad es menor o igual a cero 
     * o si no hay suficiente saldo.
     */
    public void retirar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            if (saldo.compareTo(cantidad) >= 0) {
                saldo = saldo.subtract(cantidad);
            } else {
                throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro.");
            }
        } else {
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor que cero.");
        }
    }

}
