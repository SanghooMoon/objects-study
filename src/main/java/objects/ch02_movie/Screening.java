package objects.ch02_movie;

import java.time.LocalDateTime;

// 사용자들이 예매하는 대상인 "상영"
public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    // 영화 순번이 일치하는지 확인하는 메서드
    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    // 상영 시작 시간을 반환하는 메서드
    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    // 기본 요금을 반환하는 메서드
    public Money getMovieFee() {
        return movie.getFee();
    }

    // 영화를 예매하는 메서드
    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    // 요금을 계산하는 메서드
    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }

}
