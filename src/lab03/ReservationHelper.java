package lab03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReservationHelper {
	
	public static void addReservation() {
		try {
			FilesUtil.writeReservation();
		}catch(DuplicateIdException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Rezerwacja została dodana");
	}
	public static void cancelReservation() {
		System.out.println("Jaką rezerwację chcesz anulować? (podaj jej id)");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine().split("; ")[5];
		scanner.close();
		List<Reservation> reservations = FilesUtil.loadReservation();
		String content ="";
		for(Reservation reservation:reservations) {
			if(reservation.id!=Integer.parseInt(id)) {
				content += reservation.toString()+"\r\n";
			}
			else {
				String sep = "; ";
				String[] parameters = reservation.toString().split(sep);
				content += parameters[0]+sep+parameters[1]+sep+parameters[2]+sep+parameters[3]+sep+"anulowane"+sep+parameters[5];
			}
		}
		try {
			content.trim();
			byte[] strToBytes = content.getBytes();
		    Files.write(Paths.get(FilesUtil.reservationPath), strToBytes);
		}catch(IOException e) {
			System.out.println("Can not write to the file");
		}
		System.out.println("Rezerwacja została odwołana");
	}
	public static void payForReservation() {
		System.out.println("Jaką rezerwację chcesz opłacić? (podaj jej id)");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine().split("; ")[5];
		scanner.close();
		List<Reservation> reservations = FilesUtil.loadReservation();
		String content ="";
		for(Reservation reservation:reservations) {
			if(reservation.id!=Integer.parseInt(id)) {
				content += reservation.toString() +"\r\n";
			}
			else {
				String sep = "; ";
				String[] parameters = reservation.toString().split(sep);
				content += parameters[0]+sep+parameters[1]+sep+parameters[2]+sep+parameters[3]+sep+"opłacenie"+sep+parameters[5] + "\r\n";
			}
		}
		try {
			content.trim();
			byte[] strToBytes = content.getBytes();
		    Files.write(Paths.get(FilesUtil.reservationPath), strToBytes);
		}catch(IOException e) {
			System.out.println("Can not write to the file");
		}
		System.out.println("Rezerwacja została opłacona");
	}
	
	public static int findNumberOfReservationsByDates() {
		System.out.println("Podaj datę początkową w formacie dd-MM-yyyy: ");
		Scanner scanner = new Scanner(System.in);
		String beginDateStr = scanner.nextLine();
		int result = 0;
		try {
			Date beginDate = new SimpleDateFormat("dd-MM-yyyy").parse(beginDateStr);
			System.out.println("Podaj datę końcową w formacie dd-MM-yyyy: ");
			String endDateStr = scanner.nextLine();
			scanner.close();
			Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDateStr);
			for(Reservation reservation:FilesUtil.loadReservation()) {
				if(TripHelper.findById(reservation.idOfTrip).beginDate.compareTo(beginDate)>=0 
						&& TripHelper.findById(reservation.idOfTrip).beginDate.compareTo(endDate)<0) {
					result++;
				}
			}
		} catch (ParseException e) {
			System.out.println("Wrong date format");
		}
		return result;
	}
}
