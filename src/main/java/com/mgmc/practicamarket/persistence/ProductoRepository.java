package com.mgmc.practicamarket.persistence;

import com.mgmc.practicamarket.domain.Product;
import com.mgmc.practicamarket.domain.repository.ProductRepository;
import com.mgmc.practicamarket.persistence.crud.ProductoCrudRepository;
import com.mgmc.practicamarket.persistence.entity.Producto;
import com.mgmc.practicamarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    //Usar ésta anotación d inyección de dependencias asegurándonos que el componente es de spring.
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods->mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId){
        Optional<Producto> producto = productoCrudRepository.findById(productId);
        return producto.map(prod->mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product){
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
