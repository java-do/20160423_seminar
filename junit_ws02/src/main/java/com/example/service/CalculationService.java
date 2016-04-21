package com.example.service;

public class CalculationService implements ICalculationService {

	@Override
	public Double square(Double value) {
		if(value == null) {
			throw new IllegalArgumentException("value is null.");
		}
		return Math.pow(Math.abs(value), 2.0);
	}
}
