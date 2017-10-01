package uk.org.tdn.finances.service;

import java.util.List;

import uk.org.tdn.finances.entity.MenuEntity;
import uk.org.tdn.finances.entity.enums.MenuType;
import uk.org.tdn.finances.entity.enums.RoleType;

public interface IMenuService extends IAbstractService<MenuEntity, Integer> {

	List<MenuEntity> findByMenuTypeAndActiveOrderByPositionAsc(MenuType menuType, boolean active);

	List<MenuEntity> findByMenuTypeAndRoleAndActiveOrderByPositionAsc(MenuType menuType, RoleType role, boolean active);

	List<MenuEntity> findByMenuTypeAndLinkAndActiveOrderByLabelEnGbAsc(MenuType menuType, boolean link, boolean active);

	List<MenuEntity> findByMenuTypeAndLinkAndActiveOrderByLabelPtBrAsc(MenuType menuType, boolean link, boolean active);

}