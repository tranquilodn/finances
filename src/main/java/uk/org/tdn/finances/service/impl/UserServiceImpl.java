package uk.org.tdn.finances.service.impl;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.service.IUserService;

@Default
public class UserServiceImpl extends AbstractServiceImpl<UserEntity, Integer> implements IUserService {

	private static final long serialVersionUID = -5374319038747577048L;

	public UserServiceImpl() {
		super(UserEntity.class);
	}

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity user = null;
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), email));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		user = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		return user;
	}

	@Override
	public UserEntity findByEmailAndPassword(String email, String password) {
		UserEntity user = null;
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), email),
				criteriaBuilder.equal(root.get("password"), password));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		user = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		return user;
	}

	@Override
	public List<UserEntity> findByIdOrUserMaster(Integer id, UserEntity userMaster) {
		List<UserEntity> users = null;
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.or(criteriaBuilder.equal(root.get("id"), id),
				criteriaBuilder.equal(root.get("userMaster"), userMaster)));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		users = getEntityManager().createQuery(criteriaQuery).getResultList();
		return users;
	}

}