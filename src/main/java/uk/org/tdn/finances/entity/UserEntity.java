package uk.org.tdn.finances.entity;

import javax.faces.event.ValueChangeEvent;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import uk.org.tdn.finances.entity.enums.RoleType;
import uk.org.tdn.finances.entity.interfaces.IBaseEntity;
import uk.org.tdn.finances.util.converter.EncryptStringConverter;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity implements IBaseEntity<Integer> {

	private static final long serialVersionUID = -656260485728015234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title", length = 15, nullable = true)
	private String title;

	@Column(name = "forename", length = 40, nullable = true)
	private String forename;

	@Column(name = "surname", length = 40, nullable = true)
	private String surname;

	@NotNull
	@Column(name = "email", length = 60, nullable = false)
	private String email;

	@NotNull
	@Column(name = "password", length = 255)
	@Convert(converter = EncryptStringConverter.class)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_master", referencedColumnName = "id", nullable = true)
	private UserEntity userMaster;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 5)
	private RoleType role = RoleType.USER;

	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	private DashboardEntity dashboard;

	@NotNull
	@Column(name = "active")
	private Boolean active = false;

	public UserEntity() {
		super();
	}

	public UserEntity(String title, String forename, String surname, String email, String password,
			UserEntity userMaster, RoleType role, DashboardEntity dashboard, Boolean active) {
		super();
		this.title = title;
		this.forename = forename;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.userMaster = userMaster;
		this.role = role;
		this.dashboard = dashboard;
		this.active = active;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void passwordChangeListener(ValueChangeEvent e) {
		this.setPassword((String) e.getNewValue());
	}

	public UserEntity getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserEntity userMaster) {
		this.userMaster = userMaster;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public DashboardEntity getDashboard() {
		return dashboard;
	}

	public void setDashboard(DashboardEntity dashboard) {
		this.dashboard = dashboard;
	}

	public boolean isActive() {
		return active;
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
		UserEntity other = (UserEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}