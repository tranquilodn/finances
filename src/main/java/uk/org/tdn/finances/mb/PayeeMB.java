package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.PayeeEntity;
import uk.org.tdn.finances.service.IPayeeService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "payeeMB")
public class PayeeMB extends AbstractMB<PayeeEntity, Integer> implements Serializable {

	private static final long serialVersionUID = -6435346363306824932L;

	@Inject
	private IPayeeService service;

	@Inject
	private PayeeEntity payee;

	public PayeeMB() {
	}

	@PostConstruct
	public void init() {
		super.init();
	}

	public PayeeEntity getPayee() {
		return payee;
	}

	public void setPayee(PayeeEntity payee) {
		this.payee = payee;
	}

	@Override
	public void prepareEdit() {
		if (getId() != null) {
			this.payee = service.findById(getId());
		} else {
			this.payee = new PayeeEntity();
		}
	}

	@Override
	public void prepareView() {
		if (getId() != null)
			this.payee = service.findById(getId());
	}

	@Override
	public void actionSave(ActionEvent evt) throws IOException {
		try {
			if (!super.isManaged()) {
				payee.setUser(JsfUtils.getSessionUserLoggedIn());
			}
			service.save(payee);
			super.entityEventSrc.fire(payee);
			super.getConversation().end();
		} catch (Exception ex) {
		}
		JsfUtils.pageRedirect(evt);
	}

	@Override
	public void actionRemove(ActionEvent evt) throws IOException {
		try {
			service.remove(payee);
			super.entityEventSrc.fire(payee);
			super.getConversation().end();
		} catch (Exception ex) {
		}
		JsfUtils.pageRedirect(evt);
	}

}