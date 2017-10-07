package uk.org.tdn.finances.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import lombok.Getter;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Transactional
public abstract class AbstractServiceImpl<T extends IBaseEntity<PK>, PK extends Number> implements Serializable {

	private static final long serialVersionUID = -6273903393184511454L;

	private Class<T> entityClass;

	@PersistenceContext(unitName = "FinancesPU")
	private @Getter EntityManager entityManager;

	public AbstractServiceImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Produces
	public List<T> findAll() {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public T findById(PK id) {
		return getEntityManager().find(entityClass, id);
	}

	public T save(T entity) {
		if (entity.getId() != null)
			return getEntityManager().merge(entity);
		else {
			getEntityManager().persist(entity);
			return entity;
		}
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	@Produces
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery qdef = cb.createQuery();
		Root<T> s = qdef.from(entityClass);
		Query q = getEntityManager().createQuery(qdef.select(cb.count(s)));
		return ((Long) q.getSingleResult()).intValue();
	}

}