package com.siemens.service;

import java.util.Map;

import com.siemens.dao.PickUpPointDao;

public class PickUpService {
	private static volatile PickUpService pickUpService = null;
	private static PickUpPointDao pickUpDao = null;

	private PickUpService() {
		pickUpDao = new PickUpPointDao();
	}

	public static Map<Integer, String> getAllPickUpLatLong() {
		return pickUpDao.getAllPickUpLatLong();
	}

	public static PickUpService getInstance() {

		if (null == pickUpService) {
			pickUpService = new PickUpService();
		}

		return pickUpService;
	}
}
