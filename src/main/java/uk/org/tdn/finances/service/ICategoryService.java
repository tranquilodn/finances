package uk.org.tdn.finances.service;

import java.util.List;

import uk.org.tdn.finances.entity.CategoryEntity;
import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.entity.enums.CategoryType;

public interface ICategoryService extends IAbstractService<CategoryEntity, Integer> {

	List<CategoryEntity> findAllByUser(List<UserEntity> users);

	List<CategoryEntity> findByCategoryTypeOrderByNameEnGbAsc(CategoryType categoryType);

	List<CategoryEntity> findByCategoryTypeOrderByNamePtBrAsc(CategoryType categoryType);

}