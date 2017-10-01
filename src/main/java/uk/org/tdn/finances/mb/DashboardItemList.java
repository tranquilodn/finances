package uk.org.tdn.finances.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.DashboardItemEntity;
import uk.org.tdn.finances.service.IDashboardItemService;

@SessionScoped
@Named(value = "dashboardItemList")
public class DashboardItemList extends AbstractList<DashboardItemEntity> {

	private static final long serialVersionUID = -7606579141280365138L;

	@Inject
	private IDashboardItemService service;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final DashboardItemEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAll();
	}

}