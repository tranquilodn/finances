package uk.org.tdn.finances.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import uk.org.tdn.finances.entity.CategoryEntity;
import uk.org.tdn.finances.entity.enums.CategoryType;
import uk.org.tdn.finances.service.ICategoryService;

@SessionScoped
@Named(value = "categoryList")
public class CategoryList extends AbstractList<CategoryEntity> {

	private static final long serialVersionUID = -5171641188983780713L;

	private List<CategoryEntity> categoryRevenueEnGbListCombo;
	private List<CategoryEntity> categoryRevenuePtBrListCombo;
	private List<CategoryEntity> categoryExpenditureEnGbListCombo;
	private List<CategoryEntity> categoryExpenditurePtBrListCombo;

	@Inject
	private ICategoryService service;

	@Inject
	private UserList userList;

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final CategoryEntity entity) {
		this.retrieveAll();
	}

	@Override
	@PostConstruct
	public void retrieveAll() {
		super.list = service.findAllByUser(userList.getAccountUsers());
		this.categoryRevenueEnGbListCombo = service.findByCategoryTypeOrderByNameEnGbAsc(CategoryType.R);
		this.categoryRevenuePtBrListCombo = service.findByCategoryTypeOrderByNamePtBrAsc(CategoryType.R);
		this.categoryExpenditureEnGbListCombo = service.findByCategoryTypeOrderByNameEnGbAsc(CategoryType.E);
		this.categoryExpenditurePtBrListCombo = service.findByCategoryTypeOrderByNamePtBrAsc(CategoryType.E);
	}

	public List<CategoryEntity> getCategoryRevenueEnGbListCombo() {
		return this.categoryRevenueEnGbListCombo;
	}

	public List<CategoryEntity> getCategoryRevenuePtBrListCombo() {
		return this.categoryRevenuePtBrListCombo;
	}

	public List<CategoryEntity> getCategoryExpenditureEnGbListCombo() {
		return this.categoryExpenditureEnGbListCombo;
	}

	public List<CategoryEntity> getCategoryExpenditurePtBrListCombo() {
		return this.categoryExpenditurePtBrListCombo;
	}

}