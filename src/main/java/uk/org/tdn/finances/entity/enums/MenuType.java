package uk.org.tdn.finances.entity.enums;

public enum MenuType {

	G("Menu Group", "Grupo de Menu"), I("Menu Item", "Item de Menu");

	private final String labelEnGb;
	private final String labelPtBr;

	private MenuType(String labelEnGb, String labelPtBr) {
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
