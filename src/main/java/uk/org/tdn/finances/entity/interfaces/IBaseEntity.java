package uk.org.tdn.finances.entity.interfaces;

import java.io.Serializable;

/**
 * Estipula um contrato base para as entidades persistentes da aplicação.
 * 
 * <p>
 * Esse contrato é utilizado pelo componente base de persistência:
 * <code>AbstractPersistence</code>.
 * </p>
 * 
 * @see br.com.yaw.cdi.service.AbstractServiceImpl
 * 
 * @author YaW Tecnologia
 */
public interface IBaseEntity<PK extends Number> extends Serializable {

	public PK getId();

	public void setId(PK id);

}