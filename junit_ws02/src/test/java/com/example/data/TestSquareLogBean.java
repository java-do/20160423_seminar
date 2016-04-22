package com.example.data;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestSquareLogBean {

	private Clock clock;

	@Before
	public void setUp() {
		Instant instant = Instant.parse("2016-04-23T13:00:00Z");
		clock = Clock.fixed(instant, ZoneId.systemDefault());
	}

	@Test
	public void 同値関係は同一とみなす() {
		LocalDateTime time1 = LocalDateTime.now(clock);
		LocalDateTime time2 = LocalDateTime.now(clock);
		SquareLogBean sut = new SquareLogBean(5.0, 25.0, time1);
		SquareLogBean other = new SquareLogBean(5.0, 25.0, time2);

		boolean actual = sut.equals(other);

		assertThat(actual, is(true));
	}

	@Test
	public void 同値関係でなければ同一とみなさない() {
		LocalDateTime time1 = LocalDateTime.now(clock);
		LocalDateTime time2 = LocalDateTime.now(clock).plusMinutes(1);
		SquareLogBean sut = new SquareLogBean(5.0, 25.0, time1);
		SquareLogBean other = new SquareLogBean(5.0, 25.0, time2);

		boolean actual = sut.equals(other);

		assertThat(actual, is(false));
	}


}
