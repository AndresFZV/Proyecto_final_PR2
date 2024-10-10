package co.edu.uniquindio.marketplace.marketplace.mapping.mappers;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import co.edu.uniquindio.marketplace.marketplace.services.IMarketplaceMapping;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MarketplaceMappingImpl implements IMarketplaceMapping {

    @Override
    public List<VendedorDto> getVendedoresDto(List<Vendedor> listaVendedores) {
        if (listaVendedores == null){
            return null;
        }
        List<VendedorDto> listaVendedoresDto = new ArrayList<VendedorDto>(listaVendedores.size());
        for (Vendedor vendedor : listaVendedores) {
            listaVendedoresDto.add(vendedorToVendedorDto(vendedor));
        }
        return listaVendedoresDto;
    }

    @Override
    public VendedorDto vendedorToVendedorDto(Vendedor vendedor) {
        return new VendedorDto(
                vendedor.getNombre(),
                vendedor.getApellido(),
                vendedor.getCedula(),
                vendedor.getDireccion(),
                vendedor.getTelefono(),
                vendedor.getCorreo(),
                vendedor.getUsuario()
        );
    }

    @Override
    public Vendedor vendedorDtoToVendedor(VendedorDto vendedorDto) {
        return Vendedor.builder()
                .nombre(vendedorDto.nombre())
                .apellido(vendedorDto.apellido())
                .cedula(vendedorDto.cedula())
                .direccion(vendedorDto.direccion())
                .telefono(vendedorDto.telefono())
                .correo(vendedorDto.correo())
                .usuario(vendedorDto.usuario())
                .build();
    }

    @Override
    public List<ProductoDto> getProductosDto(List<Producto> listaProductos) {
        if(listaProductos == null){
            return null;
        }
        List<ProductoDto> listaProductosDto = new ArrayList<ProductoDto>(listaProductos.size());
        for (Producto producto : listaProductos) {
            listaProductosDto.add(productoToProductoDto(producto));
        }
        return listaProductosDto;
    }

    @Override
    public ProductoDto productoToProductoDto(Producto producto) {
        return new ProductoDto(
                producto.getProductoId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getImagen(),
                producto.getCategoria(),
                producto.getPrecio(),
                producto.getEstado()
        );
    }

    @Override
    public Producto productoDtoToProducto(ProductoDto productoDto) {
        return Producto.builder()
                .productoId(productoDto.productoId())
                .nombre(productoDto.nombre())
                .descripcion(productoDto.descripcion())
                .imagen(productoDto.imagen())
                .categoria(productoDto.categoria())
                .precio(productoDto.precio())
                .estado(productoDto.estado())
                .build();
    }

}
