package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import CheemsMart;
import componente.servicios.Catalogo;
import modelo.Cliente;
import sistema.Sistema;

public class ServidorCheemsMart extends UnicastRemoteObject implements CheemsMart{

    private static final long serialVersionUID = 1L;

    private final Sistema sistema;

    public ServidorCheemsMart() throws RemoteException {
        super();
        this.sistema = new Sistema();
    }

    @Override
    public boolean registrarCliente(Cliente cliente) throws RemoteException {
        if(cliente == null){
            return false;
        } else {
            return sistema.crearCliente(cliente);
        }
    }

    @Override
    public Cliente iniciarSesion(String usuario, String codigoAcceso) throws RemoteException {
        return sistema.validarSesion(usuario, codigoAcceso);
    }

    @Override
    public Catalogo verCatalogo() throws RemoteException {
        return sistema.verCatalogo();
    }

    @Override
    public boolean verificarCuentaBancaria(String numeroCuenta) throws RemoteException {
        return sistema.validarCuentaBancaria(numeroCuenta);
    }

    @Override
    public boolean realizarCompra(UUID idCliente, String codigoBarras, int cantidad, String numeroCuenta) throws RemoteException {
        return sistema.procesarCompra(idCliente, codigoBarras, cantidad, numeroCuenta);
    }

}
