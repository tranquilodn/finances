package uk.org.tdn.finances.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.service.IUserService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "userList")
public class UserList extends AbstractList<UserEntity> {

	private static final long serialVersionUID = 3244765688803535320L;

	private List<UserEntity> accountUsers;

	@Inject
	private IUserService service;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final UserEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAll();
		this.retrieveAccountUsers();
	}

	@Produces
	public List<UserEntity> getAccountUsers() {
		return this.accountUsers;
	}

	private void retrieveAccountUsers() {
		List<UserEntity> users = null;
		UserEntity ue = JsfUtils.getSessionUserLoggedIn();
		if (ue.getUserMaster() == null) {
			users = service.findByIdOrUserMaster(ue.getId(), ue);
		} else {
			users = service.findByIdOrUserMaster(ue.getUserMaster().getId(), ue.getUserMaster());
		}
		accountUsers = users;
	}

}