package com.wineshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.text.ChangedCharSetException;

import com.wineshop.dto.ChangePasswordDTO;
import com.wineshop.dto.ClientB2BDTO;
import com.wineshop.dto.PlatformUserDTO;
import com.wineshop.exceptions.ChangePasswordException;
import com.wineshop.model.ClientB2B;
import com.wineshop.model.PlatformUser;

/**
 * Session Bean implementation class PlatformUserDAO
 */
@Stateless
@LocalBean
public class PlatformUserDAO implements PlatformUserDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PlatformUserDAO() {
	}

	@Override
	public PlatformUserDTO findById(int id) {
		PlatformUser platformUser = entityManager.find(PlatformUser.class, id);
		PlatformUserDTO platformUserDTO = new PlatformUserDTO(platformUser.getUsername(), platformUser.getPassword(),
				platformUser.getAddress(), platformUser.getEmail());
		platformUserDTO.setId(platformUser.getId());
		return platformUserDTO;
	}

	@Override
	public List<PlatformUserDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM PlatformUser u");
		List<PlatformUser> users = (List<PlatformUser>) query.getResultList();
		List<PlatformUserDTO> dtoUsers = new ArrayList<>();
		for (PlatformUser platformUser : users) {
			PlatformUserDTO platformUserDTO = new PlatformUserDTO(platformUser.getUsername(),
					platformUser.getPassword(), platformUser.getAddress(), platformUser.getEmail());
			platformUserDTO.setId(platformUser.getId());
			dtoUsers.add(platformUserDTO);
		}
		return dtoUsers;
	}

	@Override
	public PlatformUserDTO save(PlatformUserDTO entity) {
		PlatformUser platformUser = new PlatformUser(entity.getUsername(), entity.getPassword(), entity.getAddress(),
				entity.getEmail());
		entityManager.persist(platformUser);
		entityManager.flush();
		entity.setId(platformUser.getId());
		return entity;
	}

	@Override
	public PlatformUserDTO update(PlatformUserDTO entity) {
		PlatformUser platformUser = new PlatformUser(entity.getUsername(), entity.getPassword(), entity.getAddress(),
				entity.getEmail());
		platformUser.setId(entity.getId());
		platformUser = entityManager.merge(platformUser);
		return entity;
	}

	@Override
	public void delete(int id) {
		PlatformUser platformUser = entityManager.find(PlatformUser.class, id);
		entityManager.remove(platformUser);
	}

	@Override
	public Boolean updatePassword(ChangePasswordDTO changePasswordDTO) {
		PlatformUser user= null;
		try {
		 user = (PlatformUser) entityManager.createNamedQuery("findUserByUsername")
				.setParameter("username", changePasswordDTO.getUsername()).getSingleResult();
		}
		catch(NoResultException e) {
			throw new ChangePasswordException("The username is not valid!");
		}
		if (null != user) {
			if (user.getPassword().equals(changePasswordDTO.getOldPassword())) {
				if (!changePasswordDTO.getOldPassword().equals(changePasswordDTO.getNewPassword())) {
					user.setPassword(changePasswordDTO.getNewPassword());
					user = entityManager.merge(user);
					return true;
				} else {
					throw new ChangePasswordException(
							"Please choose another new password, not the same as the old one!");
				}
			} else
				throw new ChangePasswordException("The old password is not valid.");
		} else
			throw new ChangePasswordException("The username is not valid!");
	}
}
