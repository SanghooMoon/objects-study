package objects.ch01_ticket;

// 소극장
public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    // 관람객을 맞이하는 메서드
    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}
