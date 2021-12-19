package objects.ch04_movie_data_base;

import objects.ch02_movie.Money;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

// 데이터 중심의 영화 예매 시스템 설계하기 - 영화
public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditionList;

    private MovieType movieType;    // 할인 정책의 종류를 정하는 타입
    private Money discountAmount;
    private double discountPercent;

    public MovieType getMovieType() {
        return movieType;
    }

    // 정책에 맞는 할인 금액 계산 메서드 : 금액 할인
    public Money calculateAmountDiscountedFee() {
        if(movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee.minus(discountAmount);
    }

    // 정책에 맞는 할인 금액 계산 메서드 : 비율 할인
    public Money calculatePercentDiscountedFee() {
        if(movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee.minus(fee.times(discountPercent));
    }

    // 정책에 맞는 할인 금액 계산 메서드 : 할인 X
    public Money calculateNoneDiscountedFee() {
        if(movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee;
    }

    // 할인 여부를 판단하는 메서드 추가
    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for(DiscountCondition condition : discountConditionList) {
            if(condition.getType() == DiscountConditionType.PERIOD) {
                if(condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
                    return true;
                }
            } else {
                if(condition.isDiscountable(sequence)) {
                    return true;
                }
            }
        }
        return false;
    }
}
