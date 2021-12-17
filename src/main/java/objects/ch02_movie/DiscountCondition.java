package objects.ch02_movie;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
