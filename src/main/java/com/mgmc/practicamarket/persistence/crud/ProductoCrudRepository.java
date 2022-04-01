package com.mgmc.practicamarket.persistence.crud;

import com.mgmc.practicamarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoriaOrderByNombreAsc(int Categoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
