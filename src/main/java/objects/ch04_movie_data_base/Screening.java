package objects.ch04_movie_data_base;

import objects.ch02_movie.Money;

import java.time.LocalDateTime;

// 데이터 중심 - 상영
public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    // 영화 가격 계산
    public Money calculateFee(int audienceCount) {
        switch (movie.getMovieType()) {
            case AMOUNT_DISCOUNT:
                return movie.calculateAmountDiscountedFee().times(audienceCount);
            case PERCENT_DISCOUNT:
                return movie.calculatePercentDiscountedFee().times(audienceCount);
            case NONE_DISCOUNT:
                return movie.calculateNoneDiscountedFee().times(audienceCount);
        }

        return movie.calculateNoneDiscountedFee().times(audienceCount);
    }
}
