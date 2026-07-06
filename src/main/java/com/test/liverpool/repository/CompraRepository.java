package com.test.rupper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.rupper.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

	@Query(value = "SELECT cmp FROM Compra cmp INNER JOIN cmp.cliente cli WHERE cli.idCliente = :idClienteValue")
	List<Compra> findByIdCliente(@Param("idClienteValue") Integer idCliente);

	
	@Query(value = "SELECT cmp FROM Compra cmp INNER JOIN cmp.cliente cli WHERE cmp.nombre = :nombreCompraValue AND cli.idCliente = :idClienteValue")
	List<Compra> findByNameAndIdCliente(@Param("nombreCompraValue")String nombreCompra, @Param("idClienteValue")Integer idCliente);
	

}
