package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.ClientB2CDTO;
import com.wineshop.model.ClientB2C;

/**
 * Session Bean implementation class ClientB2CDAO
 */
@Stateless
@LocalBean
public class ClientB2CDAO implements ClientB2CDAORemote {
	
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * Default constructor.
	 */
	public ClientB2CDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClientB2CDTO findById(int id) {
		ClientB2C client = entityManager.find(ClientB2C.class, id);
		ClientB2CDTO clientB2CDTO = new  ClientB2CDTO(client.getFirstName(), client.getLastName(),
				client.getRegisterDate(), client.getCNP(),client.getUsername(),client.getPassword(), 
				client.getAddress(), client.getEmail());
		clientB2CDTO.setId(client.getId());
		return clientB2CDTO;
	}

	@Override
	public List<ClientB2CDTO> findAll() {
		Query query = entityManager.createQuery("SELECT c FROM ClientB2C c");
		List<ClientB2C> clients = (List<ClientB2C>) query.getResultList();
		List<ClientB2CDTO> dtoClients = new ArrayList<>();
		for (ClientB2C client : clients) {
			ClientB2CDTO clientB2CDTO =  new  ClientB2CDTO(client.getFirstName(), client.getLastName(),
					client.getRegisterDate(), client.getCNP(),client.getUsername(),client.getPassword(), 
					client.getAddress(), client.getEmail());
			clientB2CDTO.setId(client.getId());
			dtoClients.add(clientB2CDTO);
		}
		return dtoClients;
	}

	@Override
	public ClientB2CDTO save(ClientB2CDTO client) {
		ClientB2C clientB2C = new ClientB2C(client.getFirstName(), client.getLastName(),
				client.getRegisterDate(), client.getCNP(),client.getUsername(),client.getPassword(), 
				client.getAddress(), client.getEmail());
		entityManager.persist(clientB2C);
		entityManager.flush();
		client.setId(clientB2C.getId());
		return client;
	}

	@Override
	public ClientB2CDTO update(ClientB2CDTO client) {
		ClientB2C clientB2C = new ClientB2C(client.getFirstName(), client.getLastName(),
				client.getRegisterDate(), client.getCNP(),client.getUsername(),client.getPassword(), 
				client.getAddress(), client.getEmail());
		clientB2C.setId(client.getId());
		clientB2C = entityManager.merge(clientB2C);
		return client;
	}

	@Override
	public void delete(int id) {
		ClientB2C clientB2C = entityManager.find(ClientB2C.class, id);
		entityManager.remove(clientB2C);
	}
}
