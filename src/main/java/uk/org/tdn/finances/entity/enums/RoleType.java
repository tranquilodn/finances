package uk.org.tdn.finances.entity.enums;

public enum RoleType {

	ADMIN("Admin", "Admin"), USER("User", "Usuário");

	private final String labelEnGb;
	private final String labelPtBr;

	private RoleType(String labelEnGb, String labelPtBr) {
		this.labelEnGb = labelEnGb;
		this.labelPtBr = labelPtBr;
	}
	
	public String getLabelEnGb() {
		return labelEnGb;
	}

	public String getLabelPtBr() {
		return labelPtBr;
	}

}
