package modelo;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

/**
 * Clase que representa un cliente que se manejará en el sistema de CheemsMart
 * como una cuenta de usuario para realizar compras y acceder a los servicios de la
 * tienda. Cada cliente tiene un identificador único, un nombre de usuario, un código de acceso,
 * información de contacto, una cuenta bancaria asociada, un Pais de pertenencia y
 * preferencias de idioma.
 * Cliente Es serializable para permitir su transmisión a través de la red.
 */
public class Cliente implements Serializable {
    /**
     * Número de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del cliente. Utiliza {@link UUID} para garantizar la unicidad.
     */
    private final UUID id;

    /**
     * Nombre de usuario del cliente. El nombre de usuario
     * es único y es diferente al nombre real del cliente.
     */
    private final String usuario;

    /**
     * Contraseña o código de acceso del cliente.
     */
    private final String codigoAcceso;

    private final String nombre;

    private final String telefono;

    private final String direccion;

    private CuentaBancaria cuentaBancaria;

    private final Pais pais;

    /**
     * Idioma preferido del cliente para la interfaz de usuario y la comunicación.
     * Se utiliza la clase {@link Locale} para representar el idioma y la región.
     */
    private Locale idiomaPreferido;

    /**
     * Constructor de la clase Cliente.
     * Inicializa un nuevo cliente con la información proporcionada.
     * @param usuario El nombre de usuario del cliente.
     * @param codigoAcceso El código de acceso o contraseña del cliente.
     * @param nombre El nombre real del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param direccion La dirección física del cliente.
     * @param idiomaPreferido  El idioma preferido del cliente para.
     */
    public Cliente(String usuario, String codigoAcceso, String nombre, 
                   String telefono, String direccion, Locale idiomaPreferido) {
        this.id = UUID.randomUUID();
        this.usuario = usuario;
        this.codigoAcceso = codigoAcceso;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.pais = Pais.MEXICO; //Por defecto, México es el país predeterminado.
        this.idiomaPreferido = idiomaPreferido;
    }

    /**
     * Obtiene el identificador único del cliente.  
     * @return El identificador único del cliente.
     */
    public UUID getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre de usuario del cliente.
     * @return El nombre de usuario del cliente.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Obtiene el código de acceso o contraseña del cliente.
     * @return El código de acceso o contraseña del cliente.
     */
    public String getCodigoAcceso(){
        return codigoAcceso;
    }

    /**
     * Obtiene el nombre real del cliente.
     * @return El nombre real del cliente.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * @return El número de teléfono del cliente.
     */
    public String getTelefono(){
        return telefono;
    }

    /**
     * Obtiene la dirección física del cliente.
     * @return La dirección física del cliente.
     */
    public String getDireccion(){
        return direccion;
    }

    /**
     * Obtiene la cuenta bancaria del cliente.
     * @return La cuenta bancaria del cliente.
     */
    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    /**
     * Establece la cuenta bancaria del cliente.
     * @param cuentaBancaria La cuenta bancaria del cliente.
     */
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    /**
     * Obtiene el país del cliente.
     * @return El país del cliente.
     */
    public Pais getPais(){
        return pais;
    }

    /**
     * Obtiene el idioma preferido del cliente.
     * @return El idioma preferido del cliente.
     */
    public Locale getIdiomaPreferido() {
        return idiomaPreferido;
    }

    /**
     * Establece el idioma preferido del cliente.
     * @param idiomaPreferido El idioma preferido del cliente.
     */
    public void setIdiomaPreferido(Locale idiomaPreferido) {
        this.idiomaPreferido = idiomaPreferido;
    }

}
