package objects.ch04_movie_data_base;

import java.time.Duration;
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

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditionList() {
        return discountConditionList;
    }

    public void setDiscountConditionList(List<DiscountCondition> discountConditionList) {
        this.discountConditionList = discountConditionList;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
}
