package com.example.repository;

import com.example.data.SquareLogBean;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestLogRepository {

	private LogRepository sut;
	private Clock clock;

	@Before
	public void setup() {
		sut = new LogRepository();
		Instant instant = Instant.parse("2016-04-23T13:00:00Z");
		clock = Clock.fixed(instant, ZoneId.systemDefault());
	}

	@Test
	public void SquareLogを取り出せる() throws Exception {
		List<SquareLogBean> selected = sut.selectSquareLog();

		int actual = selected.size();

		assertThat(actual, is(0));
	}

	@Test
	public void SquareLogを1つインサートできる() throws Exception {
		SquareLogBean item = new SquareLogBean(5.0, 2.5, LocalDateTime.now(clock));

		sut.insertSquareLog(item);
		int actual = sut.selectSquareLog().size();

		assertThat(actual, is(1));
	}

	@Test
	public void SquareLogを複数インサートできる() throws Exception {
		SquareLogBean item0 = new SquareLogBean(5.0, 2.5, LocalDateTime.now(clock));
		SquareLogBean item1 = new SquareLogBean(2.0, 4.0, LocalDateTime.now(clock).plusHours(1));

		sut.insertSquareLog(item0);
		sut.insertSquareLog(item1);
		int actual = sut.selectSquareLog().size();

		assertThat(actual, is(2));
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void nullはインサートできない() throws Exception {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("bean is null.");

		sut.insertSquareLog(null);
	}

	@Test
	public void 同値のSquareLogBeanはインサートできない() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage("bean is duplicated.");

		SquareLogBean item0 = new SquareLogBean(5.0, 2.5, LocalDateTime.now(clock));
		SquareLogBean item1 = new SquareLogBean(5.0, 2.5, LocalDateTime.now(clock));

		sut.insertSquareLog(item0);
		sut.insertSquareLog(item1);
	}

}
