package com.example.data;

import java.time.LocalDateTime;

public class SquareLogBean {

	private Double value;
	private Double square;
	private LocalDateTime timestamp;

	public SquareLogBean(Double value, Double square, LocalDateTime timestamp) {
		this.value = value;
		this.square = square;
		this.timestamp = timestamp;
	}

	public final Double getValue() {
		return value;
	}

	public final Double getSquare() {
		return square;
	}

	public final LocalDateTime getTimestamp() {
		return timestamp;
	}
}
