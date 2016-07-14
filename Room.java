package reservations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Room {
	
	
	//ADD A reservedOn ARRAYLIST THAT KEEPS TRACK OF WHEN THE ROOM IS RESERVED.
	//ADD A checkReserved(int start, int end) METHOD WHICH CHECKS THE ARRAYLIST
	//^THIS WILL ESSENTIALLY REPLACE isVacant.
	//^THAT WONT WORK WITH THOSE PARAMETERS, CONSIDER USING Date OBJECTS INSTEAD.
	private ArrayList<String> reservationDates;
	//private boolean isVacant;
	private int number;
	private int numBeds;
	private final int COST_PER_BED = 50;
	//private String size;
	private boolean hasView;
	private final int VIEW_COST = 50;
	private double roomRate;
	
	public Room(int n, int nB, boolean hV) {
		//isVacant = true;
		number = n;
		numBeds = nB;
		//size = s;
		hasView = hV;
		calcRoomRate();
	}
	
	/**
	public boolean getIsVacant() {
		return isVacant;
	}
	*/
	
	public int getNumber() {
		return number;
	}
	
	public int getNumBeds() {
		return numBeds;
	}
	
	public int getCostPerBed() {
		return COST_PER_BED;
	}
	
	/**
	public String getSize() {
		return size;
	}
	*/
	
	public boolean getHasView() {
		return hasView;
	}
	
	public int getViewCost() {
		return VIEW_COST;
	}
	
	public double getRoomRate() {
		calcRoomRate();
		return roomRate;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	/**
	public void setIsVacant(boolean newIsVacant) {
		isVacant = newIsVacant;
	}
	*/
	
	public void setNumber(int newNumber) {
		number = newNumber;
	}
	
	public void setNumBeds(int newNumBeds) {
		numBeds = newNumBeds;
	}
	
	/**
	public void setSize(String newSize) {
		size = newSize;
	}
	*/
	
	public void setHasView(boolean newHasView) {
		hasView = newHasView;
	}
	
	public void setRoomRate(double newRoomRate) {
		roomRate = newRoomRate;
	}
	
	public void calcRoomRate() {
		roomRate = 0;
		roomRate += numBeds * COST_PER_BED;
		/**
		if (size.toLowerCase().equals("small")) {
			roomRate += 25;
		}
		else if (size.toLowerCase().equals("medium")) {
			roomRate += 50;
		}
		else if (size.toLowerCase().equals("large")) {
			roomRate += 100;
		}
		else if (size.toLowerCase().equals("penthouse")) {
			roomRate += 150;
		}
		*/
		if (hasView) {
			roomRate += VIEW_COST;
		}
	}
	
	public void refreshReservations() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String currentDate = dateFormat.format(date); //2014/08/06 15:59:48
		int yearNum = Integer.parseInt(currentDate.substring(0, 4));
		int monthNum = Integer.parseInt(currentDate.substring(5, 7));
		int dayNum = Integer.parseInt(currentDate.substring(8, 10));
		
		for (int i = 0; i < reservationDates.size(); i += 2) {
			boolean remove = false;
			String aCheckIn = reservationDates.get(i);
			String aCheckOut = reservationDates.get(i+1);
			int aCheckInDay = Integer.parseInt(aCheckIn.substring(0, 2));
			int aCheckInMonth = Integer.parseInt(aCheckIn.substring(3, 5));
			int aCheckInYear = Integer.parseInt(aCheckIn.substring(6, 10));
			int aCheckOutDay = Integer.parseInt(aCheckIn.substring(0, 2));
			int aCheckOutMonth = Integer.parseInt(aCheckIn.substring(3, 5));
			int aCheckOutYear = Integer.parseInt(aCheckIn.substring(6, 10));
			
			if (aCheckOutYear < yearNum) {
				remove = true;
			}
			else if (aCheckOutYear == yearNum) {
				if (aCheckOutMonth < monthNum) {
					remove = true;
				}
				else if (aCheckOutMonth == monthNum) {
					if (aCheckOutDay < dayNum) {
						remove = true;
					}
				}
			}
			if (remove) {
				reservationDates.remove(i);
				reservationDates.remove(i + 1);
			}
		}	
	}
	
	public boolean isVacant(String checkIn, String checkOut) {
		boolean toReturn = true;
		for (int i = 0; i < reservationDates.size(); i += 2) {
			String aCheckIn = reservationDates.get(i);
			String aCheckOut = reservationDates.get(i+1);
			int aCheckInDay = Integer.parseInt(aCheckIn.substring(0, 2));
			int aCheckInMonth = Integer.parseInt(aCheckIn.substring(3, 5));
			int aCheckInYear = Integer.parseInt(aCheckIn.substring(6, 10));
			int aCheckOutDay = Integer.parseInt(aCheckIn.substring(0, 2));
			int aCheckOutMonth = Integer.parseInt(aCheckIn.substring(3, 5));
			int aCheckOutYear = Integer.parseInt(aCheckIn.substring(6, 10));
			
			int proposedCheckInDay = Integer.parseInt(checkIn.substring(0, 2));
			int proposedCheckInMonth = Integer.parseInt(checkIn.substring(3, 5));
			int proposedCheckInYear = Integer.parseInt(checkIn.substring(6, 10));
			int proposedCheckOutDay = Integer.parseInt(checkIn.substring(0, 2));
			int proposedCheckOutMonth = Integer.parseInt(checkIn.substring(3, 5));
			int proposedCheckOutYear = Integer.parseInt(checkIn.substring(6, 10));
			
			//if (aCheckInYear == )
		}		
		
		
		return toReturn;
	}
	
	public void reserve(String checkIn, String checkOut) {
		if (isVacant(checkIn, checkOut)) {
			reservationDates.add(checkIn);
			reservationDates.add(checkOut);
		}
		else {
			System.out.println("This room is not vacant on those dates!");
		}
	}
	
	public String toString() {
		String viewString = "";
		if (hasView) {
			viewString = "Has a view";
		}
		return "Room Number: " + number + "\n" + 
				//"Size: " + size + "\n" + 
				"Number of beds: " + numBeds + "\n" + 
				 viewString + "\n" + 
				"Cost per night: " + "$" + roomRate + " + $50 per adult / $30 per child" + "\n";
	}
}
