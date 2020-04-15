import java.util.*;

public class AppMem {

    static ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
    static ArrayList<Train> trainList = new ArrayList<Train>();

    public static List<Train> getTrainList() {
        List<Train> list = new ArrayList<Train>();
        for (Train train : trainList) {
            list.add(train.clone());
        }
        return list;
    }

    public static List<Ticket> getTicketList() {
        List<Ticket> list = new ArrayList<Ticket>();
        for (Ticket ticket : ticketList) {
            list.add(ticket.clone());
        }
        return list;
    }

    public static void addTicket(Ticket ticket) {
        ticketList.add(ticket);
    }

    public static void addTrain(Train train) {
        trainList.add(train);
    }

}