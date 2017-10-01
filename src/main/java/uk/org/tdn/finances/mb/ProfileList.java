package uk.org.tdn.finances.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.service.IProfileService;

@SessionScoped
@Named(value = "profileList")
public class ProfileList extends AbstractList<UserEntity> {

	private static final long serialVersionUID = -6159451556139995505L;

	@Inject
	private IProfileService service;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final UserEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAll();
	}

}