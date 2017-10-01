package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import uk.org.tdn.finances.entity.DashboardEntity;
import uk.org.tdn.finances.service.IDashboardService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "dashboardMB")
public class DashboardMB extends AbstractMB<DashboardEntity, Integer> implements Serializable {

	private static final long serialVersionUID = 386105436316340508L;

	@Inject
	private IDashboardService service;

	@Inject
	private DashboardEntity dashboard;

	private DashboardModel model;
	private DashboardColumn revenue;
	private DashboardColumn expenses;

	public DashboardMB() {
	}

	@PostConstruct
	public void init() {
		super.init();
		this.model = new DefaultDashboardModel();
		this.revenue = new DefaultDashboardColumn();
		this.revenue.addWidget("revenue");
		this.expenses = new DefaultDashboardColumn();
		this.expenses.addWidget("expenses");
		model.addColumn(revenue);
		model.addColumn(expenses);
	}

	public DashboardEntity getDashboard() {
		return dashboard;
	}

	public void setDashboard(DashboardEntity dashboard) {
		this.dashboard = dashboard;
	}

	public DashboardModel getModel() {
		return model;
	}

	public void setModel(DashboardModel model) {
		this.model = model;
	}

	@Override
	public void prepareEdit() {
		if (getId() != null) {
			this.dashboard = service.findById(getId());
		} else {
			this.dashboard = new DashboardEntity();
		}
	}

	@Override
	public void prepareView() {
		if (getId() != null)
			this.dashboard = service.findById(getId());
	}

	@Override
	public void actionSave(ActionEvent evt) throws IOException {
		try {
			service.save(dashboard);
			super.entityEventSrc.fire(dashboard);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao salvar mercadoria.", ex);
		}
		JsfUtils.pageRedirect(evt);
	}

	@Override
	public void actionRemove(ActionEvent evt) throws IOException {
		try {
			service.remove(dashboard);
			super.entityEventSrc.fire(dashboard);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao remover mercadoria.", ex);
		}
		// log.debug("Removeu mercadoria "+mercadoria.getId());
		JsfUtils.pageRedirect(evt);
	}

}