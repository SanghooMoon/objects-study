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

    public DiscountConditionType getType() {
        return type;
    }

    public void setType(DiscountConditionType type) {
        this.type = type;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
