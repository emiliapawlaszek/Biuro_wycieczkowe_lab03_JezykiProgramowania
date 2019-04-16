package lab03;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Reservation {
	Client reservedBy;
	int idOfTrip;
	List<Client> participants;
	Date mustBePaidUntil;
	String status;
	int DAYS_TO_PAY_FOR_TRIP = 7;
	int id;
	
	public Reservation(Client reservedBy, int idOfTrip, List<Client> participantss, Date mustBePaidUntil, String status, int id)
	{
		this.reservedBy = reservedBy;
		this.idOfTrip = idOfTrip; 
		this.participants = participantss;
		this.status = status;
		this.id= id;
		this.mustBePaidUntil = mustBePaidUntil;
	}
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String mustBePaidUntilString =  dateFormat.format(mustBePaidUntil);
		String sep = "; ";
		String participantsStr = "";
		for (Client participant:participants) {
			participantsStr+=participant.toString();
			if(!(participant==participants.get(participants.size()-1))) {
				participantsStr+=", ";
			}	
		}
		return reservedBy.toString()+sep+idOfTrip+sep+participantsStr+sep+mustBePaidUntilString+sep+status+sep+id;
		}
}
