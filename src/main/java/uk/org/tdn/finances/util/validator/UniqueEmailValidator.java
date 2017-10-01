package uk.org.tdn.finances.util.validator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.outputlabel.OutputLabel;

import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.mb.UserMB;
import uk.org.tdn.finances.service.IUserService;

@Named
@RequestScoped
public class UniqueEmailValidator implements Validator, Serializable {

	private static final long serialVersionUID = -7642439489183232319L;

	@Inject
	private IUserService service;

	@Inject
	private UserMB userMB;

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String email = (String) value.toString().trim();
		UserEntity user = null;
		user = service.findByEmail(email);
		if (user != null && !user.equals(userMB.getUser())) {
			StringBuilder sb = new StringBuilder();
			sb.append(retrieveOutputLabel(component) + ": ");
			sb.append(getMessage(context, "user.emailAlreadyExists") + ".");
			FacesMessage message = new FacesMessage(sb.toString());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	private String getMessage(FacesContext context, String key) {
		Locale locale = context.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		return bundle.getString(key);
	}

	private String retrieveOutputLabel(UIComponent uiComponent) {
		OutputLabel ol = null;
		if (!uiComponent.getParent().getChildren().isEmpty()) {
			Iterator<UIComponent> iterator = uiComponent.getParent().getChildren().iterator();
			while (iterator.hasNext()) {
				UIComponent component = ((UIComponent) iterator.next());
				if (component instanceof OutputLabel) {
					if (((OutputLabel) component).getFor().toString()
							.equals(new String(uiComponent.getId().toString()))) {
						ol = ((OutputLabel) component);
						break;
					}
				}
			}
		}
		return (ol == null) ? "" : ol.getValue().toString();
	}

}