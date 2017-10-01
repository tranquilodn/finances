package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.TransactionEntity;
import uk.org.tdn.finances.entity.enums.CategoryType;
import uk.org.tdn.finances.entity.enums.RecordType;
import uk.org.tdn.finances.service.ITransactionService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "transactionMB")
public class TransactionMB extends AbstractMB<TransactionEntity, Long> implements Serializable {

	private static final long serialVersionUID = 3538770472732076242L;

	@Inject
	private ITransactionService service;

	@Inject
	private TransactionEntity transaction;

	private CategoryType categoryType;

	public TransactionMB() {
	}

	@PostConstruct
	public void init() {
		super.init();
	}

	public TransactionEntity getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionEntity transaction) {
		this.transaction = transaction;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public CategoryType[] getCategoryTypes() {
		return CategoryType.values();
	}

	public RecordType[] getRecordTypes() {
		return RecordType.values();
	}

	@Override
	public void prepareEdit() {
		if (getId() != null) {
			this.transaction = service.findById(getId());
			this.categoryType = transaction.getCategoryType();
		} else {
			this.transaction = new TransactionEntity();
		}
	}

	@Override
	public void prepareView() {
		if (getId() != null)
			this.transaction = service.findById(getId());
	}

	@Override
	public void actionNew(ActionEvent evt) throws IOException {
		String type = super.getAttributeValue(evt, "transactionType");
		if (type.equals(new String("revenue"))) {
			categoryType = CategoryType.R;
		} else if (type.equals(new String("expenditure"))) {
			categoryType = CategoryType.E;
		} else {
			throw new IOException("Parameter missing: transactionType");
		}
		super.actionNew(evt);
	}

	@Override
	public void actionSave(ActionEvent evt) throws IOException {
		try {
			if (!super.isManaged()) {
				transaction.setUser(JsfUtils.getSessionUserLoggedIn());
			}
			service.save(transaction);
			super.entityEventSrc.fire(transaction);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao salvar mercadoria.", ex);
		}
		JsfUtils.pageRedirect(evt);
	}

	@Override
	public void actionRemove(ActionEvent evt) throws IOException {
		try {
			service.remove(transaction);
			super.entityEventSrc.fire(transaction);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao remover mercadoria.", ex);
		}
		// log.debug("Removeu mercadoria "+mercadoria.getId());
		JsfUtils.pageRedirect(evt);
	}

}