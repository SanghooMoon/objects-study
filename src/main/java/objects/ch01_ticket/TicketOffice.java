package objects.ch01_ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 티켓을 판매/교환하는 매표소
public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    private Ticket getTicket() {
        return tickets.remove(0);
    }

    private void plusAmount(Long amount) {
        this.amount += amount;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buy(getTicket()));
    }
}
