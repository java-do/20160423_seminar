package com.example.service;

import com.google.inject.ImplementedBy;

@ImplementedBy(CalculationService.class)
public interface ICalculationService {

	public int add(double a, double b);

}
