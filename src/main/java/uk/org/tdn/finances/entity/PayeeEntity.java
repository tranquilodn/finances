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

import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

/**
 * Entity implementation class for Entity: Payee
 *
 */
@Entity
@Table(name = "payees")
public class PayeeEntity implements IBaseEntity<Integer> {

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

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Collection<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(Collection<TransactionEntity> transactions) {
		this.transactions = transactions;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayeeEntity other = (PayeeEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
