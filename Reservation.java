package reservations;

import java.util.ArrayList;

//ADD DATES AND SHIT
public class Reservation {

	//-----------------------------------------------------------------------------------------------------------------------------------
	private Room[][] hotel = new Room[16][21];
	
	private String name;
	private String phoneNum;
	private String creditCard;
	private int checkInDay; 
	private String checkInDayString;
	private int checkInMonth; 
	private String checkInMonthString;
	private int checkInYear; 
	private String checkInString;
	private int checkOutDay; 
	private String checkOutDayString;
	private int checkOutMonth; 
	private String checkOutMonthString;
	private int checkOutYear; 
	private String checkOutString;
	private int adultsInRoom;
	private final int COST_PER_ADULT = 50;
	private int childrenInRoom;
	private final int COST_PER_CHILD = 30;
	
	//private String desiredSize;
	private int desiredNumBeds;
	private boolean wantsView;
	private String wantsViewString;
		
	private double roomCost;
	private Room toReserve;
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	public Reservation(String n, String pN, String cC, int cID, int cIM, int cIY, int cOD, int cOM, int cOY, int aIR, int cIR, 
			int dNB, boolean wV) {
		name = n;
		phoneNum = pN;
		creditCard = cC;
		checkInDay = cID;
		checkInMonth = cIM;
		checkInYear = cIY;
		checkOutDay = cOD;
		checkOutMonth = cOM;
		checkOutYear = cOY;
		adultsInRoom = aIR;
		childrenInRoom = cIR;
		//desiredSize = dS;
		desiredNumBeds = dNB;
		wantsView = wV;
		for (int i = 1; i < 16; i++) {
			for (int j = 0; j < 21; j++) {
				String roomNumberString = i + "";
				int roomNumber;
				int numBeds;
				//String size;
				boolean hasView;
				if (i == 15) {
					numBeds = 4;
					//size = "penthouse";
					hasView = true;
				}
				else if (i > 11) {
					numBeds = 3;
					//size = "large";
					hasView = true;
				}
				else if (i > 5) {
					numBeds = 2;
					//size = "medium";
					hasView = true;
				}
				else {
					numBeds = 1;
					//size = "small";
					hasView = false;
				}
				if (j < 10) {
					roomNumberString += "0" + j;
				}
				else {
					roomNumberString += j;
				}
				roomNumber = Integer.parseInt(roomNumberString);
				hotel[i][j] = new Room(roomNumber, numBeds, hasView);
			}
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public String getCreditCard() {
		return creditCard;
	}
	
	public int getCheckInDay() {
		return checkInDay;
	}
	
	public int getCheckInMonth() {
		return checkInMonth;
	}
	
	public int getCheckInYear() {
		return checkInYear;
	}
	
	public int getCheckOutDay() {
		return checkOutDay;
	}
	
	public int getCheckOutMonth() {
		return checkOutMonth;
	}
	
	public int getCheckOutYear() {
		return checkOutYear;
	}
	
	public int getAdultsPerRoom() {
		return adultsInRoom;
	}
	
	public int getChildrenPerRoom() {
		return childrenInRoom;
	}
	
	/**
	public String getDesiredSize() {
		return desiredSize;
	}
	*/
	
	public int getDesiredNumBeds() {
		return desiredNumBeds;
	}
	
	public boolean getWantsView() {
		return wantsView;
	}
	
	public double getRoomCost() {
		calcRoomCost();
		return roomCost;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setPhoneNum(String newPhoneNum) {
		phoneNum = newPhoneNum;
	}
	
	public void setCreditCard(String newCreditCard) {
		creditCard = newCreditCard;
	}
	
	public void setCheckInDay(int newCheckInDay) {
		checkInDay = newCheckInDay;
	}
	
	public void setCheckInMonth(int newCheckInMonth) {
		checkInMonth = newCheckInMonth;
	}
	
	public void setCheckInYear(int newCheckInYear) {
		checkInYear = newCheckInYear;
	}
	
	public void setCheckOutDay(int newCheckOutDay) {
		checkOutDay = newCheckOutDay;
	}
	
	public void setCheckOutMonth(int newCheckOutMonth) {
		checkOutMonth = newCheckOutMonth;
	}
	
	public void setCheckOutYear(int newCheckOutYear) {
		checkOutYear = newCheckOutYear;
	}
	
	public void setAdultsPerRoom(int newAdultsPerRoom) {
		adultsInRoom = newAdultsPerRoom;
	}
	
	public void setChildrenPerRoom(int newChildrenPerRoom) {
		childrenInRoom = newChildrenPerRoom;
	}
	
	/**
	public void setDesiredSize(String newDesiredSize) {
		desiredSize = newDesiredSize;
	}
	*/
	
	public void setDesiredNumBeds(int newDesiredNumBeds) {
		desiredNumBeds = newDesiredNumBeds;
	}
	
	public void setWantsView(boolean newWantsView) {
		wantsView = newWantsView;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------
	
	public ArrayList<Room> searchForRoom() {
		ArrayList<Room> options = new ArrayList<>();
		for (int i = 1; i < 16; i++) {
			for (int j = 1; j < 21; j++) {
				if (hotel[i][j].getNumBeds() == desiredNumBeds && 
					//hotel[i][j].getSize().equals(desiredSize) && 
					hotel[i][j].getHasView() == wantsView && 
					hotel[i][j].isVacant(checkInString, checkOutString)) { 
						options.add(hotel[i][j]);
				}
			}
		}
		return options;
	}
	
	public void reserve(int roomNumber) {
		String numberString = roomNumber + "";
		String floorString = "";
		String roomString = "";
		if (numberString.length() == 3) {
			floorString = numberString.substring(0, 1);
			roomString = numberString.substring(1, 3);
		}
		else {
			floorString = numberString.substring(0, 2);
			roomString = numberString.substring(2, 4);
		}
		int floor = Integer.parseInt(floorString);
		int room = Integer.parseInt(roomString);
		toReserve = hotel[floor][room];
		checkInString = checkInDayString + " " + checkInMonthString + " " + checkInYear;
		checkOutString = checkOutDayString + " " + checkOutMonthString + " " + checkOutYear;

		toReserve.reserve(checkInString, checkOutString);
		System.out.println("Room Reserved");
	}
	
	public void calcRoomCost() {
		double roomRate = (double)toReserve.getRoomRate();
		double peopleRate = (double)(adultsInRoom * COST_PER_ADULT + childrenInRoom * COST_PER_CHILD);
		roomCost = roomRate + peopleRate;
	}	
	
	public void formatDates() {
		checkInDayString = "";
		checkOutDayString = "";
		if (checkInDay < 10) {
			checkInDayString = "0" + checkInDay;
		}
		else {
			checkInDayString = checkInDay + "";
		}
		if (checkOutDay < 10) {
			checkOutDayString = "0" + checkOutDay;
		}
		else {
			checkOutDayString = checkOutDay + "";
		}
		checkInMonthString = "";
		checkOutMonthString = "";
		if (checkInMonth < 10) {
			checkInMonthString = "0" + checkInMonth;
		}
		else {
			checkInMonthString = checkInMonth + "";
		}
		if (checkOutMonth < 10) {
			checkOutMonthString = "0" + checkOutMonth;
		}
		else {
			checkOutMonthString = checkOutMonth + "";
		}
		wantsViewString = "";
		if (wantsView)
			wantsViewString = "Wants a view.";
	}
	
	public String toString() {
		formatDates();
		return "Name: " + name + "\n" + 
				"Phone Number: " + phoneNum + "\n" + 
				"Credit Card Number: " + creditCard + "\n" + 
				"Check-in Date (DD-MM-YYYY): " + checkInDayString + "-" + checkInMonthString + "-" + checkInYear + "\n" + 
				"Check-out Date (DD-MM-YYYY): " + checkOutDayString + "-" + checkOutMonthString + "-" + checkOutYear + "\n" + 
				"Adults in room: " + adultsInRoom + "\n" + 
				"Children in room: " + childrenInRoom + "\n" + 
				"Number of beds: " + desiredNumBeds + "\n" + 
				wantsViewString;
	}
	
}
