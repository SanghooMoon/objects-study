package objects.ch06_message_interface;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void 이벤트가_반복조건_만족하는지_체크() {
        // 매주 수요일 10:30부터 30분 진행되는 회의
        RecurringSchedule schedule = new RecurringSchedule("회의", DayOfWeek.WEDNESDAY, LocalTime.of(10,30), Duration.ofMinutes(30));
        // 2019/05/08 10:30 회의 이벤트
        Event meeting = new Event("회의", LocalDateTime.of(2019,5,8,10,30), Duration.ofMinutes(30));

        // 해당 이벤트는 반복 일정에 만족하는가?
        assertTrue(meeting.isSatisfied(schedule));
    }

    @Test
    void 이벤트가_반복조건_만족하는지_체크2() {
        RecurringSchedule schedule = new RecurringSchedule("회의", DayOfWeek.WEDNESDAY, LocalTime.of(10,30), Duration.ofMinutes(30));
        Event meeting = new Event("회의", LocalDateTime.of(2019,5,9,10,30), Duration.ofMinutes(30));

        assertFalse(meeting.isSatisfied(schedule));
        assertTrue(meeting.isSatisfied(schedule)); // False가 나와야하는데 True가 나온다..  그 이유는 isSatisfied 메서드 내 Event를 변경하기 때문
    }


}