package objects.ch01_ticket;

// 관람객
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    // 자신의 가방에 초대장이 있는지 스스로 확인 - 외부에 자신의 가방 공개 차단
    public Long buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
