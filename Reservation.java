package reservations;

/**
 * A class which models a reservation at a hotel, including all information about the guest, the check-in and 
 * check-out dates, and the information about the room the customer wants.
 */

import java.util.ArrayList;

public class Reservation {

	//Attributes----------------------------------------------------------------------------------------------------------------------------
	
	private final int NUM_FLOORS = 15;
	private final int ROOMS_PER_FLOOR = 20;
	private Room[][] hotel = new Room[NUM_FLOORS][ROOMS_PER_FLOOR];  //A model of a hotel for reference. 15 floors with 20 rooms per floor.
	
	private String name;
	private String phoneNum;
	private String creditCard;
	private int checkInDay; 
	private String checkInDayString;
	private int checkInMonth; 
	private String checkInMonthString;
	private int checkInYear; 
	private String checkInString;	//A string modelling the check-in date, in the format of "DD MM YYYY"
	private int checkOutDay; 
	private String checkOutDayString;
	private int checkOutMonth; 
	private String checkOutMonthString;
	private int checkOutYear; 
	private String checkOutString;   //A string modelling the check-in date, in the format of "DD MM YYYY"
	private int adultsInRoom;
	private final int COST_PER_ADULT = 50;
	private int childrenInRoom;
	private final int COST_PER_CHILD = 30;
	
	//private String desiredSize; This attribute was removed, as it was redundant to have room size and number of beds, 
	private int desiredNumBeds;   //since the number of beds decides the size of the room.
	private boolean wantsView;
	private String wantsViewString;  //Used in the toString() method, simply adds "Wants a view" to the descrption.
		
	private double roomCost;
	private Room toReserve;
	
	//Constructor Method---------------------------------------------------------------------------------------------------------------------
	
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
		//The initialization of the hotel 2-D Array. You can ignore, but for the record
		//the first 6 floors have single rooms, 7-11 doubles, 12-14 suites, 15 is the penthouse.
		for (int floor = 1; floor <= NUM_FLOORS; floor++) {
			for (int room = 0; room <= ROOMS_PER_FLOOR; room++) {
				String roomNumberString = floor + "";
				int roomNumber;
				int numBeds;
				//String size;
				boolean hasView;
				if (floor == 15) {
					numBeds = 4;
					//size = "penthouse";
					hasView = true;
				}
				else if (floor > 11) {
					numBeds = 3;
					//size = "large";
					hasView = true;
				}
				else if (floor > 5) {
					numBeds = 2;
					//size = "medium";
					hasView = true;
				}
				else {
					numBeds = 1;
					//size = "small";
					hasView = false;
				}
				if (room < 10) {
					roomNumberString += "0" + room;
				}
				else {
					roomNumberString += room;
				}
				roomNumber = Integer.parseInt(roomNumberString);
				hotel[floor][room] = new Room(roomNumber, numBeds, hasView);
			}
		}
	}
	
	//Accessor Methods-------------------------------------------------------------------------------------------------------------------------
	
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
	
	//Mutator Methods-------------------------------------------------------------------------------------------------------------------------
	
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
	
	//Other Methods-------------------------------------------------------------------------------------------------------------------------
	
	//Searches the sample hotel Array for a room with the given desired features.
	//This would have to be modified to accomodate the floors and rooms/per floor of the used hotel. (edit attributes)
	public ArrayList<Room> searchForRoom() {
		ArrayList<Room> options = new ArrayList<>();
		for (int floor = 1; floor <= NUM_FLOORS; floor++) {
			for (int room = 1; room <= ROOMS_PER_FLOOR; room++) {
				if (hotel[floor][room].getNumBeds() == desiredNumBeds && 
					//hotel[i][j].getSize().equals(desiredSize) && 
					hotel[floor][room].getHasView() == wantsView && 
					hotel[floor][room].getIsVacantBetween(checkInString, checkOutString)) { 
						options.add(hotel[floor][room]);
				}
			}
		}
		return options;
	}
	
	//Relies fairly heavily on the reserve(String checkIn, String checkOut) method of the room class.
	//Books the room with the given number from the check-in date to the check-out date (both are attributes).
	public void reserve(int roomNumber) {
		//Lines 252-264 are just finding the given room in the hotel Array.
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
		checkInString = checkInDayString + " " + checkInMonthString + " " + checkInYear; //Dates use this format (DD MM YYYY) to make
		checkOutString = checkOutDayString + " " + checkOutMonthString + " " + checkOutYear; //reading from a text file potentially easier

		toReserve.reserve(checkInString, checkOutString); //Note: does not check room vacany as only rooms already known to be available
		System.out.println("Room Reserved");				//are presented to the user in the GUI.
	}
	
	//Updates the roomCost attribute given all the information about the reservation.
	public void calcRoomCost() {
		double roomRate = (double)toReserve.getRoomRate();
		double peopleRate = (double)(adultsInRoom * COST_PER_ADULT + childrenInRoom * COST_PER_CHILD);
		roomCost = roomRate + peopleRate;
	}	
	
	//Properly formats the check-in and check-out dates by placing a 0 before single-digit dates.
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
	
	//Returns all the information about the reservation in a well-formatted String
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
