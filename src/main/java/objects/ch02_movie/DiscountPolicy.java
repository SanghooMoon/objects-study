package objects.ch02_movie;

// 할인 정책
public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
