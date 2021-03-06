package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.CarModel;

@Service
public class CarInMemoryService implements CarService {
	private List<CarModel> archiveCar;

	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}

	@Override
	public void addcar(CarModel car) {
		archiveCar.add(car);
	}

	@Override
	public List<CarModel> getCarList() {
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		for (CarModel model : archiveCar) {
			if (model.getId().equals(id)) {
				return model;
			}
		}
		return null;	
	}
}
