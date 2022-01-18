package objects.ch06_message_interface;

import java.time.Duration;
import java.time.LocalDateTime;

// 이벤트 개념을 구현한 클래스
public class Event {
    private String subject;
    private LocalDateTime from;
    private Duration duration;

    public Event(String subject, LocalDateTime from, Duration duration) {
        this.subject = subject;
        this.from = from;
        this.duration = duration;
    }

}
