package objects.ch04_movie_data_base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DiscountConditionTest {
    // 기간조건 : 월요일 11시 ~ 13시
    final DiscountCondition 기간조건_월요일_11시_13시 = new DiscountCondition(DiscountConditionType.PERIOD,
            0, DayOfWeek.MONDAY,
            LocalTime.of(11, 0), LocalTime.of(13, 0));

    // 순번조건 : 16
    final DiscountCondition 순번조건_16 = new DiscountCondition(DiscountConditionType.SEQUENCE,
            16, null, null, null);


    @Test
    void 기간조건_일치시_TRUE() {
        Assertions.assertTrue(기간조건_월요일_11시_13시.isDiscountable(DayOfWeek.MONDAY, LocalTime.of(12,0)));
    }
    @Test
    void 기간조건_불일치시_FALSE() {
        Assertions.assertFalse(기간조건_월요일_11시_13시.isDiscountable(DayOfWeek.MONDAY, LocalTime.of(13,1)));
        Assertions.assertFalse(기간조건_월요일_11시_13시.isDiscountable(DayOfWeek.THURSDAY, LocalTime.of(12,0)));
    }
    @Test
    void 기간조건인데_순번조건_확인요청시_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            기간조건_월요일_11시_13시.isDiscountable(1);
        });
    }

    @Test
    void 순번조건_일치() {
        Assertions.assertTrue(순번조건_16.isDiscountable(16));
    }
    @Test
    void 순번조건_불일치() {
        Assertions.assertFalse(순번조건_16.isDiscountable(15));
    }
    @Test
    void 순번조건인데_기간조건_확인요청시_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            순번조건_16.isDiscountable(DayOfWeek.THURSDAY, LocalTime.of(12,0));
        });
    }
}