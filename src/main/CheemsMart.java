
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import componente.servicios.Catalogo;
import modelo.Cliente;

public interface CheemsMart extends Remote {

    // Registro enviando el objeto Cliente serializable
    boolean registrarCliente(Cliente cliente) throws RemoteException;

    // Autenticación usando 'usuario' y 'codigoAcceso' (coincidiendo con las propiedades de Cliente)
    Cliente iniciarSesion(String usuario, String codigoAcceso) throws RemoteException;

    // Consulta del catálogo completo
    Catalogo verCatalogo() throws RemoteException;

    // Validación de la cuenta bancaria por su número
    boolean verificarCuentaBancaria(String numeroCuenta) throws RemoteException;

    // Transacción de compra identificando al cliente por su UUID y el producto por su código de barras
    boolean realizarCompra(UUID idCliente, String codigoBarras, int cantidad, String numeroCuenta) throws RemoteException;
}
