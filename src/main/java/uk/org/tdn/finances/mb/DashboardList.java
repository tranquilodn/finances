package uk.org.tdn.finances.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.DashboardEntity;
import uk.org.tdn.finances.service.IDashboardService;

@SessionScoped
@Named(value = "dashboardList")
public class DashboardList extends AbstractList<DashboardEntity> {

	private static final long serialVersionUID = -5728508478856214670L;

	@Inject
	private IDashboardService service;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final DashboardEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAll();
	}

}