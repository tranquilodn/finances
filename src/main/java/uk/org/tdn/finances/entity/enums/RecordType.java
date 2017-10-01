package uk.org.tdn.finances.entity.enums;

public enum RecordType {

	S("Sporadic", "Esporádico"),
	W("Weekly", "Semanal"),
	F("Fortnightly", "Quinzenal"),
	M("Monthly", "Mensal");

	private final String labelEnGb;
	private final String labelPtBr;

	private RecordType(String labelEnGb, String labelPtBr) {
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
