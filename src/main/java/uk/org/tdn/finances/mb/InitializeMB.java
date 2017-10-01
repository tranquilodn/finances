package uk.org.tdn.finances.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "initializeMB")
@RequestScoped
public class InitializeMB implements Serializable {

	private static final long serialVersionUID = -2923202300046925598L;

	@PersistenceContext(unitName = "FinancesPU")
	private EntityManager em;

	@PostConstruct
	public void init() {
	}

}
