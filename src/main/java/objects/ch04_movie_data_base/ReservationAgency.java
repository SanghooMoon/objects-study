package objects.ch04_movie_data_base;

import objects.ch02_movie.Money;

// 영화 예매 절차를 구현하는 클래스
public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }
}
