package uk.org.tdn.finances.service.impl;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import uk.org.tdn.finances.entity.MenuEntity;
import uk.org.tdn.finances.entity.enums.MenuType;
import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.service.IMenuService;

@Default
public class MenuServiceImpl extends AbstractServiceImpl<MenuEntity, Integer> implements IMenuService {

	private static final long serialVersionUID = 6180145039482476145L;

	public MenuServiceImpl() {
		super(MenuEntity.class);
	}

	@Override
	public List<MenuEntity> findByMenuTypeAndActiveOrderByPositionAsc(MenuType menuType, boolean active) {
		List<MenuEntity> menus = null;
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);
		Root<MenuEntity> root = criteriaQuery.from(MenuEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("menuType"), menuType),
				criteriaBuilder.equal(root.get("active"), active));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("position")));
		menus = getEntityManager().createQuery(criteriaQuery).getResultList();
		return menus;
	}

	@Override
	public List<MenuEntity> findByMenuTypeAndRoleAndActiveOrderByPositionAsc(MenuType menuType, RoleType role,
			boolean active) {
		List<MenuEntity> menus = null;
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);
		Root<MenuEntity> root = criteriaQuery.from(MenuEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("menuType"), menuType),
				criteriaBuilder.equal(root.get("roleType"), role), criteriaBuilder.equal(root.get("active"), active));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("position")));
		menus = getEntityManager().createQuery(criteriaQuery).getResultList();
		return menus;
	}

	@Override
	public List<MenuEntity> findByMenuTypeAndLinkAndActiveOrderByLabelEnGbAsc(MenuType menuType, boolean link,
			boolean active) {
		List<MenuEntity> menus = null;
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);
		Root<MenuEntity> root = criteriaQuery.from(MenuEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("menuType"), menuType),
				criteriaBuilder.equal(root.get("link"), link), criteriaBuilder.equal(root.get("active"), active));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("labelEnGb")));
		menus = getEntityManager().createQuery(criteriaQuery).getResultList();
		return menus;
	}

	@Override
	public List<MenuEntity> findByMenuTypeAndLinkAndActiveOrderByLabelPtBrAsc(MenuType menuType, boolean link,
			boolean active) {
		List<MenuEntity> menus = null;
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);
		Root<MenuEntity> root = criteriaQuery.from(MenuEntity.class);
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("menuType"), menuType),
				criteriaBuilder.equal(root.get("link"), link), criteriaBuilder.equal(root.get("active"), active));
		criteriaQuery.select(root);
		criteriaQuery.where(p);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("labelPtBr")));
		menus = getEntityManager().createQuery(criteriaQuery).getResultList();
		return menus;
	}

}