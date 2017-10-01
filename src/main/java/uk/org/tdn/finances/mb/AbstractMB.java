package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.service.IUserService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

public abstract class AbstractMB<T, PK> implements Serializable {

	private static final long serialVersionUID = -1042332315353405498L;

	private Boolean managed;

	private PK id;

	private List<T> filteredList;

	private String mobileLinkRedirect;

	@Inject
	protected Event<T> entityEventSrc;

	@Inject
	private Conversation conversation;

	private List<UserEntity> users;

	@Inject
	private IUserService userService;

	protected void init() {
		setManaged(false);
		filteredList = new ArrayList<T>(0);
		users = new ArrayList<UserEntity>(0);
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public String getMobileLinkRedirect() {
		return mobileLinkRedirect;
	}

	public void setMobileLinkRedirect(String mobileLinkRedirect) {
		this.mobileLinkRedirect = mobileLinkRedirect;
	}

	protected Conversation getConversation() {
		return conversation;
	}

	protected List<UserEntity> getUsers(UserEntity user) {
		if (user.getUserMaster() == null) {
			users = userService.findByIdOrUserMaster(user.getId(), user);
		} else {
			users = userService.findByIdOrUserMaster(user.getUserMaster().getId(), user.getUserMaster());
		}
		return users;
	}

	public boolean isManaged() {
		return this.managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	public List<T> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<T> filteredList) {
		this.filteredList = filteredList;
	}

	protected String getAttributeValue(ActionEvent evt, String attribute) {
		return (String) evt.getComponent().getAttributes().get(attribute);
	}

	public String getLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
	}

	public String getCurrencySymbol() {
		return Currency.getInstance(FacesContext.getCurrentInstance().getViewRoot().getLocale()).getSymbol();
	}

	public int getDefaultFractionDigits() {
		return Currency.getInstance(FacesContext.getCurrentInstance().getViewRoot().getLocale())
				.getDefaultFractionDigits();
	}

	public void actionNew(ActionEvent evt) throws IOException {
		setId(null);
		setManaged(false);
		getConversation().begin();
		JsfUtils.pageRedirect(evt);
	}

	public void actionDone(ActionEvent evt) throws IOException {
		setId(null);
		setManaged(false);
		if (!getConversation().isTransient())
			getConversation().end();
		JsfUtils.pageRedirect(evt);
	}

	public void actionCancel(ActionEvent evt) throws IOException {
		setId(null);
		setManaged(false);
		if (!getConversation().isTransient())
			getConversation().end();
		JsfUtils.pageRedirect(evt);
	}

	@SuppressWarnings("unchecked")
	public void actionEdit(ActionEvent evt) throws IOException {
		setId((PK) evt.getComponent().getAttributes().get("entityId"));
		setManaged(true);
		getConversation().begin();
		JsfUtils.pageRedirect(evt);
	}

	@SuppressWarnings("unchecked")
	public void actionView(ActionEvent evt) throws IOException {
		setId((PK) evt.getComponent().getAttributes().get("entityId"));
		JsfUtils.pageRedirect(evt);
	}

	abstract void prepareEdit();

	abstract void prepareView();

	abstract void actionSave(ActionEvent evt) throws IOException;

	abstract void actionRemove(ActionEvent evt) throws IOException;

}