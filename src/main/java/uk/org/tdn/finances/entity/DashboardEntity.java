package uk.org.tdn.finances.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "dashboards")
public @Data class DashboardEntity implements IBaseEntity<Integer> {

	private static final long serialVersionUID = -1551380522823899409L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "dashboard_reference_name", length = 60, nullable = true)
	private String dashboardReferenceName;

	@OneToOne(mappedBy = "dashboard")
	private UserEntity user;

	@ManyToMany
	@JoinTable(name = "dashboard_item_dashboard", joinColumns = @JoinColumn(name = "dashboard_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dashboard_item_id", referencedColumnName = "id"))
	private Set<DashboardItemEntity> items;

	public DashboardEntity() {
		super();
	}

	public DashboardEntity(UserEntity user) {
		super();
		this.user = user;
	}

}