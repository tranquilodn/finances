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

import lombok.Data;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "dashboard_items")
public @Data class DashboardItemEntity implements IBaseEntity<Integer> {

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

}