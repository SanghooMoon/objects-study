package objects.ch02_movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MovieTest {

    private final LocalDateTime 월요일_11시 = LocalDateTime.of(2021,12,13, 11, 0);
    private final LocalDateTime 화요일_17시 = LocalDateTime.of(2021,12,14, 17, 0);
    private final LocalDateTime 목요일_11시 = LocalDateTime.of(2021,12,16, 11, 0);
    private final LocalDateTime 목요일_19시 = LocalDateTime.of(2021,12,16, 19, 0);
    private final LocalDateTime 금요일_11시 = LocalDateTime.of(2021,12,17, 11, 0);
    private final LocalDateTime 토요일_11시 = LocalDateTime.of(2021,12,18, 11, 0);

    private DiscountPolicy 아바타_금액할인정책 = new AmountDiscountPolicy(Money.wons(800),
                                                new SequenceCondition(1),
                                                new SequenceCondition(10),
                                                new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12,0)),
                                                new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(18, 0), LocalTime.of(21,0)));
    private Movie 아바타 = new Movie("아바타", Duration.ofMinutes(120), Money.wons(10000), 아바타_금액할인정책);

    private DiscountPolicy 타이타닉_비율할인정책 = new PercentDiscountPolicy(0.1,
                                                 new SequenceCondition(2),
                                                 new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(17, 0)),
                                                 new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(14, 0)));
    private Movie 타이타닉 = new Movie("타이타닉", Duration.ofMinutes(80), Money.wons(11000), 타이타닉_비율할인정책);

    private DiscountPolicy 할인정책_없음 = new NoneDiscountPolicy();
    private Movie 스타워저 = new Movie("스타워즈", Duration.ofMinutes(50), Money.wons(10000), 할인정책_없음);


    @Test
    void 아바타_할인조건_불일치_가격계산(){
        // given
        Screening 예매할_아바타_상영 = new Screening(아바타, 2, 토요일_11시);

        // when
        Money 금액 = 아바타.calculateMovieFee(예매할_아바타_상영);

        // then
        Assertions.assertEquals(BigDecimal.valueOf(10000), 금액.getAmount(), "할인 X");
    }

    @Test
    void 아바타_할인조건_일치_가격계산(){
        // given
        Screening 아바타_순번조건_일치1 = new Screening(아바타, 1, 토요일_11시);
        Screening 아바타_순번조건_일치2 = new Screening(아바타, 10, 토요일_11시);
        Screening 아바타_기간조건_일치1 = new Screening(아바타, 5, 목요일_19시);
        Screening 아바타_기간조건_일치2 = new Screening(아바타, 10, 월요일_11시);

        // when
        Money 금액1 = 아바타.calculateMovieFee(아바타_순번조건_일치1);
        Money 금액2 = 아바타.calculateMovieFee(아바타_순번조건_일치2);
        Money 금액3 = 아바타.calculateMovieFee(아바타_기간조건_일치1);
        Money 금액4 = 아바타.calculateMovieFee(아바타_기간조건_일치2);

        // then
        Assertions.assertEquals(BigDecimal.valueOf(9200), 금액1.getAmount(), "할인 O");
        Assertions.assertEquals(BigDecimal.valueOf(9200), 금액2.getAmount(), "할인 O");
        Assertions.assertEquals(BigDecimal.valueOf(9200), 금액3.getAmount(), "할인 O");
        Assertions.assertEquals(BigDecimal.valueOf(9200), 금액4.getAmount(), "할인 O");
    }

    @Test
    void 타이타닉_할인조건_불일치_가격계산(){
        // given
        Screening 예매할_타이타닉_상영 = new Screening(타이타닉, 5, 토요일_11시);

        // when
        Money 금액 = 타이타닉.calculateMovieFee(예매할_타이타닉_상영);

        // then
        Assertions.assertEquals(BigDecimal.valueOf(11000), 금액.getAmount(), "할인 X");
    }

    @Test
    void 타이타닉_할인조건_일치_가격계산(){
        // given
        Screening 타이나닉_순번조건_일치1 = new Screening(타이타닉, 2, 토요일_11시);
        Screening 타이타닉_기간조건_일치1 = new Screening(타이타닉, 5, 목요일_11시);
        Screening 타이타닉_기간조건_일치2 = new Screening(타이타닉, 10, 화요일_17시);

        // when
        Money 금액1 = 타이타닉.calculateMovieFee(타이나닉_순번조건_일치1);
        Money 금액2 = 타이타닉.calculateMovieFee(타이타닉_기간조건_일치1);
        Money 금액3 = 타이타닉.calculateMovieFee(타이타닉_기간조건_일치2);

        // then
        Assertions.assertEquals(BigDecimal.valueOf(9900), 금액1.getAmount().setScale(0), "할인 O");
        Assertions.assertEquals(BigDecimal.valueOf(9900), 금액2.getAmount().setScale(0), "할인 O");
        Assertions.assertEquals(BigDecimal.valueOf(9900), 금액3.getAmount().setScale(0), "할인 O");
    }
}
