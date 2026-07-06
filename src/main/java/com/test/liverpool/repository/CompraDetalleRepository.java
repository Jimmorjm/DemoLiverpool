package com.test.rupper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.rupper.model.CompraDetalle;
import com.test.rupper.model.CompraDetallePK;

@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, CompraDetallePK>{

	@Query(value = "SELECT det FROM CompraDetalle det WHERE det.detalleCompraId.compra.idCompra = :idCompraValue")
	List<CompraDetalle> findByIdCompra(@Param("idCompraValue") Integer idCompra);

}
