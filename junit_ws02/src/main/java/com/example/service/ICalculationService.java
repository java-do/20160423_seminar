package com.example.service;

import com.google.inject.ImplementedBy;

@ImplementedBy(CalculationService.class)
public interface ICalculationService {

	/**
	 * 引数の絶対値を二乗して返す
	 * @param value {@link Double}
	 * @return 二乗された value
	 * @throws IllegalArgumentException 二乗できない値が渡されたとき
	 */
	Double square(Double value);

}
