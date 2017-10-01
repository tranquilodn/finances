package uk.org.tdn.finances.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import uk.org.tdn.finances.entity.CategoryEntity;

@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (CategoryEntity) uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof CategoryEntity) {
			CategoryEntity entity = (CategoryEntity) value;
			if (entity != null && entity instanceof CategoryEntity && entity.getId() != null) {
				uiComponent.getAttributes().put(entity.getId().toString(), entity);
				return entity.getId().toString();
			}
		}
		return "";
	}

}