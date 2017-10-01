package uk.org.tdn.finances.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import uk.org.tdn.finances.util.resources.UAgentInfo;

@SessionScoped
@Named(value = "userAgent")
public class UserAgentMB implements Serializable {

	private static final long serialVersionUID = 7244773743592805881L;

	private UAgentInfo uAgentInfo;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		String userAgentStr = request.getHeader("user-agent");
		String httpAccept = request.getHeader("Accept");
		uAgentInfo = new UAgentInfo(userAgentStr, httpAccept);
	}

	public boolean isPhone() {
		return uAgentInfo.detectTierIphone();
	}

	public boolean isTablet() {
		return uAgentInfo.detectTierTablet();
	}

}