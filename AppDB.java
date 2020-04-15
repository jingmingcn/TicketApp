import java.util.*;
import java.io.*;

public class AppDB {

    static ArrayList<Ticket> ticketList;
    static ArrayList<Train> trainList;

    static {
        try {
            File ticketDBFile = new File("ticket.db");
            if (ticketDBFile.exists()) {
                InputStream is = new FileInputStream(ticketDBFile);
                ObjectInputStream ois = new ObjectInputStream(is);
                ticketList = (ArrayList<Ticket>) ois.readObject();
                ois.close();
                is.close();
            } else {
                ticketDBFile.createNewFile();
                ticketList = new ArrayList<Ticket>();
                OutputStream os = new FileOutputStream(ticketDBFile);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(ticketList);
                oos.close();
                os.close();
            }

            File trainDBFile = new File("train.db");
            if (trainDBFile.exists()) {
                InputStream is = new FileInputStream(trainDBFile);
                ObjectInputStream ois = new ObjectInputStream(is);
                trainList = (ArrayList<Train>) ois.readObject();
                ois.close();
                is.close();
            } else {
                trainDBFile.createNewFile();
                trainList = new ArrayList<Train>();
                OutputStream os = new FileOutputStream(trainDBFile);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(trainList);
                oos.close();
                os.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

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
        flushTicketDB();
    }

    public static void addTrain(Train train) {
        trainList.add(train);
        flushTrainDB();
    }

    private static void flushTicketDB() {
        File ticketDBFile = new File("ticket.db");

        try {
            OutputStream os = new FileOutputStream(ticketDBFile);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(ticketList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void flushTrainDB() {
        File trainDBFile = new File("train.db");

        try {
            OutputStream os = new FileOutputStream(trainDBFile);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(trainList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}