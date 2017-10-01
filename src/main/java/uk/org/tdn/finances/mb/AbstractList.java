package uk.org.tdn.finances.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;

public abstract class AbstractList<T> implements Serializable {

	private static final long serialVersionUID = 7222438019369278879L;

	protected List<T> list;

	@Produces
	public List<T> getList() {
		return list;
	}

	@PostConstruct
	public abstract void retrieveAll();

}
