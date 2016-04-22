package com.example.service;

import com.example.data.SquareLogBean;
import com.example.repository.ILogRepository;
import com.google.inject.Inject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CalculationService implements ICalculationService {

	@Inject
	private ILogRepository logRepository;

	@Override
	public double square(double value) {
		return Math.pow(Math.abs(value), 2.0);
	}

	@Override
	public void insertLog(SquareLogBean bean) throws Exception {
		if (bean == null) {
			throw new IllegalArgumentException("bean is null.");
		}
		logRepository.insertSquareLog(bean);
	}

	@Override
	public List<SquareLogBean> fetchLogsLimit5() {
		List<SquareLogBean> selected = logRepository.selectSquareLog();
		List<SquareLogBean> limited = selected.stream()
			.sorted(Comparator.comparing(SquareLogBean::getTimestamp).reversed())
			.limit(5)
			.collect(Collectors.toList());
		return limited;
	}
}
