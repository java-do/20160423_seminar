package com.example.repository;

import com.example.data.SquareLogBean;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(LogRepository.class)
public interface ILogRepository {

	void insertSquareLog(SquareLogBean bean) throws Exception;

	List<SquareLogBean> selectSquareLog();

}
