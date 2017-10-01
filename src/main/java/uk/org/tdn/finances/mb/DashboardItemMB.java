package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.DashboardItemEntity;
import uk.org.tdn.finances.service.IDashboardItemService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "dashboardItemMB")
public class DashboardItemMB extends AbstractMB<DashboardItemEntity, Integer> implements Serializable {

	private static final long serialVersionUID = 3625899939590189859L;

	@Inject
	private IDashboardItemService service;

	@Inject
	private DashboardItemEntity item;

	public DashboardItemMB() {
	}

	@PostConstruct
	public void init() {
		super.init();
	}

	public DashboardItemEntity getItem() {
		return item;
	}

	public void setItem(DashboardItemEntity item) {
		this.item = item;
	}

	@Override
	public void prepareEdit() {
		if (getId() != null) {
			this.item = service.findById(getId());
		} else {
			this.item = new DashboardItemEntity();
		}
	}

	@Override
	public void prepareView() {
		if (getId() != null)
			this.item = service.findById(getId());
	}

	@Override
	public void actionSave(ActionEvent evt) throws IOException {
		try {
			service.save(item);
			if (!isManaged()) {
				setItem(item);
			}
			super.entityEventSrc.fire(item);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao salvar mercadoria.", ex);
		}
		JsfUtils.pageRedirect(evt);
	}

	@Override
	public void actionRemove(ActionEvent evt) throws IOException {
		try {
			service.remove(item);
			super.entityEventSrc.fire(item);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao remover mercadoria.", ex);
		}
		// log.debug("Removeu mercadoria "+mercadoria.getId());
		JsfUtils.pageRedirect(evt);
	}

}