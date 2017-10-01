package uk.org.tdn.finances.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.MenuEntity;
import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.entity.enums.MenuType;
import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.service.IMenuService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "menuList")
public class MenuList extends AbstractList<MenuEntity> {

	private static final long serialVersionUID = -7795295979519443597L;

	private List<MenuEntity> menuGroups;
	private List<MenuEntity> menuItems;
	private List<MenuEntity> menuComboEnGb;
	private List<MenuEntity> menuComboPtBr;

	@Inject
	private IMenuService service;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final MenuEntity entity) {
		this.retrieveAll();
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final UserEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAll();
		this.retrieveMenuGroups();
		this.retrieveMenuItems();
		this.menuComboEnGb = service.findByMenuTypeAndLinkAndActiveOrderByLabelEnGbAsc(MenuType.G, false, true);
		this.menuComboPtBr = service.findByMenuTypeAndLinkAndActiveOrderByLabelPtBrAsc(MenuType.G, false, true);
	}

	private void retrieveMenuGroups() {
		List<MenuEntity> mel = null;
		UserEntity ue = JsfUtils.getSessionUserLoggedIn();
		if (ue != null) {
			if (ue.getRole() == RoleType.ADMIN) {
				mel = service.findByMenuTypeAndActiveOrderByPositionAsc(MenuType.G, true);
			} else {
				mel = service.findByMenuTypeAndRoleAndActiveOrderByPositionAsc(MenuType.G, RoleType.USER, true);
			}
		}
		this.menuGroups = mel;
	}

	private void retrieveMenuItems() {
		List<MenuEntity> mil = null;
		UserEntity ue = JsfUtils.getSessionUserLoggedIn();
		if (ue != null) {
			if (ue.getRole() == RoleType.ADMIN) {
				mil = service.findByMenuTypeAndActiveOrderByPositionAsc(MenuType.I, true);
			} else {
				mil = service.findByMenuTypeAndRoleAndActiveOrderByPositionAsc(MenuType.I, RoleType.USER, true);
			}
		}
		this.menuItems = mil;
	}

	public List<MenuEntity> getMenuGroups() {
		return this.menuGroups;
	}

	public List<MenuEntity> getMenuComboEnGb() {
		return this.menuComboEnGb;
	}

	public List<MenuEntity> getMenuComboPtBr() {
		return this.menuComboPtBr;
	}

	public List<MenuEntity> getMenuItemsEnGb(MenuEntity menuGroup) {
		List<MenuEntity> list = new ArrayList<MenuEntity>();
		for (MenuEntity menuItem : menuItems) {
			if (menuItem.getMenuGroup().equals(menuGroup)) {
				list.add(menuItem);
			}
		}
		return list;
	}

	public List<MenuEntity> getMenuItemsPtBr(MenuEntity menuGroup) {
		List<MenuEntity> list = new ArrayList<MenuEntity>();
		for (MenuEntity menuItem : menuItems) {
			if (menuItem.getMenuGroup().equals(menuGroup)) {
				list.add(menuItem);
			}
		}
		return list;
	}

}