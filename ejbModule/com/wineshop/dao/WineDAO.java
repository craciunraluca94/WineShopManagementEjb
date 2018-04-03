package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.WineDTO;
import com.wineshop.model.Wine;

/**
 * Session Bean implementation class WineDao
 */
@Stateless
@LocalBean
public class WineDAO implements WineDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public WineDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public WineDTO findById(int id) {
		Wine wine = entityManager.find(Wine.class, id);
		WineDTO wineDTO = new WineDTO(wine.getYear(), wine.getName(), wine.getDescription(), wine.getVariety(),
				wine.getType());
		wineDTO.setId(wine.getId());
		return wineDTO;
	}

	@Override
	public List<WineDTO> findAll() {
		Query query = entityManager.createQuery("SELECT w FROM Wine w");
		List<Wine> wines = (List<Wine>) query.getResultList();
		List<WineDTO> dtoWines = new ArrayList<>();
		for (Wine wine : wines) {
			WineDTO wineDTO = new WineDTO(wine.getYear(), wine.getName(), wine.getDescription(), wine.getVariety(),
					wine.getType());
			wineDTO.setId(wine.getId());
			dtoWines.add(wineDTO);
		}
		return dtoWines;
	}

	@Override
	public WineDTO save(WineDTO entity) {
		Wine wine = new Wine(entity.getYear(), entity.getName(), entity.getDescription(), entity.getVariety(),
				entity.getType());
		entityManager.persist(wine);
		entityManager.flush();
		entity.setId(wine.getId());
		return entity;
	}

	@Override
	public WineDTO update(WineDTO entity) {
		Wine wine = new Wine(entity.getYear(), entity.getName(), entity.getDescription(), entity.getVariety(),
				entity.getType());
		wine.setId(entity.getId());
		wine = entityManager.merge(wine);
		return entity;
	}

	@Override
	public void delete(int id) {
		Wine wine = entityManager.find(Wine.class, id);
		entityManager.remove(wine);
	}
}
