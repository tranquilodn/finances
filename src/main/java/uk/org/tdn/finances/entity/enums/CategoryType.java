package uk.org.tdn.finances.entity.enums;

public enum CategoryType {

	R("Revenue", "Receita"), E("Expenditure", "Despesa");

	private final String labelEnGb;
	private final String labelPtBr;

	private CategoryType(String labelEnGb, String labelPtBr) {
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