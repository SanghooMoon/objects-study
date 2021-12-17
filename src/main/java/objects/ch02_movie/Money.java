package objects.ch02_movie;

import java.math.BigDecimal;

// 금액과 관련된 다양한 계산을 구현하는 클래스
public class Money {
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public static Money wons(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    // 테스트를 위해 getter 추가 FIXME : 테스트 시 형식의 문제가 있어 좋은 방법은 아닌 듯(equals 오버라이딩 등으로 리팩토링할 것)
    public BigDecimal getAmount() {
        return amount;
    }
}
