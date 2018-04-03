package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineshop.dto.OrderItemDTO;
import com.wineshop.dto.OrderItemDTO;
import com.wineshop.model.ClientB2C;
import com.wineshop.model.ClientOrder;
import com.wineshop.model.OrderItem;
import com.wineshop.model.StockClientB2B;
import com.wineshop.model.OrderItem;

/**
 * Session Bean implementation class OrderItemDAO
 */
@Stateless
@LocalBean
public class OrderItemDAO implements OrderItemDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public OrderItemDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public OrderItemDTO findById(int id) {
		OrderItem orderItem = entityManager.find(OrderItem.class, id);
		OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem.getStockClientB2B().getId(),
				orderItem.getOrder().getId(), orderItem.getQuantity(), orderItem.getPrice());
		orderItemDTO.setId(orderItem.getId());
		return orderItemDTO;
	}

	@Override
	public List<OrderItemDTO> findAll() {
		Query query = entityManager.createQuery("SELECT o FROM OrderItem o");
		List<OrderItem> orderItems = (List<OrderItem>) query.getResultList();
		List<OrderItemDTO> dtoOrderItems = new ArrayList<>();
		for (OrderItem orderItem : orderItems) {
			OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem.getStockClientB2B().getId(),
					orderItem.getOrder().getId(), orderItem.getQuantity(), orderItem.getPrice());
			orderItemDTO.setId(orderItem.getId());
			dtoOrderItems.add(orderItemDTO);
		}
		return dtoOrderItems;
	}

	@Override
	public OrderItemDTO save(OrderItemDTO entity) {
		Query query = entityManager
				.createQuery("SELECT s FROM StockClientB2C s where s.id='" + entity.getStockClientB2BId() + "'");
		StockClientB2B stockClientB2B = (StockClientB2B) query.getSingleResult();

		Query clientOrderQuery = entityManager
				.createQuery("SELECT o FROM ClientOrder o where o.id='" + entity.getClientOrderId() + "'");
		ClientOrder clientOrder = (ClientOrder) query.getSingleResult();

		OrderItem orderItem = new OrderItem(stockClientB2B, clientOrder, entity.getQuantity(), entity.getPrice());
		entityManager.persist(orderItem);
		entityManager.flush();
		entity.setId(orderItem.getId());
		return entity;
	}

	@Override
	public OrderItemDTO update(OrderItemDTO entity) {

		Query query = entityManager
				.createQuery("SELECT s FROM StockClientB2C s where s.id='" + entity.getStockClientB2BId() + "'");
		StockClientB2B stockClientB2B = (StockClientB2B) query.getSingleResult();

		Query clientOrderQuery = entityManager
				.createQuery("SELECT o FROM ClientOrder o where o.id='" + entity.getClientOrderId() + "'");
		ClientOrder clientOrder = (ClientOrder) query.getSingleResult();

		OrderItem orderItem = new OrderItem(stockClientB2B, clientOrder, entity.getQuantity(), entity.getPrice());

		orderItem.setId(entity.getId());
		orderItem = entityManager.merge(orderItem);
		return entity;
	}

	@Override
	public void delete(int id) {
		OrderItem orderItem = entityManager.find(OrderItem.class, id);
		entityManager.remove(orderItem);
	}
}
