package lab03;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class FilesUtil {
	
	static String clientPath = "rsrc\\Clients1.txt";
	static String tripPath = "rsrc\\Trips1.txt";
	static String reservationPath = "rsrc\\Reservations1.txt";
	
	public static List<Client> loadClient(){
		List<Client> result = new ArrayList<Client>();
		try {
			Scanner scanner = new Scanner(new File(clientPath));
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] parameters = line.split(" ");
				String name = parameters[0];
				String secondName = parameters [1];
				result.add(new Client(name, secondName));
				//System.out.println(name +" "+ secondName);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("Wrong path to the file");
		}
		return result;		
	}
	public static List<Trip> loadTrips() {
		List<Trip> result = new ArrayList<Trip>();
		try {
			Scanner scanner = new Scanner (new File(tripPath));
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] parameters = line.split(", ");
				String beginDateTemporary = parameters[0];
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date beginDate = new Date();
				try{
					beginDate = dateFormat.parse(beginDateTemporary);
					String endDateTemporary = parameters[1];
					Date endDate = new Date();
					endDate = dateFormat.parse(endDateTemporary);
					String description = parameters[2];
					String destination = parameters[3];
					int numberOfClients = Integer.parseInt(parameters[4]);
					int id = Integer.parseInt(parameters[5]);
					result.add(new Trip(beginDate, endDate, description, destination, numberOfClients, id));
				}catch(ParseException e) {
					System.out.println("Wrong date format!");
				}
				//System.out.println(beginDate + " " + endDate +" "+ description+" "+ destination+" "+ numberOfClients);
				
			}scanner.close();
			}catch(FileNotFoundException e) {
			System.out.println("Wrong path to the file");
			}
		return result;
	}
	private static Client stringToClient(String fullName)
	{
		String[] parameters = fullName.split(" ");
		return (new Client(parameters[0], parameters[1]));
	}
	public static List<Client> toClient(List<String> participants)
	{
		List<Client> result = new ArrayList<Client>();
		for(String a:participants)
		{	
			result.add(stringToClient(a));
		}
		
		return result;
	}
	public static List<Reservation> loadReservation(){
		List<Reservation> result = new ArrayList<Reservation>();
		try {
			Scanner scanner = new Scanner (new File(reservationPath));
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] parameters = line.split("; ");
				String actualReservedBy = parameters[0];
				String[] reservedByTemporary = actualReservedBy.split(" ");
				Client reservedBy = new Client(reservedByTemporary[0], reservedByTemporary[1]);
				int idOfTrip = Integer.parseInt(parameters[1]);
				
				String actualParticipants = parameters[2];
				List<String> participants = new ArrayList<String>();
				if(participants.contains(", "))
				{
					for(int i=0; i<actualParticipants.split(", ").length; i++)
					{
						participants.add(actualParticipants.split(", ")[i]);
					}
				}else{ participants.add(actualParticipants);
				}
				List<Client> participantss = toClient(participants);
				String mustBePaidUntilTemporary = parameters[3];
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date mustBePaidUntil = new Date();
				mustBePaidUntil = dateFormat.parse(mustBePaidUntilTemporary);
				String status = parameters[4];
				int id = Integer.parseInt(parameters[5]);
				result.add(new Reservation(reservedBy, idOfTrip, participantss, mustBePaidUntil, status, id));
				
				//System.out.println(reservedBy.toString()+" "+ idOfTrip+" "+ participants+" "+ mustBePaidUntil+" "+ status);
				}
			scanner.close();
		}catch(FileNotFoundException e) {
			System.out.println("Wrong path to the file");
		}
		catch(ParseException e) {
			System.out.println("Wrong date format");
		}
		return result;
	}
	
	public static void writeClient() throws IOException{
		try {
			System.out.println("Dodaj klienta. Użyj następującej składni: imię nazwisko");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String clientData = scanner.nextLine();
			
			FileWriter file = new FileWriter(clientPath, true);
			BufferedWriter out = new BufferedWriter(file);
			out.write("\n"+ clientData);
			out.close();
		}catch(FileNotFoundException e){
			System.out.println("Wrong path to the file");}
	}
	public static void writeTrip() throws DuplicateIdException{
		try {
			System.out.println("Dodaj wycieczkę. Użyj następującej składni: dd-MM-yyyy (termin od), dd-MM-yyyy (termin do), "
					+ "opis, kierunek, x (liczba miejsc), id (unikalny numer)");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String tripData = scanner.nextLine();
			File file = new File(tripPath);
			List<String> lines = Files.lines(file.toPath()).collect(Collectors.toList()); //wczytuje wszystkie linijki z pliku
			String newId = tripData.split(", ")[5];
			for(String line:lines) {
				String id = line.split(", ")[5];
				if(id.equals(newId)) //.equals to odpowiednik == <-odpowiednie dla typów prostych
				{
					throw new DuplicateIdException("The trip with this ID already exists");
				}
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(tripPath, true));
			out.write("\n"+ tripData);
			out.close();
		}catch(FileNotFoundException e) {
			System.out.println("Wrong path to the file");}
		catch(IOException e) {
			System.out.println("Can not write to the file");
		}
		System.out.println("Wycieczka została dodana.");
	}
	public static void writeReservation()throws DuplicateIdException {
		try {
			System.out.println("Dodaj rezerwację. Użyj następującej składni: imie nazwisko (kto dokonał rezerwacji);"
					+ " id wycieczki; imie1 nazwisko1, imie2 nazwisko2, ... (kto w wycieczce uczestniczy); "
					+ "dd-MM-yyyy (termin płatności); założenie/opłacenie/anulowanie (status rezerwacji)");
			Scanner scanner = new Scanner(System.in);
			String reservationData = scanner.nextLine();
			scanner.close();
			File file = new File(reservationPath);
			List<String> lines = Files.lines(file.toPath()).collect(Collectors.toList()); //wczytuje wszystkie linijki z pliku
			String newId = reservationData.split(", ")[5];
			for(String line:lines) {
				String id = line.split(", ")[5];
				if(id.equals(newId)) //.equals to odpowiednik == <-odpowiednie dla typów prostych
				{
					throw new DuplicateIdException("The trip with this ID already exists");
				}
			}
			FileWriter fileWriter = new FileWriter(reservationPath, true);
			BufferedWriter out = new BufferedWriter(fileWriter);
			out.write("\n"+ reservationData);
			out.close();
		}catch(FileNotFoundException e) {
			System.out.println("Wrong path to the file");}
		catch(IOException e) {
			System.out.println("Can not write to the file");
		}
	}
}
