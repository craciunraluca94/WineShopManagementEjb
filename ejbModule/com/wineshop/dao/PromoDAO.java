package com.wineshop.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.OrderItemDTO;
import com.wineshop.dto.PromoDTO;
import com.wineshop.model.ClientOrder;
import com.wineshop.model.OrderItem;
import com.wineshop.model.Promo;
import com.wineshop.model.StockClientB2B;
import com.wineshop.model.StockSupplier;

/**
 * Session Bean implementation class PromoDAO
 */
@Stateless
@LocalBean
public class PromoDAO implements PromoDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PromoDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PromoDTO findById(int id) {
		Promo promo = entityManager.find(Promo.class, id);
		PromoDTO promoDTO = new PromoDTO(promo.getStockSupplier().getId(), promo.getStartDate(), promo.getEndDate(),
				promo.getDiscount());
		promoDTO.setId(promo.getId());
		return promoDTO;
	}

	@Override
	public List<PromoDTO> findAll() {
		Query query = entityManager.createQuery("SELECT p FROM Promo p");
		List<Promo> promos = (List<Promo>) query.getResultList();
		List<PromoDTO> dtoPromos = new ArrayList<>();
		for (Promo promo : promos) {
			PromoDTO promoDTO = new PromoDTO(promo.getStockSupplier().getId(), promo.getStartDate(), promo.getEndDate(),
					promo.getDiscount());
			promoDTO.setId(promo.getId());
			dtoPromos.add(promoDTO);
		}
		return dtoPromos;
	}

	@Override
	public PromoDTO save(PromoDTO entity) {
		Query query = entityManager
				.createQuery("SELECT s FROM StockSupplier s where s.id='" + entity.getStockSupplierId() + "'");
		StockSupplier stockSupplier = (StockSupplier) query.getSingleResult();

		Promo promo = new Promo(stockSupplier, entity.getStartDate(), entity.getEndDate(), entity.getDiscount());
		entityManager.persist(promo);
		entityManager.flush();
		entity.setId(promo.getId());
		return entity;
	}

	@Override
	public PromoDTO update(PromoDTO entity) {
		Query query = entityManager
				.createQuery("SELECT s FROM StockSupplier s where s.id='" + entity.getStockSupplierId() + "'");
		StockSupplier stockSupplier = (StockSupplier) query.getSingleResult();

		Promo promo = new Promo(stockSupplier, entity.getStartDate(), entity.getEndDate(), entity.getDiscount());
		promo.setId(entity.getId());	
		promo = entityManager.merge(promo);
		return entity;
	}

	@Override
	public void delete(int id) {
		Promo promo = entityManager.find(Promo.class, id);
		entityManager.remove(promo);
	}
}
