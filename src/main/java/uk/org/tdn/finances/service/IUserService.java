package uk.org.tdn.finances.service;

import java.util.List;

import uk.org.tdn.finances.entity.UserEntity;

public interface IUserService extends IAbstractService<UserEntity, Integer> {

	UserEntity findByEmail(String email);

	UserEntity findByEmailAndPassword(String email, String password);

	List<UserEntity> findByIdOrUserMaster(Integer id, UserEntity userMaster);

}