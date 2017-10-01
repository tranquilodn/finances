package uk.org.tdn.finances.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.TransactionEntity;
import uk.org.tdn.finances.service.ITransactionService;

@SessionScoped
@Named(value = "transactionList")
public class TransactionList extends AbstractList<TransactionEntity> {

	private static final long serialVersionUID = -5488216812958240328L;

	@Inject
	private ITransactionService service;

	@Inject
	private UserList userList;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TransactionEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAllByUser(userList.getAccountUsers());
	}

}