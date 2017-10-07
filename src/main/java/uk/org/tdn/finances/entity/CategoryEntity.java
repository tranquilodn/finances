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

import lombok.Data;
import uk.org.tdn.finances.entity.enums.CategoryType;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "categories")
public @Data class CategoryEntity implements IBaseEntity<Integer> {

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

	public void categoryTypeChangeListener(ValueChangeEvent e) {
		this.setCategoryType((CategoryType) e.getNewValue());
	}

}