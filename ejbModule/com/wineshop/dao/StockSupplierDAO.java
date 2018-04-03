package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.PromoDTO;
import com.wineshop.dto.StockSupplierDTO;
import com.wineshop.model.Promo;
import com.wineshop.model.StockSupplier;
import com.wineshop.model.Supplier;
import com.wineshop.model.Wine;

/**
 * Session Bean implementation class StockSupplierDAO
 */
@Stateless
@LocalBean
public class StockSupplierDAO implements StockSupplierDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public StockSupplierDAO() {
	}

	@Override
	public StockSupplierDTO findById(int id) {
		StockSupplier stockSupplier = entityManager.find(StockSupplier.class, id);
		StockSupplierDTO stockSupplierDTO = new StockSupplierDTO(stockSupplier.getWine().getId(),
				stockSupplier.getSupplier().getId(), stockSupplier.getPrice(), stockSupplier.getQuantity());
		stockSupplierDTO.setId(stockSupplier.getId());
		return stockSupplierDTO;
	}

	@Override
	public List<StockSupplierDTO> findAll() {
		Query query = entityManager.createQuery("SELECT s FROM StockSupplier s");
		List<StockSupplier> stockSuppliers = (List<StockSupplier>) query.getResultList();
		List<StockSupplierDTO> dtoStockSuppliers = new ArrayList<>();
		for (StockSupplier stockSupplier : stockSuppliers) {
			StockSupplierDTO stockSupplierDTO = new StockSupplierDTO(stockSupplier.getWine().getId(),
					stockSupplier.getSupplier().getId(), stockSupplier.getPrice(), stockSupplier.getQuantity());
			stockSupplierDTO.setId(stockSupplier.getId());
			dtoStockSuppliers.add(stockSupplierDTO);
		}
		return dtoStockSuppliers;
	}

	@Override
	public StockSupplierDTO save(StockSupplierDTO entity) {
		Query wineQuery = entityManager.createQuery("SELECT w FROM Wine w where w.id='" + entity.getWineId() + "'");
		Wine wine = (Wine) wineQuery.getSingleResult();

		Query supplierQuery = entityManager
				.createQuery("SELECT s FROM Supplier s where s.id='" + entity.getSupplierId() + "'");
		Supplier supplier = (Supplier) supplierQuery.getSingleResult();

		StockSupplier stockSupplier = new StockSupplier(wine, supplier, entity.getPrice(), entity.getQuantity());
		entityManager.persist(stockSupplier);
		entityManager.flush();
		entity.setId(stockSupplier.getId());
		return entity;
	}

	@Override
	public StockSupplierDTO update(StockSupplierDTO entity) {
		Query wineQuery = entityManager.createQuery("SELECT w FROM Wine w where w.id='" + entity.getWineId() + "'");
		Wine wine = (Wine) wineQuery.getSingleResult();

		Query supplierQuery = entityManager
				.createQuery("SELECT s FROM Supplier s where s.id='" + entity.getSupplierId() + "'");
		Supplier supplier = (Supplier) supplierQuery.getSingleResult();

		StockSupplier stockSupplier = new StockSupplier(wine, supplier, entity.getPrice(), entity.getQuantity());
		stockSupplier.setId(entity.getId());	
		stockSupplier = entityManager.merge(stockSupplier);
		return entity;
	}

	@Override
	public void delete(int id) {
		StockSupplier stockSupplier = entityManager.find(StockSupplier.class, id);
		entityManager.remove(stockSupplier);
	}
}
