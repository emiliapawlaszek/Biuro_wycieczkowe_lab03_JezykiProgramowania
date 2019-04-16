package lab03;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
	
	public Date beginDate;
	public Date endDate;
	public String description;
	public String destination;
	public int numberOfClients;
	public int id;
	
	public Trip(Date beginDate, Date endDate, String description, String destination, int numberOfClients, int id)
	{
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.description = description;
		this.destination = destination;
		this.numberOfClients = numberOfClients;
		this.id = id;
	}
	//metoda która zamienia date na string
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String beginDateString =  dateFormat.format(beginDate);
		String endDateString =  dateFormat.format(endDate);
		String sep = ", ";
		return beginDateString+sep+endDateString+sep+description+sep+destination+sep+numberOfClients+sep+id;
		}
}
