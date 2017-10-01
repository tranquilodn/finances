package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.DashboardEntity;
import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.service.IProfileService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "profileMB")
public class ProfileMB extends AbstractMB<UserEntity, Integer> implements Serializable {

	private static final long serialVersionUID = 6496456289610479275L;

	@Inject
	private IProfileService service;

	@Inject
	private UserEntity user;

	public ProfileMB() {
	}

	@PostConstruct
	public void init() {
		super.init();
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public RoleType[] getRoleTypes() {
		return RoleType.values();
	}

	@Override
	public void prepareEdit() {
		if (getId() != null) {
			this.user = service.findById(getId());
		} else {
			this.user = new UserEntity();
		}
	}

	@Override
	public void prepareView() {
		if (getId() != null)
			this.user = service.findById(getId());
	}

	@Override
	public void actionSave(ActionEvent evt) throws IOException {
		try {
			if (user.getDashboard() == null) {
				DashboardEntity dashboard = new DashboardEntity();
				dashboard.setDashboardReferenceName(user.getEmail());
				user.setDashboard(dashboard);
			}
			service.save(user);
			if (!isManaged()) {
				setUser(user);
			}
			super.entityEventSrc.fire(user);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao salvar mercadoria.", ex);
		}
		JsfUtils.pageRedirect(evt);
	}

	@Override
	public void actionRemove(ActionEvent evt) throws IOException {
		try {
			service.remove(user);
			super.entityEventSrc.fire(user);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao remover mercadoria.", ex);
		}
		// log.debug("Removeu mercadoria "+mercadoria.getId());
		JsfUtils.pageRedirect(evt);
	}

}