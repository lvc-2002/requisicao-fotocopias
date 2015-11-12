package br.com.lam.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtil {
	
	public static void createMessageError(String componente, String sumario, String detalhes) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sumario, detalhes);
		fc.addMessage(componente, msg);
	}
	
	public static void createMessageFatal(String componente, String sumario, String detalhes) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sumario, detalhes);
		fc.addMessage(componente, msg);
	}
	
	public static void createMessageInfo(String componente, String sumario, String detalhes) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, sumario, detalhes);
		fc.addMessage(componente, msg);
	}
	
	public static void createMessageWarn(String componente, String sumario, String detalhes) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, sumario, detalhes);
		fc.addMessage(componente, msg);
	}

}
