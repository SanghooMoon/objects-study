package objects.ch01_ticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TheaterTest {

    @Test
    void enter_초대장_관람객() {
        // given
        Invitation 초대장 = new Invitation();
        Audience 초대장_관람객 = new Audience(new Bag(초대장, 10000L));
        TicketSeller 티켓판매원 = new TicketSeller(new TicketOffice(100000L, new Ticket(1000L)));
        Theater 소극장 = new Theater(티켓판매원);

        // when
        소극장.enter(초대장_관람객);

        //then
        Assertions.assertTrue(초대장_관람객.getBag().hasTicket(), "초대장 관람객은 티켓을 가지고있다");
        Assertions.assertEquals(10000L, 초대장_관람객.getBag().getAmount(), "현금을 지불하지 않았다.");
    }

    @Test
    void enter_일반_관람객() {
        // given
        Audience 일반_관람객 = new Audience(new Bag(null, 10000L));
        TicketSeller 티켓판매원 = new TicketSeller(new TicketOffice(100000L, new Ticket(1000L)));
        Theater 소극장 = new Theater(티켓판매원);

        // when
        소극장.enter(일반_관람객);

        //then
        Assertions.assertTrue(일반_관람객.getBag().hasTicket(), "초대장 관람객은 티켓을 가지고있다");
        Assertions.assertEquals(9000L, 일반_관람객.getBag().getAmount(), "현금을 지불했다.");
    }
}