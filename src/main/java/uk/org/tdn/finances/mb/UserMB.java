package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.DashboardEntity;
import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.qualifiers.interfaces.LoggedIn;
import uk.org.tdn.finances.service.IUserService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "userMB")
public class UserMB extends AbstractMB<UserEntity, Integer> implements Serializable {

	private static final long serialVersionUID = -5250263596023896406L;

	@Inject
	private IUserService service;

	@Inject
	private UserEntity user;

	private String username;
	private String password;

	public UserMB() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(ActionEvent evt) throws IOException {
		UserEntity u = null;
		try {
			u = service.findByEmailAndPassword(username, password);
		} catch (Exception e) {
		}
		if (u != null) {
			JsfUtils.setSessionUserLoggedIn(u);
			super.entityEventSrc.fire(u);
			JsfUtils.pageRedirect(evt);
		} else {
			JsfUtils.addMessageInfo("Login fail:", "Username or password invalid.");
		}
	}

	public void logout(ActionEvent evt) throws IOException {
		JsfUtils.clearSession();
		JsfUtils.pageRedirect(evt);
	}

	@Produces
	@LoggedIn
	public boolean isLoggedIn() {
		return !(JsfUtils.getSessionUserLoggedIn() == null);
	}

	// UserEntity getCurrentUser() {
	// return this.user;
	// }

}