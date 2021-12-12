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

    public Long hold(Ticket ticket) {
        // 초대장을 가진 관람객 : 초대장 -> 티켓 교환
        if(hasInvitation()) {
            setTicket(ticket);
            return 0L;
        } else { // 초대장이 없는 관람객 : 현금 -> 티켓 구매
            minusAmount(ticket.getFee());
            setTicket(ticket);
            return ticket.getFee();
        }
    }

    // 초대장 보유 여부 판단
    private boolean hasInvitation() {
        return invitation != null;
    }

    // 티켓 보유 여부 판단
    private boolean hasTicket() {
        return ticket != null;
    }

    // 초대장을 티켓으로 교환
    private void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    // 현금 증가
    private void plusAmount(Long amount) {
        this.amount += amount;
    }

    // 현금 감소
    private void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public Long getAmount() {
        return amount;
    }
}
