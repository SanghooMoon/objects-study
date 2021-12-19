package objects.ch05_GRASP_pattern;

import objects.ch02_movie.Money;

import java.time.Duration;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;    // 할인 정책의 종류를 정하는 타입
    private Money discountAmount;
    private double discountPercent;

    public MovieType getMovieType() {
        return movieType;
    }

    public Money calculateAmountDiscounted() {
        return discountAmount;
    }

    public Money calculatePercentDiscounted() {
        return fee.times(discountPercent);
    }

    public Money calculateNoneDiscounted() {
        return Money.ZERO;
    }

    public Money calculateMovieFee(Screening screening) {
        if(isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }
        return fee;
    }

    private Money calculateDiscountAmount() {
        switch(movieType) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscounted();
            case PERCENT_DISCOUNT:
                return calculatePercentDiscounted();
            case NONE_DISCOUNT:
                return calculateNoneDiscounted();
        }

        throw new IllegalStateException();
    }

    public boolean isDiscountable(Screening screening) {
        return discountConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
    }
}
