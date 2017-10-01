package uk.org.tdn.finances.service.impl;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import uk.org.tdn.finances.entity.CategoryEntity;
import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.entity.enums.CategoryType;
import uk.org.tdn.finances.service.ICategoryService;

@Default
public class CategoryServiceImpl extends AbstractServiceImpl<CategoryEntity, Integer> implements ICategoryService {

	private static final long serialVersionUID = -4292478338813603081L;

	public CategoryServiceImpl() {
		super(CategoryEntity.class);
	}

	@Override
	public List<CategoryEntity> findAllByUser(List<UserEntity> users) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> root = criteriaQuery.from(CategoryEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.in(root.get("user")).value(users));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		List<CategoryEntity> categories = getEntityManager().createQuery(criteriaQuery).getResultList();
		return categories;
	}

	@Override
	public List<CategoryEntity> findByCategoryTypeOrderByNameEnGbAsc(CategoryType categoryType) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> root = criteriaQuery.from(CategoryEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("categoryType"), categoryType));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nameEnGb")));
		List<CategoryEntity> categories = getEntityManager().createQuery(criteriaQuery).getResultList();
		return categories;
	}

	@Override
	public List<CategoryEntity> findByCategoryTypeOrderByNamePtBrAsc(CategoryType categoryType) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> root = criteriaQuery.from(CategoryEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("categoryType"), categoryType));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("namePtBr")));
		List<CategoryEntity> categories = getEntityManager().createQuery(criteriaQuery).getResultList();
		return categories;
	}

}