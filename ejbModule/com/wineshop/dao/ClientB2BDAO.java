package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.ClientB2BDTO;
import com.wineshop.model.ClientB2B;

/**
 * Session Bean implementation class ClientB2BDAO
 */
@Stateless
@LocalBean
public class ClientB2BDAO implements ClientB2BDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ClientB2BDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClientB2BDTO findById(int id) {
		ClientB2B client = entityManager.find(ClientB2B.class, id);
		ClientB2BDTO clientB2BDTO = new ClientB2BDTO(client.isVisibility(), client.getCompanyName(), client.getCUI(),
				client.isSubscribed(), client.isActive(), client.getLimitQuantity(), client.getUsername(),
				client.getPassword(), client.getAddress(), client.getEmail());
		clientB2BDTO.setId(client.getId());
		return clientB2BDTO;
	}

	@Override
	public List<ClientB2BDTO> findAll() {
		Query query = entityManager.createQuery("SELECT c FROM ClientB2B c");
		List<ClientB2B> clients = (List<ClientB2B>) query.getResultList();
		List<ClientB2BDTO> dtoClients = new ArrayList<>();
		for (ClientB2B client : clients) {
			ClientB2BDTO clientB2BDTO = new ClientB2BDTO(client.isVisibility(), client.getCompanyName(),
					client.getCUI(), client.isSubscribed(), client.isActive(), client.getLimitQuantity(),
					client.getUsername(), client.getPassword(), client.getAddress(), client.getEmail());
			clientB2BDTO.setId(client.getId());
			dtoClients.add(clientB2BDTO);
		}
		return dtoClients;
	}

	@Override
	public ClientB2BDTO save(ClientB2BDTO entity) {
		ClientB2B clientB2B = new ClientB2B(entity.isVisibility(), entity.getCompanyName(), entity.getCUI(),
				entity.isSubscribed(), entity.isActive(), entity.getLimitQuantity(), entity.getUsername(),
				entity.getPassword(), entity.getAddress(), entity.getEmail());
		entityManager.persist(clientB2B);
		entityManager.flush();
		entity.setId(clientB2B.getId());
		return entity;
	}

	@Override
	public ClientB2BDTO update(ClientB2BDTO entity) {
		ClientB2B clientB2B = new ClientB2B(entity.isVisibility(), entity.getCompanyName(), entity.getCUI(),
				entity.isSubscribed(), entity.isActive(), entity.getLimitQuantity(), entity.getUsername(),
				entity.getPassword(), entity.getAddress(), entity.getEmail());
		clientB2B.setId(entity.getId());
		clientB2B = entityManager.merge(clientB2B);
		return entity;
	}

	@Override
	public void delete(int id) {
		ClientB2B clientB2B = entityManager.find(ClientB2B.class, id);
		entityManager.remove(clientB2B);
	}
}
