package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.ClientB2BDTO;
import com.wineshop.dto.ClientB2CDTO;
import com.wineshop.dto.ClientOrderDTO;
import com.wineshop.model.ClientB2B;
import com.wineshop.model.ClientB2C;
import com.wineshop.model.ClientOrder;

/**
 * Session Bean implementation class ClientOrderDAO
 */
@Stateless
@LocalBean
public class ClientOrderDAO implements ClientOrderDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ClientOrderDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClientOrderDTO findById(int id) {
		ClientOrder clientOrder = entityManager.find(ClientOrder.class, id);
		ClientOrderDTO dtoClientOrder = new ClientOrderDTO(clientOrder.getDate(), clientOrder.getOrderNumber(),
				clientOrder.getClientB2C().getId());
		dtoClientOrder.setId(clientOrder.getId());
		return dtoClientOrder;
	}

	@Override
	public List<ClientOrderDTO> findAll() {
		Query query = entityManager.createQuery("SELECT c FROM ClientOrder c");
		List<ClientOrder> clientOrders = (List<ClientOrder>) query.getResultList();
		List<ClientOrderDTO> dtoClientOrders = new ArrayList<>();
		for (ClientOrder clientOrder : clientOrders) {
			ClientOrderDTO dtoClientOrder = new ClientOrderDTO(clientOrder.getDate(), clientOrder.getOrderNumber(),
					clientOrder.getClientB2C().getId());
			dtoClientOrder.setId(clientOrder.getId());
			dtoClientOrders.add(dtoClientOrder);
		}
		return dtoClientOrders;
	}

	@Override
	public ClientOrderDTO save(ClientOrderDTO clientOrderDto) {
		Query query = entityManager
				.createQuery("SELECT c FROM ClientB2C c where c.id='" + clientOrderDto.getClientB2CiId() + "'");
		ClientB2C clientB2C = (ClientB2C) query.getSingleResult();

		ClientOrder clientOrder = new ClientOrder(clientOrderDto.getDate(), clientOrderDto.getOrderNumber(), clientB2C);
		entityManager.persist(clientOrder);
		entityManager.flush();
		clientOrderDto.setId(clientOrder.getId());
		return clientOrderDto;
	}

	@Override
	public ClientOrderDTO update(ClientOrderDTO clientOrderDto) {
		Query query = entityManager
				.createQuery("SELECT c FROM ClientB2C c where c.id='" + clientOrderDto.getClientB2CiId() + "'");
		ClientB2C clientB2C = (ClientB2C) query.getSingleResult();

		ClientOrder clientOrder = new ClientOrder(clientOrderDto.getDate(), clientOrderDto.getOrderNumber(), clientB2C);
		clientOrder.setId(clientOrderDto.getId());
		clientOrder = entityManager.merge(clientOrder);
		return clientOrderDto;
	}

	@Override
	public void delete(int id) {
		ClientOrder clientOrder = entityManager.find(ClientOrder.class, id);
		entityManager.remove(clientOrder);
	}
}
