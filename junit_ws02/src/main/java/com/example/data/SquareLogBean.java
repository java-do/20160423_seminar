package com.example.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 二乗の計算結果を格納するBean, 同値関係のオブジェクトは同一とみなす
 */
public class SquareLogBean implements Serializable {

	private Double value;
	private Double square;
	private LocalDateTime timestamp;

	public SquareLogBean(Double value, Double square, LocalDateTime timestamp) {
		this.value = value;
		this.square = square;
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SquareLogBean other = (SquareLogBean) obj;
		return Objects.equals(value, other.value)
			&& Objects.equals(square, other.square)
			&& Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, square, timestamp);
	}

	@Override
	public String toString() {
		return "SquareLogBean(super="
			+ super.toString()
			+ ", value="
			+ value
			+ ", square="
			+ square
			+ ", timestamp="
			+ timestamp + ")";
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
