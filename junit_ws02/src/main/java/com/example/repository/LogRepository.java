package com.example.repository;

import com.example.data.SquareLogBean;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Singleton
public class LogRepository implements ILogRepository {

	private List<SquareLogBean> virtualTable;

	public LogRepository() {
		this.virtualTable = new ArrayList<>();
	}

	@Override
	public synchronized void insertSquareLog(SquareLogBean bean) throws Exception {
		if (bean == null) {
			throw new IllegalArgumentException("bean is null.");
		}
		if (virtualTable.contains(bean)) {
			throw new Exception("bean is duplicated.");
		}
		virtualTable.add(bean);
	}

	@Override
	public List<SquareLogBean> selectSquareLog() {
		List<SquareLogBean> deepCopies = virtualTable.stream()
			.map(src -> new SquareLogBean(src.getValue(), src.getSquare(), src.getTimestamp()))
			.collect(toList());
		Collections.shuffle(deepCopies);
		return deepCopies;
	}
}
