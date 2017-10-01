package uk.org.tdn.finances.service;

import java.util.List;

import uk.org.tdn.finances.entity.TransactionEntity;
import uk.org.tdn.finances.entity.UserEntity;

public interface ITransactionService extends IAbstractService<TransactionEntity, Long> {

	List<TransactionEntity> findAllByUser(List<UserEntity> users);

}