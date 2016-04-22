package com.example.service;

import com.example.data.SquareLogBean;
import com.example.repository.ILogRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MockingTestCalculationService {

	@InjectMocks
	private CalculationService sut;

	@Mock
	private ILogRepository mock;

	private Clock clock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Instant instant = Instant.parse("2016-04-23T13:00:00Z");
		clock = Clock.fixed(instant, ZoneId.systemDefault());
	}

	public List<SquareLogBean> getRecords() {
		List<SquareLogBean> records = new ArrayList<>();
		records.add(new SquareLogBean(5.0, 25.0, LocalDateTime.now(clock).plusMinutes(5)));
		records.add(new SquareLogBean(4.0, 16.0, LocalDateTime.now(clock).plusMinutes(4)));
		records.add(new SquareLogBean(3.0, 9.0, LocalDateTime.now(clock).plusMinutes(3)));
		records.add(new SquareLogBean(2.0, 4.0, LocalDateTime.now(clock).plusMinutes(2)));
		records.add(new SquareLogBean(1.0, 1.0, LocalDateTime.now(clock).plusMinutes(1)));
		records.add(new SquareLogBean(0.0, 0.0, LocalDateTime.now(clock).plusMinutes(0)));
		return records;
	}

	@Test
	public void fetchLogsLimit5で6個以上の要素が返ってきても5個だけ返す() throws Exception {
		when(mock.selectSquareLog()).thenReturn(getRecords());

		int actual = sut.fetchLogsLimit5().size();

		verify(mock, times(1)).selectSquareLog();
		assertThat(actual, is(5));
	}

	@Test
	public void fetchLogsLimit5でランダムな要素が返ってきてもtimestampで降順ソートして返す() throws Exception {
		List<SquareLogBean> records = getRecords();
		List<SquareLogBean> copied = new ArrayList<>(records);
		Collections.shuffle(copied);
		when(mock.selectSquareLog()).thenReturn(copied);
		List<SquareLogBean> expected = new ArrayList<>(records);
		expected.remove(5);

		List<SquareLogBean> actual = sut.fetchLogsLimit5();

		verify(mock, times(1)).selectSquareLog();
		assertThat(actual, is(expected));
	}

	@Test
	public void insertLogにBeanを渡すとExceptionが発生しない() throws Exception {
		SquareLogBean bean = new SquareLogBean(1.0, 1.0, LocalDateTime.now());
		doNothing().when(mock).insertSquareLog(bean);

		sut.insertLog(bean);

		verify(mock, times(1)).insertSquareLog(bean);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void insertLogにnullを渡すと処理を中断してExceptionをスローする() throws Exception {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("bean is null.");

		sut.insertLog(null);
	}

	@Test
	public void insertLogでデータベースへのinsertに失敗するとそのExceptionをそのままスローする() throws Exception {
		SquareLogBean bean = new SquareLogBean(1.0, 1.0, LocalDateTime.now());
		doThrow(new Exception("データベースの接続に失敗しました.")).when(mock).insertSquareLog(any());
		exception.expect(Exception.class);
		exception.expectMessage("データベースの接続に失敗しました.");

		sut.insertLog(bean);
	}

}
