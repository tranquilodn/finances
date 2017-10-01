package uk.org.tdn.finances.entity;

import java.util.Collection;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uk.org.tdn.finances.entity.enums.CategoryType;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "categories")
public class CategoryEntity implements IBaseEntity<Integer> {

	private static final long serialVersionUID = 8960212962711260019L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "category_type", nullable = false, length = 1)
	private CategoryType categoryType;

	@Column(name = "name_en_gb", nullable = false, length = 40)
	private String nameEnGb;

	@Column(name = "name_pt_br", nullable = false, length = 40)
	private String namePtBr;

	@Column(name = "description", nullable = true, length = 255)
	private String description;

	@Column(name = "active", nullable = false)
	private boolean active = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserEntity user;

	@OneToMany(mappedBy = "category")
	private Collection<TransactionEntity> transactions;

	public CategoryEntity() {
		super();
	}

	public CategoryEntity(CategoryType categoryType, String nameEnGb, String namePtBr, String description,
			boolean active, UserEntity user) {
		super();
		this.categoryType = categoryType;
		this.nameEnGb = nameEnGb;
		this.namePtBr = namePtBr;
		this.description = description;
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

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public void categoryTypeChangeListener(ValueChangeEvent e) {
		this.setCategoryType((CategoryType) e.getNewValue());
	}

	public String getNameEnGb() {
		return nameEnGb;
	}

	public void setNameEnGb(String nameEnGb) {
		this.nameEnGb = nameEnGb;
	}

	public String getNamePtBr() {
		return namePtBr;
	}

	public void setNamePtBr(String namePtBr) {
		this.namePtBr = namePtBr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		CategoryEntity other = (CategoryEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}