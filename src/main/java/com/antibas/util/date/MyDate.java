package com.antibas.util.date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyDate {
	private int day, month, year;

	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

}
