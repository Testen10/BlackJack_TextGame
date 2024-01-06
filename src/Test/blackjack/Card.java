package Test.blackjack;

public class Card {
	private int num;
	private String shape;
	private String[] shape_list = new String[] {"Heart", "Spade", "Clover", "Diamond"};
	
	
	private static final String red      = "\u001B[31m" ;
	private static final String exit     = "\u001B[0m" ;
	
	
	public Card(int num, int shape_loc) {
		this.num = num;
		shape = shape_list[shape_loc];
	}
	
	public String toString() {
		String message = "";
		
		if(num==1) { message = shape+" A"; }
		else if(num==11) { message = shape+" J"; }
		else if(num==12) { message = shape+" Q"; }
		else if(num==13) { message = shape+" K"; }
		else { message = shape+" "+Integer.toString(num); }
		
		if(shape == "Heart" || shape == "Diamond") {message = red+message+exit;}
		

		return message;
	}
	
	public int return_num(){
		if(this.num>10) {
			return 10;
		}
		return this.num;
	}
}
