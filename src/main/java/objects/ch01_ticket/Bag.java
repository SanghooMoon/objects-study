package objects.ch01_ticket;

/**
 * 관람객이 소지품을 보관하는 가방
 */
public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    // 초대장이 없이 현금만 보관
    public Bag(Long amount) {
        this(null, amount);
    }

    // 현금과 초대장을 함께 보관
    public Bag(Invitation invitation, Long amount) {
        this.amount = amount;
        this.invitation = invitation;
    }

    // 초대장 보유 여부 판단
    public boolean hasInvitation() {
        return invitation != null;
    }

    // 티켓 보유 여부 판단
    public boolean hasTicket() {
        return ticket != null;
    }

    // 초대장을 티켓으로 교환
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    // 현금 증가
    public void plusAmount(Long amount) {
        this.amount += amount;
    }

    // 현금 감소
    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public Long getAmount() {
        return amount;
    }
}
