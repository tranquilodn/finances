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

import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "dashboards")
public class DashboardEntity implements IBaseEntity<Integer> {

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
	@JoinTable(
			name = "dashboard_item_dashboard",
			joinColumns = @JoinColumn(name = "dashboard_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "dashboard_item_id", referencedColumnName = "id"))
	private Set<DashboardItemEntity> items;

	public DashboardEntity() {
		super();
	}

	public DashboardEntity(UserEntity user) {
		super();
		this.user = user;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDashboardReferenceName() {
		return dashboardReferenceName;
	}

	public void setDashboardReferenceName(String dashboardReferenceName) {
		this.dashboardReferenceName = dashboardReferenceName;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Set<DashboardItemEntity> getItems() {
		return items;
	}

	public void setItems(Set<DashboardItemEntity> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DashboardEntity other = (DashboardEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}