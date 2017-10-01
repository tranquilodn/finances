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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "dashboard_items")
public class DashboardItemEntity implements IBaseEntity<Integer> {

	private static final long serialVersionUID = 569986066757117363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "dashboard_item_name", nullable = false, length = 100)
	private String dashboardItemName;

	@NotNull
	@Column(name = "dashboard_item_description")
	private String dashboardItemDescription;

	@ManyToMany
	@JoinTable(name = "dashboard_item_dashboard", joinColumns = @JoinColumn(name = "dashboard_item_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dashboard_id", referencedColumnName = "id"))
	private Set<DashboardEntity> dashboards;

	public DashboardItemEntity() {
		super();
	}

	public DashboardItemEntity(String dashboardItemName, String dashboardItemDescription) {
		super();
		this.dashboardItemName = dashboardItemName;
		this.dashboardItemDescription = dashboardItemDescription;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDashboardItemName() {
		return dashboardItemName;
	}

	public void setDashboardItemName(String dashboardItemName) {
		this.dashboardItemName = dashboardItemName;
	}

	public String getDashboardItemDescription() {
		return dashboardItemDescription;
	}

	public void setDashboardItemDescription(String dashboardItemDescription) {
		this.dashboardItemDescription = dashboardItemDescription;
	}

	public Set<DashboardEntity> getDashboards() {
		return dashboards;
	}

	public void setDashboards(Set<DashboardEntity> dashboards) {
		this.dashboards = dashboards;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DashboardItemEntity other = (DashboardItemEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}