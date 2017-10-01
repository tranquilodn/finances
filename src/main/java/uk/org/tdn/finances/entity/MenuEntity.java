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

import uk.org.tdn.finances.entity.enums.MenuType;
import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;

@Entity
@Table(name = "menus")
public class MenuEntity implements IBaseEntity<Integer> {

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
	private Boolean link = false;

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

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabelEnGb() {
		return labelEnGb;
	}

	public void setLabelEnGb(String labelEnGb) {
		this.labelEnGb = labelEnGb;
	}

	public String getLabelPtBr() {
		return labelPtBr;
	}

	public void setLabelPtBr(String labelPtBr) {
		this.labelPtBr = labelPtBr;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public void menuTypeChangeListener(ValueChangeEvent e) {
		this.setMenuType((MenuType) e.getNewValue());
	}

	public MenuEntity getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuEntity menuGroup) {
		this.menuGroup = menuGroup;
	}

	public void menuGroupChangeListener(ValueChangeEvent e) {
		this.setMenuGroup((MenuEntity) e.getNewValue());
	}

	public boolean isLink() {
		return link;
	}

	public void setLink(boolean link) {
		this.link = link;
	}

	public void linkChangeListener(ValueChangeEvent e) {
		this.setLink((Boolean) e.getNewValue());
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMurl() {
		return murl;
	}

	public void setMurl(String murl) {
		this.murl = murl;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuEntity other = (MenuEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}