package lab03;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[]args) throws ParseException, IOException, NoSuchElementException {
		
		System.out.println("Wybierz opcję:\n1: wyświetl bazę \n2: dodaj ofertę wycieczki\n3: aktualizuj ofertę wycieczki"
				+ "\n4: usuń ofertę wycieczki\n5: szukaj ofert wg terminu\n6: szukaj ofert wg kierunku \n7: szukaj ofert wg liczby miejsc"
				+ "\n8: generuj zestawienie aktywności uczestników w zadanym okresie \n9: generuj zestawienie najpopularniejszych wycieczek"
				+ "\n10: złóż rezerwację \n11: opłać rezerwację \n12: anuluj rezerwację \n13: ustaw czas");
		showMenu();
	}
	public static void showMenu() {
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
				
		if(choice == 1) {
			showDataBase();
		} else if(choice == 2) {
			TripHelper.addTrip();
		} else if(choice == 3) {
			TripHelper.updateTrip();
		} else if(choice == 4) {
			TripHelper.deleteTrip();
		} else if(choice == 5) {
			showListOfTrips(TripHelper.findByDate());
		} else if(choice == 6) {
			showListOfTrips(TripHelper.findByDestination());
		} else if(choice == 7) {
			showListOfTrips(TripHelper.findByNumberOfClients());
		} else if(choice == 8) {
			System.out.println("Liczba rezerwacji w zadanym okresie: " + ReservationHelper.findNumberOfReservationsByDates());
		} else if(choice == 9) {
			System.out.println("Pracujemy nad tym");
		} else if(choice == 10) {
			ReservationHelper.addReservation();
		} else if(choice == 11) {
			ReservationHelper.payForReservation();
		} else if(choice == 12) {
			ReservationHelper.cancelReservation();
		} else if(choice == 13) {
			System.out.println("Pracujemy nad tym");
		} else {
			System.out.println("Spróbuj ponownie.");
		}
	}
	public static void showListOfTrips(List<Trip> trips) {
		for(Trip trip:trips) {
			System.out.println(trip.toString());
		}
	}
	
	public static void showDataBase() {
		List<Client> clients = FilesUtil.loadClient();
		for(Client client:clients) {			
			System.out.println("Clients: \n" + client.toString() + "\n");
		}
		List<Trip> trips = FilesUtil.loadTrips();
		for (Trip trip:trips) {
			System.out.println("Trips: \n" + trip.toString() + "\n");
		}
		List<Reservation> reservations = FilesUtil.loadReservation();
		for (Reservation reservation:reservations) {
			System.out.println("Reservations: \n" + reservation.toString() + "\n");
		}
	}
	
}
