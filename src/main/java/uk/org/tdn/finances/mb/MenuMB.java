package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.MenuEntity;
import uk.org.tdn.finances.entity.enums.MenuType;
import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.service.IMenuService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "menuMB")
public class MenuMB extends AbstractMB<MenuEntity, Integer> implements Serializable {

	private static final long serialVersionUID = -6435346363306824932L;

	/**
	 * Referência para o componente EJB, injetado pelo container.
	 */

	@Inject
	private IMenuService service;

	@Inject
	private MenuEntity menu;

	public MenuMB() {
	}

	@PostConstruct
	public void init() {
		super.init();
	}

	public MenuEntity getMenu() {
		return menu;
	}

	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}

	public MenuType[] getMenuTypes() {
		return MenuType.values();
	}

	public RoleType[] getRoleTypes() {
		return RoleType.values();
	}

	@Override
	public void prepareEdit() {
		if (getId() != null) {
			this.menu = service.findById(getId());
		} else {
			this.menu = new MenuEntity();
		}
	}

	@Override
	public void prepareView() {
		if (getId() != null)
			this.menu = service.findById(getId());
	}

	@Override
	public void actionSave(ActionEvent evt) throws IOException {
		try {
			if (menu.getMenuType() == MenuType.I) {
				menu.setPosition(null);
			} else {
				menu.setMenuGroup(null);
			}

			if (!menu.isLink()) {
				menu.setUrl(null);
			}

			service.save(menu);
			if (!isManaged()) {
				setMenu(menu);
			}
			super.entityEventSrc.fire(menu);
			if (!getConversation().isTransient())
				super.getConversation().end();
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		JsfUtils.pageRedirect(evt);
	}

	@Override
	public void actionRemove(ActionEvent evt) throws IOException {
		try {
			service.remove(menu);
			super.entityEventSrc.fire(menu);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao remover mercadoria.", ex);
		}
		// log.debug("Removeu mercadoria "+mercadoria.getId());
		JsfUtils.pageRedirect(evt);
	}

}