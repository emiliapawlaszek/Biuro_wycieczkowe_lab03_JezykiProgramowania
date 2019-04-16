package lab03;

public class Client {
	public String name;
	public String secondName;
	
	public Client(String name, String surname)
	{
		this.name = name;
		this.secondName = surname;
	}
	
	@Override
	public String toString()
	{
		return name +" "+ secondName;
	}

}
