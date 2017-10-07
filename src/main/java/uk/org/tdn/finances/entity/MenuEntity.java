package uk.org.tdn.finances.entity;

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
import javax.validation.constraints.NotNull;

import lombok.Data;
import uk.org.tdn.finances.entity.enums.MenuType;
import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "menus")
public @Data class MenuEntity implements IBaseEntity<Integer> {

	private static final long serialVersionUID = 1981327675331767392L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "label_en_gb", nullable = false, length = 100)
	private String labelEnGb;

	@NotNull
	@Column(name = "label_pt_br", nullable = false, length = 100)
	private String labelPtBr;

	@Enumerated(EnumType.STRING)
	@Column(name = "menu_type", nullable = false, length = 1)
	private MenuType menuType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_group", referencedColumnName = "id", nullable = true)
	private MenuEntity menuGroup;

	@Column(name = "link", nullable = false)
	private boolean link = false;

	@Column(name = "url", nullable = true, length = 100)
	private String url;

	@Column(name = "murl", nullable = true, length = 100)
	private String murl;

	@Column(name = "position", nullable = true, length = 2)
	private String position;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 5)
	private RoleType role;

	@Column(name = "active", nullable = false)
	private Boolean active = false;

	public MenuEntity() {
		super();
	}

	public MenuEntity(String labelEnGb, String labelPtBr, MenuType menuType, MenuEntity menuGroup, Boolean link,
			String url, String position, RoleType role, Boolean active) {
		super();
		this.labelEnGb = labelEnGb;
		this.labelPtBr = labelPtBr;
		this.menuType = menuType;
		this.menuGroup = menuGroup;
		this.link = link;
		this.url = url;
		this.position = position;
		this.role = role;
		this.active = active;
	}

	public void menuTypeChangeListener(ValueChangeEvent e) {
		this.setMenuType((MenuType) e.getNewValue());
	}

	public void menuGroupChangeListener(ValueChangeEvent e) {
		this.setMenuGroup((MenuEntity) e.getNewValue());
	}

	public void linkChangeListener(ValueChangeEvent e) {
		this.setLink((Boolean) e.getNewValue());
	}

}