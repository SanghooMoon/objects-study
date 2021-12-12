package objects.ch01_ticket;

// 관람객
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    // 자신의 가방에 초대장이 있는지 스스로 확인 - 외부에 자신의 가방 공개 차단
    public Long buy(Ticket ticket) {
        // 초대장을 가진 관람객 : 초대장 -> 티켓 교환
        if(bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        } else { // 초대장이 없는 관람객 : 현금 -> 티켓 구매
            bag.minusAmount(ticket.getFee());
            bag.setTicket(ticket);
            return ticket.getFee();
        }
    }
}
