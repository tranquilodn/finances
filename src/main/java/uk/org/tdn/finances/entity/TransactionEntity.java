package uk.org.tdn.finances.entity;

import java.util.Date;

import javax.faces.event.ValueChangeEvent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import uk.org.tdn.finances.entity.enums.CategoryType;
import uk.org.tdn.finances.entity.enums.RecordType;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "transactions")
public @Data class TransactionEntity implements IBaseEntity<Long> {

	private static final long serialVersionUID = -6139374400180939931L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Temporal(value = TemporalType.DATE)
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "category_type", nullable = false, length = 1)
	private CategoryType categoryType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
	private CategoryEntity category;

	@Enumerated(EnumType.STRING)
	@Column(name = "record_type", nullable = false, length = 1)
	private RecordType recordType;

	@NotNull
	@Column(name = "amount", nullable = false)
	private Double amount;

	@Column(name = "history", nullable = true, length = 100)
	private String history;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
	private UserEntity user;

	public TransactionEntity() {
		super();
	}

	public TransactionEntity(Date transactionDate, CategoryType categoryType) {
		super();
		this.transactionDate = transactionDate;
		this.categoryType = categoryType;
	}

	public TransactionEntity(Date transactionDate, CategoryEntity category, CategoryType categoryType, Double amount,
			String history, UserEntity user) {
		super();
		this.transactionDate = transactionDate;
		this.category = category;
		this.categoryType = categoryType;
		this.amount = amount;
		this.history = history;
		this.user = user;
	}

	public void categoryTypeChangeListener(ValueChangeEvent e) {
		this.setCategoryType((CategoryType) e.getNewValue());
	}

	public void categoryChangeListener(ValueChangeEvent e) {
		this.setCategory((CategoryEntity) e.getNewValue());
	}

	public void recordTypeChangeListener(ValueChangeEvent e) {
		this.setRecordType((RecordType) e.getNewValue());
	}

}