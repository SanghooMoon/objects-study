package objects.ch02_movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 할인 정책 - 추상 클래스
public abstract class DiscountPolicy {
    // 하나의 할인 정책은 여러 개의 할인 조건을 포함 가능
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    // 할인 금액 계산
    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
