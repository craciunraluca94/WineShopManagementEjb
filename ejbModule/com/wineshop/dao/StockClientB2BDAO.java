package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.PromoDTO;
import com.wineshop.dto.StockClientB2BDTO;
import com.wineshop.model.ClientB2B;
import com.wineshop.model.Promo;
import com.wineshop.model.StockClientB2B;
import com.wineshop.model.StockSupplier;

/**
 * Session Bean implementation class StockClientB2BAO
 */
@Stateless
@LocalBean
public class StockClientB2BDAO implements StockClientB2BDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public StockClientB2BDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public StockClientB2BDTO findById(int id) {
		StockClientB2B stockClientB2B = entityManager.find(StockClientB2B.class, id);
		StockClientB2BDTO stockClientB2BDTO = new StockClientB2BDTO(stockClientB2B.getClientB2B().getId(),
				stockClientB2B.getPrice(), stockClientB2B.getQuantity());
		stockClientB2BDTO.setId(stockClientB2B.getId());
		return stockClientB2BDTO;
	}

	@Override
	public List<StockClientB2BDTO> findAll() {
		Query query = entityManager.createQuery("SELECT s FROM StockClientB2B s");
		List<StockClientB2B> stocks = (List<StockClientB2B>) query.getResultList();
		List<StockClientB2BDTO> dtoStocks = new ArrayList<>();
		for (StockClientB2B stock : stocks) {
			StockClientB2BDTO stockClientB2BDTO = new StockClientB2BDTO(stock.getClientB2B().getId(), stock.getPrice(),
					stock.getQuantity());
			stockClientB2BDTO.setId(stock.getId());
			dtoStocks.add(stockClientB2BDTO);
		}
		return dtoStocks;

	}

	@Override
	public StockClientB2BDTO save(StockClientB2BDTO entity) {
		Query query = entityManager
				.createQuery("SELECT s FROM ClientB2B s where s.id='" + entity.getClientB2BId() + "'");
		ClientB2B clientB2B = (ClientB2B) query.getSingleResult();

		StockClientB2B stockClientB2B = new StockClientB2B(clientB2B, entity.getPrice(), entity.getQuantity());
		entityManager.persist(stockClientB2B);
		entityManager.flush();
		entity.setId(stockClientB2B.getId());
		return entity;
	}

	@Override
	public StockClientB2BDTO update(StockClientB2BDTO entity) {
		Query query = entityManager
				.createQuery("SELECT s FROM ClientB2B s where s.id='" + entity.getClientB2BId() + "'");
		ClientB2B clientB2B = (ClientB2B) query.getSingleResult();

		StockClientB2B stockClientB2B = new StockClientB2B(clientB2B, entity.getPrice(), entity.getQuantity());
		stockClientB2B.setId(entity.getId());
		stockClientB2B = entityManager.merge(stockClientB2B);
		return entity;
	}

	@Override
	public void delete(int id) {
		StockClientB2B stockClientB2B = entityManager.find(StockClientB2B.class, id);
		entityManager.remove(stockClientB2B);
	}
}
