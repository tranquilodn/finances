package uk.org.tdn.finances.util.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import uk.org.tdn.finances.entity.enums.RecordType;

@FacesConverter(value = "recordTypeConverter")
public class RecordTypeConverter extends EnumConverter {

	public RecordTypeConverter() {
		super(RecordType.class);
	}

}
