package uk.org.tdn.finances.service.impl;

import javax.enterprise.inject.Default;

import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.service.IProfileService;

@Default
public class ProfileServiceImpl extends AbstractServiceImpl<UserEntity, Integer> implements IProfileService {

	private static final long serialVersionUID = -9114815983838948705L;

	public ProfileServiceImpl() {
		super(UserEntity.class);
	}

	// @Override
	// public UserEntity findByEmail(String email) {
	// CriteriaBuilder criteriaBuilder =
	// getEntityManager().getCriteriaBuilder();
	// CriteriaQuery<UserEntity> criteriaQuery =
	// criteriaBuilder.createQuery(UserEntity.class);
	// Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
	// Predicate p =
	// criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), email));
	// criteriaQuery.select(root);
	// criteriaQuery.where(p);
	// UserEntity user =
	// getEntityManager().createQuery(criteriaQuery).getSingleResult();
	// return user;
	// }

}