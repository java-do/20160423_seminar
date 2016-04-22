package com.example.service;

import com.example.data.SquareLogBean;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(CalculationService.class)
public interface ICalculationService {

	/**
	 * 引数の絶対値を二乗して返す
	 * @param value {@link Double}
	 * @return 二乗された value
	 * @throws IllegalArgumentException nullが渡されたとき
	 */
	Double square(Double value);

	/**
	 * これまでに計算したログから、最新の5件だけを返す
	 * @return 最新5件分の {@link SquareLogBean} の {@link List}、エラー時は {@link java.util.Collections#EMPTY_LIST}
	 */
	List<SquareLogBean> fetchLogsLimit5();

}
