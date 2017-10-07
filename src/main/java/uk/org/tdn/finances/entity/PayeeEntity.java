package uk.org.tdn.finances.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

/**
 * Entity implementation class for Entity: Payee
 *
 */
@Entity
@Table(name = "payees")
public @Data class PayeeEntity implements IBaseEntity<Integer> {

	private static final long serialVersionUID = 1003115123652137274L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", nullable = false, length = 40)
	private String name;

	@Column(name = "active", nullable = false)
	private boolean active = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserEntity user;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<TransactionEntity> transactions;

	public PayeeEntity() {
		super();
	}

	public PayeeEntity(String name, boolean active, UserEntity user) {
		super();
		this.name = name;
		this.active = active;
		this.user = user;
	}

}
