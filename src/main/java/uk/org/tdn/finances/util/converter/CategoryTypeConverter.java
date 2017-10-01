package uk.org.tdn.finances.util.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import uk.org.tdn.finances.entity.enums.CategoryType;

@FacesConverter(value = "categoryTypeConverter")
public class CategoryTypeConverter extends EnumConverter {

	public CategoryTypeConverter() {
		super(CategoryType.class);
	}

}
