import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Locale;
import java.util.Scanner;

import componente.servicios.Catalogo;
import modelo.Cliente;
import servidor.ServidorCheemsMart;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            mostrarAyuda();
            return;
        }

        String modo = args[0].toLowerCase();

        switch (modo) {
            case "servidor":
                ejecutarServidor();
                break;

            case "cliente":
                ejecutarCliente();
                break;

            default:
                System.out.println(" Error: Opción no reconocida '" + args[0] + "'.\n");
                mostrarAyuda();
                break;
        }
    }

    private static void mostrarAyuda() {
        System.out.println("==================================================");
        System.out.println("         SISTEMA TIENDA VIRTUAL CHEEMSMART        ");
        System.out.println("==================================================");
        System.out.println("Debes especificar un modo de ejecución:\n");
        System.out.println("  1. Iniciar el Servidor CheemsMart:");
        System.out.println("     java Main servidor\n");
        System.out.println("  2. Iniciar la aplic:");
        System.out.println("     java Main cliente");
        System.out.println("==================================================");
    }

    private static void ejecutarServidor() {
        try {
            System.out.println("[SERVIDOR] Iniciando RMI Registry interno en puerto 1099...");
            LocateRegistry.createRegistry(1099); // Crea el rmiregistry por código[cite: 1]

            System.out.println("[SERVIDOR] Instanciando ServidorCheemsMart (Real Subject)...");
            CheemsMart servidor = new ServidorCheemsMart();

            // Publica el servicio en el registro local
            Naming.rebind("rmi://localhost/CheemsMartService", servidor);

            System.out.println("[SERVIDOR] Servidor CheemsMart iniciado con éxito.");
            System.out.println("[SERVIDOR] Escuchando peticiones de los clientes RMI...");
        } catch (Exception e) {
            System.err.println("[SERVIDOR ERROR] No se pudo iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void ejecutarCliente() {
        System.out.println("[CLIENTE] Inicializando TiendaVirtualCheemsMart (Proxy)...");
        TiendaVirtualCheemsMart tiendaProxy = new TiendaVirtualCheemsMart();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- CONECTADO A CHEEMSMART ---");

        try {
            // Ejemplo de flujo interactivo probando las capacidades del Proxy
            System.out.print("¿Deseas (1) Registrarte o (2) Iniciar Sesión?: ");
            String opc = scanner.nextLine();

            if ("1".equals(opc)) {
                System.out.print("Ingresa tu usuario: ");
                String user = scanner.nextLine();
                System.out.print("Ingresa tu contraseña: ");
                String pass = scanner.nextLine();
                System.out.print("Ingresa tu nombre: ");
                String nombre = scanner.nextLine();

                Cliente nuevoCliente = new Cliente(user, pass, nombre, "555-1234", "CDMX", Locale.forLanguageTag("es-MX"));
                boolean registrado = tiendaProxy.registrarCliente(nuevoCliente);

                if (registrado) {
                    System.out.println(" Registro exitoso. Ahora inicia sesión.");
                }
            }

            // Intento de inicio de sesión
            System.out.print("\nUsuario para login: ");
            String user = scanner.nextLine();
            System.out.print("Contraseña para login: ");
            String pass = scanner.nextLine();

            Cliente clienteLogueado = tiendaProxy.iniciarSesion(user, pass);

            if (clienteLogueado != null) {
                // Consulta del catálogo usando el proxy
                System.out.println("\nObteniendo catálogo de productos...");
                Catalogo catalogo = tiendaProxy.verCatalogo();
                
                if (catalogo != null && catalogo.getProductos() != null) {
                    System.out.println("--- Catálogo Disponible ---");
                    catalogo.getProductos().forEach((codigo, producto) -> {
                        System.out.println("Codigo: " + codigo + " | " + producto.getNombre() + " | $" + producto.getPrecio());
                    });
                }
            }

        } catch (Exception e) {
            System.err.println("[CLIENTE ERROR] Error durante la ejecución: " + e.getMessage());
        }
    }
}