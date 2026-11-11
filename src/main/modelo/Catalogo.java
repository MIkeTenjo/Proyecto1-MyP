package modelo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Catalogo implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<String, Producto> productos;

    public Catalogo() {
        this.productos = new HashMap<>();
    }

    public Catalogo(Map<String, Producto> productos){
        if(!(productos instanceof Map<String, Producto>)){
            throw new IllegalArgumentException("Error");
        }
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        productos.put(producto.getCodigoBarras(), producto);
    }

    public Producto buscarProducto(String codigoBarras) {
        return productos.get(codigoBarras);
    }

    public Map<String, Producto> getProductos() {
        return productos;
    }
}