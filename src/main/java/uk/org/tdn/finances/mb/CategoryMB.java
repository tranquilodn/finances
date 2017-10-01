package uk.org.tdn.finances.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.CategoryEntity;
import uk.org.tdn.finances.entity.enums.CategoryType;
import uk.org.tdn.finances.service.ICategoryService;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@SessionScoped
@Named(value = "categoryMB")
public class CategoryMB extends AbstractMB<CategoryEntity, Integer> implements Serializable {

	private static final long serialVersionUID = 3624531238974823354L;

	@Inject
	private ICategoryService service;

	@Inject
	private CategoryEntity category;

	public CategoryMB() {
	}

	@PostConstruct
	public void init() {
		super.init();
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public CategoryType[] getCategoryTypes() {
		return CategoryType.values();
	}

	@Override
	public void prepareEdit() {
		if (getId() != null) {
			this.category = service.findById(getId());
		} else {
			this.category = new CategoryEntity();
		}
	}

	@Override
	public void prepareView() {
		if (getId() != null)
			this.category = service.findById(getId());
	}

	@Override
	public void actionSave(ActionEvent evt) throws IOException {
		try {
			if (!super.isManaged()) {
				category.setUser(JsfUtils.getSessionUserLoggedIn());
			}
			service.save(category);
			super.entityEventSrc.fire(category);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao salvar mercadoria.", ex);
		}
		JsfUtils.pageRedirect(evt);
	}

	@Override
	public void actionRemove(ActionEvent evt) throws IOException {
		try {
			service.remove(category);
			super.entityEventSrc.fire(category);
			super.getConversation().end();
		} catch (Exception ex) {
			// log.error("Erro ao remover mercadoria.", ex);
		}
		// log.debug("Removeu mercadoria "+mercadoria.getId());
		JsfUtils.pageRedirect(evt);
	}

}