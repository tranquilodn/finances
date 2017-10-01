package uk.org.tdn.finances.service;

import java.util.List;

import uk.org.tdn.finances.entity.PayeeEntity;
import uk.org.tdn.finances.entity.UserEntity;

public interface IPayeeService extends IAbstractService<PayeeEntity, Integer> {

	List<PayeeEntity> findAllByUser(List<UserEntity> users);

}