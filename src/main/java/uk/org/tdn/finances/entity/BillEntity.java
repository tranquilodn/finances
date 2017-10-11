package uk.org.tdn.finances.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "bills")
public @Data class BillEntity implements IBaseEntity<Long> {

	private static final long serialVersionUID = -1796065176456745521L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "registration_date", nullable = false)
	private Date registrationDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "due_date", nullable = false)
	private Date dueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payee_id", referencedColumnName = "id", nullable = false)
	private PayeeEntity payee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserEntity user;

}