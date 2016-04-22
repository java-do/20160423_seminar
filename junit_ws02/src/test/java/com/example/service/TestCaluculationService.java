package com.example.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestCaluculationService {

	private CalculationService sut;

	@Before
	public void setUp() {
		sut = new CalculationService();
	}

	@Test
	public void squareが正の数の絶対値の二乗の値を返す() {
		double arg = 5.0;
		double expected = 25.0;

		double actual = sut.square(arg);

		assertThat(actual, is(expected));
	}


	@Test
	public void squareが正の数の絶対値の二乗の値を返す_逆関数() {
		double arg = 5.0;

		double actual = sut.square(arg);

		assertThat(actual / arg, is(arg));
	}

	@Test
	public void squareが正の数の絶対値の二乗の値を返す_クロスチェック() {
		double arg = 5.0;

		double actual = sut.square(arg);

		assertThat(actual, is(arg * arg));
	}

	@Test
	public void squareが負の数の絶対値の二乗の値を返す() {
		double arg = -5.0;
		double expected = 25.0;

		double actual = sut.square(arg);

		assertThat(actual, is(expected));
	}


	@Test
	public void squareが負の数の絶対値の二乗の値を返す_逆関数() {
		double arg = -5.0;

		double actual = sut.square(arg);

		assertThat((actual / arg * (-1.0)), is(arg * (-1.0)));
	}

	@Test
	public void squareが負の数の絶対値の二乗の値を返す_クロスチェック() {
		double arg = -5.0;

		double actual = sut.square(arg);

		assertThat(actual, is(arg * (-1.0) * arg * (-1.0)));
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void insertLogにnullを渡すとExceptionをスローする() throws Exception {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("bean is null.");

		sut.insertLog(null);
	}
}
