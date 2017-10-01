package uk.org.tdn.finances.util.jsf;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import uk.org.tdn.finances.entity.UserEntity;

public class JsfUtils {

	public static final String SESSION_USER_LOGGED_IN = "userLoggedIn";

	public static void pageRedirect(ActionEvent evt) throws IOException {
		String pageRedirect = (String) evt.getComponent().getAttributes().get("pageRedirect");
		redirect(pageRedirect);
	}

	public static UserEntity getSessionUserLoggedIn() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		return (UserEntity) session.getAttribute(JsfUtils.SESSION_USER_LOGGED_IN);
	}

	public static void setSessionUserLoggedIn(UserEntity userEntity) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute(JsfUtils.SESSION_USER_LOGGED_IN, userEntity);
	}

	public static void clearSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
	}

	private static void redirect(String pageRedirect) throws IOException {
		String redirect = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
				+ pageRedirect;
		FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
	}

	public static void addMessageInfo(String summary, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, message));
	}

}