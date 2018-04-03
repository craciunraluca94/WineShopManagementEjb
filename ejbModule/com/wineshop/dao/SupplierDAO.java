package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.PromoDTO;
import com.wineshop.dto.SupplierDTO;
import com.wineshop.model.Promo;
import com.wineshop.model.StockSupplier;
import com.wineshop.model.Supplier;

/**
 * Session Bean implementation class SupplierDAO
 */
@Stateless
@LocalBean
public class SupplierDAO implements SupplierDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SupplierDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SupplierDTO findById(int id) {
		Supplier supplier = entityManager.find(Supplier.class, id);
		SupplierDTO supplierDTO = new SupplierDTO(supplier.isVisibility(), supplier.getWineShopName(),
				supplier.getAge(), supplier.getUsername(), supplier.getPassword(), supplier.getAddress(),
				supplier.getEmail());
		supplierDTO.setId(supplier.getId());
		return supplierDTO;
	}

	@Override
	public List<SupplierDTO> findAll() {
		Query query = entityManager.createQuery("SELECT s FROM Supplier s");
		List<Supplier> suppliers = (List<Supplier>) query.getResultList();
		List<SupplierDTO> dtoSuppliers = new ArrayList<>();
		for (Supplier supplier : suppliers) {
			SupplierDTO supplierDTO = new SupplierDTO(supplier.isVisibility(), supplier.getWineShopName(),
					supplier.getAge(), supplier.getUsername(), supplier.getPassword(), supplier.getAddress(),
					supplier.getEmail());
			supplierDTO.setId(supplier.getId());
			dtoSuppliers.add(supplierDTO);
		}
		return dtoSuppliers;
	}

	@Override
	public SupplierDTO save(SupplierDTO entity) {
		Supplier supplier = new Supplier(entity.isVisibility(), entity.getWineShopName(), entity.getAge(),
				entity.getUsername(), entity.getPassword(), entity.getAddress(), entity.getEmail());
		entityManager.persist(supplier);
		entityManager.flush();
		entity.setId(supplier.getId());
		return entity;
	}

	@Override
	public SupplierDTO update(SupplierDTO entity) {
		Supplier supplier = new Supplier(entity.isVisibility(), entity.getWineShopName(), entity.getAge(),
				entity.getUsername(), entity.getPassword(), entity.getAddress(), entity.getEmail());

		supplier.setId(entity.getId());
		supplier = entityManager.merge(supplier);
		return entity;
	}

	@Override
	public void delete(int id) {
		Supplier supplier = entityManager.find(Supplier.class, id);
		entityManager.remove(supplier);
	}
}
