package com.example.service;

import com.example.data.SquareLogBean;

import java.util.Collections;
import java.util.List;

public class CalculationService implements ICalculationService {

	@Override
	public Double square(Double value) {
		if(value == null) {
			throw new IllegalArgumentException("value is null.");
		}
		return Math.pow(Math.abs(value), 2.0);
	}

	@Override
	public List<SquareLogBean> fetchLogsLimit5() {
		return Collections.emptyList();
	}
}
