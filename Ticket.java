import java.io.Serializable;

public class Ticket implements Cloneable, Serializable {

    // default serialVersion id
    private static final long serialVersionUID = 1L;

    String personId;
    Train train;
    SeatType type;
    int cabinNo;
    int seatNo;

    public Ticket(String personId, Train train, SeatType type, int cabinNo, int seatNo) {
        this.personId = personId;
        this.train = train;
        this.type = type;
        this.cabinNo = cabinNo;
        this.seatNo = seatNo;
    }

    public SeatType getType() {
        return this.type;
    }

    public int getCabinNo() {
        return this.cabinNo;
    }

    public int getSeatNo() {
        return this.seatNo;
    }

    public String toString() {
        return "TrainId: " + train.getId() + "\nFrom: " + train.getDepart() + "\nTo: " + train.getDestination()
                + "\nCabinNo: " + cabinNo + "\nSeatNo: " + seatNo;
    }

    public void displayTicket() {
        String s;
        if (type == SeatType.BIZ)
            s = "一等座";
        else
            s = "二等座";
        System.out.format("+%1$s+%n", StringUtils.center("-", 50, '-'));
        System.out.format("|%1$s%2$s%3$s|%n", StringUtils.center(train.getDepart(), 20),
                StringUtils.center(train.getId(), 10), StringUtils.center(train.getDestination(), 20));
        System.out.format("|%1$s%2$s|%n", StringUtils.center("2020年5月1日10:03发车", 30),
                StringUtils.center(cabinNo + "车" + seatNo + "号", 20));
        System.out.format("|%1$s|%n", StringUtils.center(s, 50));
        System.out.format("|%1$s|%n", StringUtils.center("限乘当日当次车", 50));
        System.out.format("|%1$s|%n", StringUtils.center(this.personId, 50));
        System.out.format("+%1$s+%n", StringUtils.center("-", 50, '-'));

    }

    @Override
    public Ticket clone() {
        Ticket clone = null;
        try {
            clone = (Ticket) super.clone();
            clone.train = this.train.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }
}