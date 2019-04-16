package lab03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TripHelper {
	
	public static void addTrip() {
		try {
			FilesUtil.writeTrip();
		}catch(DuplicateIdException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void updateTrip() {
		System.out.println("Jaką wycieczkę chcesz zaktualizować? (podaj jej id)");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		System.out.println("Wpisz nowe dane");
		String newTrip = scanner.nextLine();
		scanner.close();
		List<Trip> trips = FilesUtil.loadTrips();
		String content ="";
		for(Trip trip:trips) {
			if(trip.id!=Integer.parseInt(id)) {
				content += trip.toString() + "\r\n";
			}
			else {
				content += newTrip + "\r\n";
			}
		}
		try {
			content.trim();
			byte[] strToBytes = content.getBytes();
		    Files.write(Paths.get(FilesUtil.tripPath), strToBytes);
		}catch(IOException e) {
			System.out.println("Can not write to the file");
		}
		System.out.println("Wycieczka została zaktualizowana");
	}
	public static void deleteTrip(){
		System.out.println("Jaką wycieczkę chcesz usunąć? (podaj jej id)");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine().split(", ")[5];
		scanner.close();
		List<Trip> trips = FilesUtil.loadTrips();
		String content ="";
		for(Trip trip:trips) {
			if(trip.id!=Integer.parseInt(id)) {
				content += trip.toString()+"\r\n";
			}
		}
		try {
			content.trim();
			byte[] strToBytes = content.getBytes();
		    Files.write(Paths.get(FilesUtil.tripPath), strToBytes);
		}catch(IOException e) {
			System.out.println("Can not write to the file");
		}
		System.out.println("Wycieczka została usunięta");
	}
	public static List<Trip> findByDate(){
		List<Trip> all = FilesUtil.loadTrips();
		List<Trip> result = new ArrayList<Trip>();
		System.out.println("Podaj datę w formacie dd-MM-yyyy");
		Scanner scanner = new Scanner(System.in);
		String dateStr = scanner.nextLine();
		scanner.close();
		try {
			Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
			
			for(Trip trip:all) {
				if(trip.beginDate.equals(date)){
					result.add(trip);
				}
			}
		} catch (ParseException e) {
			System.out.println("Wrong date format!");
		}
		return result;
	}
	public static List<Trip> findByDestination(){
		List<Trip> all = FilesUtil.loadTrips();
		List<Trip> result = new ArrayList<Trip>();
		System.out.println("Podaj kierunek");
		Scanner scanner = new Scanner(System.in);
		String destinationStr = scanner.nextLine();
		scanner.close();	
		for(Trip trip:all) {
			if(trip.destination.equals(destinationStr)){
				result.add(trip);
			}
		}
		return result;
	}
	public static List<Trip> findByNumberOfClients(){
		List<Trip> all = FilesUtil.loadTrips();
		List<Trip> result = new ArrayList<Trip>();
		System.out.println("Podaj liczbę miejsc");
		Scanner scanner = new Scanner(System.in);
		String numberOfClientsStr = scanner.nextLine();
		scanner.close();	
		for(Trip trip:all) {
			if(trip.numberOfClients == Integer.parseInt(numberOfClientsStr)){
				result.add(trip);
			}
		}
		return result;
	}
	public static Trip findById(int id){
		List<Trip> all = FilesUtil.loadTrips();
		for(Trip trip:all) {
			if(trip.id == id){
				return trip;
			}
		}
		return null;
	}
	
}
