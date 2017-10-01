package uk.org.tdn.finances.service.impl;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import uk.org.tdn.finances.entity.PayeeEntity;
import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.service.IPayeeService;

@Default
public class PayeeServiceImpl extends AbstractServiceImpl<PayeeEntity, Integer> implements IPayeeService {

	private static final long serialVersionUID = -4767043826706266567L;

	public PayeeServiceImpl() {
		super(PayeeEntity.class);
	}

	@Override
	public List<PayeeEntity> findAllByUser(List<UserEntity> users) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PayeeEntity> criteriaQuery = criteriaBuilder.createQuery(PayeeEntity.class);
		Root<PayeeEntity> root = criteriaQuery.from(PayeeEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.in(root.get("user")).value(users));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

}