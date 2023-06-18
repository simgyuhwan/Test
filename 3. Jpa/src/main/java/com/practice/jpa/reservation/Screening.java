package com.practice.jpa.reservation;

import java.time.LocalDateTime;

/**
 * 상영 클래스
 *
 * 영화 이름
 * 순서
 * 시간
 *
 */
public class Screening {
	private Movie movie;
	private int sequence;
	private LocalDateTime whenScreened;

	public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
		this.movie = movie;
		this.sequence = sequence;
		this.whenScreened = whenScreened;
	}

	/**
	 * 시작 시간
	 */
	public LocalDateTime getStartTime() {
		return whenScreened;
	}

	/**
	 * 순선 일치 여부
	 */
	public boolean isSequence(int sequence) {
		return this.sequence == sequence;
	}

	/**
	 * 영화 가격
	 */
	public Money getMovieFee() {
		return movie.getFee();
	}

	public Reservation reserve(Customer customer, int audienceCount) {
		return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
	}

	private Money calculateFee(int audienceCount) {
		return movie.calculateMovieFee(this).times(audienceCount);
	}

}
