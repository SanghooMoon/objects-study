package objects.ch04_movie_data_base;

import java.time.DayOfWeek;
import java.time.LocalTime;

// 할인 조건을 구현하는 클래스
public class DiscountCondition {
    private DiscountConditionType type;

    private int sequence;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    // 테스트를 위해 생성자 추가
    public DiscountCondition(DiscountConditionType type, int sequence, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.type = type;
        this.sequence = sequence;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DiscountConditionType getType() {
        return type;
    }

    // 할인 조건을 판단하는 메서드 : 기간 조건
    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
        if(type != DiscountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }

        return this.dayOfWeek.equals(dayOfWeek) &&
               this.startTime.compareTo(time) <= 0 &&
               this.endTime.compareTo(time) >= 0;
    }

    // 할인 조건을 판단하는 메서드(오버로딩) : 순번 조건
    public boolean isDiscountable(int sequence) {
        if(type != DiscountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }

        return this.sequence == sequence;
    }
}
