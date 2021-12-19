package objects.ch05_GRASP_pattern;

// 할인 정책의 종류를 정하는 타입
public enum MovieType {
    AMOUNT_DISCOUNT,    // 금액 할인 정책
    PERCENT_DISCOUNT,   // 비율 할인 정책
    NONE_DISCOUNT       // 미적용
}
