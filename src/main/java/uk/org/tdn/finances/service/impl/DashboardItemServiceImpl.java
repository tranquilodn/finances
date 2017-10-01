package uk.org.tdn.finances.service.impl;

import javax.enterprise.inject.Default;

import uk.org.tdn.finances.entity.DashboardItemEntity;
import uk.org.tdn.finances.service.IDashboardItemService;

@Default
public class DashboardItemServiceImpl extends AbstractServiceImpl<DashboardItemEntity, Integer>
		implements IDashboardItemService {

	private static final long serialVersionUID = 6095044305655113378L;

	public DashboardItemServiceImpl() {
		super(DashboardItemEntity.class);
	}

}