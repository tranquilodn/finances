package uk.org.tdn.finances.service.impl;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import uk.org.tdn.finances.entity.TransactionEntity;
import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.service.ITransactionService;

@Default
public class TransactionServiceImpl extends AbstractServiceImpl<TransactionEntity, Long>
		implements ITransactionService {

	private static final long serialVersionUID = -2852144690303798105L;

	public TransactionServiceImpl() {
		super(TransactionEntity.class);
	}

	@Override
	public List<TransactionEntity> findAllByUser(List<UserEntity> users) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<TransactionEntity> criteriaQuery = criteriaBuilder.createQuery(TransactionEntity.class);
		Root<TransactionEntity> root = criteriaQuery.from(TransactionEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.in(root.get("user")).value(users));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		List<TransactionEntity> transactions = getEntityManager().createQuery(criteriaQuery).getResultList();
		return transactions;
	}

}