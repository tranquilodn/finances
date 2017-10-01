package uk.org.tdn.finances.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.PayeeEntity;
import uk.org.tdn.finances.service.IPayeeService;

@SessionScoped
@Named(value = "payeeList")
public class PayeeList extends AbstractList<PayeeEntity> {

	private static final long serialVersionUID = -2148758889303477975L;

	@Inject
	private IPayeeService service;

	@Inject
	private UserList userList;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final PayeeEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAllByUser(userList.getAccountUsers());
	}

}