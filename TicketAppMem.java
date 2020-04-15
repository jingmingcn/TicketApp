
import java.util.*;
import java.io.*;

public class TicketAppMem {

	static Scanner scanner = new Scanner(System.in);
	static Console console = System.console();
	static String fmt = "";

	public void run() {
		String cmd = null;
		do {
			this.printMainMenu();
			cmd = scanner.nextLine();
			switch (cmd) {
				case "1":
					addTrain();
					break;
				case "2":
					sellTicket();
					break;
				case "Q":
					break;
				default:
					System.out.println("INVALID INPUT!");
					break;
			}
		} while (!"Q".equalsIgnoreCase(cmd));
		scanner.close();
	}

	private void printMainMenu() {
		String fmt = "%1$-4s%2$-50s%n";

		console.format(fmt, "[1]", "Add a Train");
		console.format(fmt, "[2]", "Sell a Ticket");
		console.format(fmt, "[Q]", "Exit");
	}

	private void sellTicket() {

		listTrains();
		System.out.print("Choose The Train: ");
		int index = Integer.parseInt(scanner.nextLine());
		Train train = AppMem.getTrainList().get(index - 1);

		System.out.print(this.SELL_TICKET_MENU_1);
		String personId = scanner.nextLine();

		System.out.print(this.SELL_TICKET_MENU_2);
		SeatType type;
		if ("1".equals(scanner.nextLine())) {
			type = SeatType.BIZ;
		} else {
			type = SeatType.ECO;
		}

		int rs[] = randomTicket(train, type);

		Ticket ticket = new Ticket(personId, train, type, rs[0], rs[1]);
		// System.out.println(ticket.toString());
		ticket.displayTicket();
		AppMem.addTicket(ticket);
	}

	private void addTrain() {

		System.out.print("Train Id: ");
		String id = scanner.nextLine();
		System.out.print("Depart: ");
		String depart = scanner.nextLine();
		System.out.print("Destination: ");
		String destination = scanner.nextLine();
		System.out.print("BIZ Cabin Number: ");
		int bizCabinNumber = Integer.parseInt(scanner.nextLine());
		System.out.print("BIZ Cabin Seat Number: ");
		int bizCabinSeatNumber = Integer.parseInt(scanner.nextLine());
		System.out.print("ECO Cabin Number: ");
		int ecoCabinNumber = Integer.parseInt(scanner.nextLine());
		System.out.print("ECO Cabin Seat Number: ");
		int ecoCabinSeatNumber = Integer.parseInt(scanner.nextLine());

		Train train = new Train(id, depart, destination, bizCabinNumber, ecoCabinNumber, bizCabinSeatNumber,
				ecoCabinSeatNumber);
		AppMem.addTrain(train);
		listTrains();
	}

	private void listTrains() {
		List<Train> trainList = AppMem.getTrainList();
		String fmt = "|%1$4s|%2$6s|%3$15s|%4$15s|%5$10s|%6$10s|%n";
		console.format("+----+------+---------------+---------------+----------+----------+%n");
		console.format(fmt, "", "ID", "Depart", "Destination", "BizSeats", "EcoSeats");
		console.format("+----+------+---------------+---------------+----------+----------+%n");
		int i = 1;
		for (Train train : trainList) {
			console.format(fmt, i++, train.getId(), train.getDepart(), train.getDestination(),
					train.getBizCabinNumber() * train.getBizCabinSeatNumber(),
					train.getEcoCabinNumber() * train.getEcoCabinSeatNumber());
		}
		console.format("+----+------+---------------+---------------+----------+----------+%n");
	}

	// TODO this method will break into infinit loop if no ticket is remainded.
	private int[] randomTicket(Train train, SeatType type) {

		int rs[] = new int[2];
		Random random = new Random();
		boolean valid = true;

		do {
			if (type == SeatType.BIZ) {
				rs[0] = random.nextInt(train.getBizCabinNumber()) + 1;
				rs[1] = random.nextInt(train.getBizCabinSeatNumber()) + 1;
			} else {
				rs[0] = random.nextInt(train.getEcoCabinNumber()) + 1;
				rs[1] = random.nextInt(train.getEcoCabinSeatNumber()) + 1;
			}
			for (Ticket t : AppMem.getTicketList()) {
				if (t.getType() == type && t.getCabinNo() == rs[0] && t.getSeatNo() == rs[1]) {
					valid = false;
					break;
				}
			}

		} while (!valid);
		return rs;
	}

	public static void main(String args[]) {
		TicketAppMem app = new TicketAppMem();
		app.run();
	}

	private final String MAIN_MENU = "[1] SELL TICKET\n" + "[2] SOLD TICKETS\n" + "[3] CABIN SEATS\n" + "[4] EXIT";
	private final String SELL_TICKET_MENU_1 = "PERSON ID:";
	private final String SELL_TICKET_MENU_2 = "[1]BIZ\n[2]ECO\nTICKET TYPE: ";

}