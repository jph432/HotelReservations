package reservations;

/**
 * A class which models a room in a hotel, with a room number, number of beds, whether or not it has a view, and the room's rate.
 * Note: roomRate represents the cost of the room itself. It does not take into account the cost added on by the number of people
 * staying in the room. That is handled by the Reservation class.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Room {
	
	//Attributes------------------------------------------------------------------------------------------------------------------------------
	
	private ArrayList<String> reservationDates;
	//private boolean isVacant;
	private int number;
	private int numBeds;
	private final int COST_PER_BED = 50;
	//private String size;
	private boolean hasView;
	private final int VIEW_COST = 50;
	private double roomRate;
	
	//Constructor Method----------------------------------------------------------------------------------------------------------------------
	
	public Room(int n, int nB, boolean hV) {
		//isVacant = true;
		number = n;
		numBeds = nB;
		//size = s;
		hasView = hV;
		calcRoomRate();
	}
	
	//Accessor Methods-------------------------------------------------------------------------------------------------------------------------
	
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
	
	//Mutator Methods-----------------------------------------------------------------------------------------------------------------------
	
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
	
	//Other Methods------------------------------------------------------------------------------------------------------------------------
	
	//Calculates the cost of the room based on the number of beds and whether or not the room has a view.
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
	
	//Goes through the list of this room's reservations and removes those that have already ended (guests have checked out).
	public void refreshReservations() {
		
		//This block gets the current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String currentDate = dateFormat.format(date); //2014/08/06 15:59:48
		int yearNum = Integer.parseInt(currentDate.substring(0, 4));
		int monthNum = Integer.parseInt(currentDate.substring(5, 7));
		int dayNum = Integer.parseInt(currentDate.substring(8, 10));
		
		for (int i = 0; i < reservationDates.size(); i += 2) {
			boolean remove = false;
			String aCheckOut = reservationDates.get(i+1);
			int aCheckOutDay = Integer.parseInt(aCheckOut.substring(0, 2)); 	//Reservation dates are formatted as "DD MM YYYY", as
			int aCheckOutMonth = Integer.parseInt(aCheckOut.substring(3, 5));   //seen in the Reservation class.
			int aCheckOutYear = Integer.parseInt(aCheckOut.substring(6, 10));
			
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
	
	//Given a potential check-in and check-out date, this method checks to see if the room is vacant during this period.
	public boolean getIsVacantBetween(String checkIn, String checkOut) {
		
		boolean isVacant = true;
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
			
			
			if (proposedCheckInYear == aCheckInYear) {
				if (proposedCheckInMonth == 12 && proposedCheckOutMonth == 1) {
					
				}
				else if (proposedCheckInMonth < proposedCheckOutMonth) {
					if (proposedCheckInDay >= aCheckInDay && proposedCheckOutDay < aCheckOutDay) {
						isVacant = false;
					}
				}
				else if (proposedCheckInMonth == aCheckInMonth) {
					if (proposedCheckInDay >= aCheckInDay && proposedCheckOutDay < aCheckOutDay) {
						isVacant = false;
					}
				}
			}
		}		
		return isVacant;
	}
	
	//Reserves this room between the given dates. The room becomes available on the check-out date.
	public void reserve(String checkIn, String checkOut) {
		if (getIsVacantBetween(checkIn, checkOut)) {
			reservationDates.add(checkIn);
			reservationDates.add(checkOut);
		}
		else {
			System.out.println("This room is not vacant on those dates!"); //This shouldn't happen, as the Reservation class ensures that
		}																	//only vacant rooms are available to the user, but ya never know.
	}
	
	//Returns all the information about this room in a well-formatted String.
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
