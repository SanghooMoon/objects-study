package objects.ch04_movie_data_base;

import objects.ch02_movie.Money;

// 영화 예매 절차를 구현하는 클래스
public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie(); // 예매할 영화 가져오기

        boolean discountable = false; // 할인이 가능한지 확인하는 변수
        // 할인조건들을 탐색
        for(DiscountCondition condition : movie.getDiscountConditionList()) {
            // 할인조건이 "상영 시간" 이라면
            if (condition.getType() == DiscountConditionType.PERIOD) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                        condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                        condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
            } else { // "상영 순번"
                discountable = condition.getSequence() == screening.getSequence();
            }

            if (discountable) {
                break;
            }
        }

        Money fee;  // 금액
        if(discountable) {  // 할인이 가능하다면 할인 금액 계산 후 비용 계싼
            Money discountAmount = Money.ZERO;
            switch (movie.getMovieType()) {
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
            }
            fee = movie.getFee().minus(discountAmount);
        } else { // 할인이 안되면 원가
            fee = movie.getFee();
        }

        return new Reservation(customer, screening, fee, audienceCount);
    }
}
