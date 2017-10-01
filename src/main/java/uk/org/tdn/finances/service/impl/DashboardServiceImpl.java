package uk.org.tdn.finances.service.impl;

import javax.enterprise.inject.Default;

import uk.org.tdn.finances.entity.DashboardEntity;
import uk.org.tdn.finances.service.IDashboardService;

@Default
public class DashboardServiceImpl extends AbstractServiceImpl<DashboardEntity, Integer> implements IDashboardService {

	private static final long serialVersionUID = 2218318327219215858L;

	public DashboardServiceImpl() {
		super(DashboardEntity.class);
	}

}