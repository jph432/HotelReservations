package reservations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.TitledBorder;

//CHANGE EACH INSTANCE OF "Frame" TO NAME YOU'RE USING
public class ReservationFrame extends JFrame {
	
	private static final int FRAME_WIDTH = 1100; //CHANGE TO DESIRED DEMENSIONS
	private static final int FRAME_HEIGHT = 500;
	private static JPanel mainPanel;
	
	private static Reservation reservation;
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Date date = new Date();
	private static String currentDate = dateFormat.format(date); //2014/08/06 15:59:48
	private static int dayNum = Integer.parseInt(currentDate.substring(8, 10));
	private static int yearNum = Integer.parseInt(currentDate.substring(0, 4));
	private static int monthNum = Integer.parseInt(currentDate.substring(5, 7));
	
	private static JPanel infoPanel;
	
	private static JPanel customerPanel;
	private static JLabel nameFieldLabel, phoneNumFieldLabel, creditCardFieldLabel;
	private static JTextField nameField, phoneNumField, creditCardField;
	
	private static JPanel roomPrefPanel;
	private static JLabel numAdultsBoxLabel, numChildrenBoxLabel, numBedsBoxLabel;
	private static JComboBox numAdultsBox, numChildrenBox, numBedsBox;
	private static JCheckBox wantsViewBox;
	
	private static JPanel checkingPanel;
	
	private static JPanel checkInPanel;
	private static ButtonGroup checkInGroup;
	//private static JComboBox checkInYearBox;
	private static JComboBox checkInMonthBox;
	private static JPanel checkOutPanel;
	private static ButtonGroup checkOutGroup;
	//private static JComboBox checkOutYearBox;
	private static JComboBox checkOutMonthBox;
	
	private static JButton searchButton;
	
	public ReservationFrame() {
		createComponents();
		add(mainPanel);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
	
	//A simple method to display a pop-up to show the user a warning.
	//Used if the user chooses to convert from a currency to that same currency.
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void createComponents() {
		mainPanel = new JPanel(new BorderLayout());
		
		
		
		infoPanel = new JPanel();
		
		customerPanel = new JPanel(new GridLayout(3, 2));
		nameFieldLabel = new JLabel("Name: ");
		phoneNumFieldLabel = new JLabel("Phone Number: ");
		creditCardFieldLabel = new JLabel("Credit Card Number: ");
		nameField = new JTextField();
		phoneNumField = new JTextField();
		creditCardField = new JTextField();
		customerPanel.add(nameFieldLabel);
		customerPanel.add(nameField);
		customerPanel.add(phoneNumFieldLabel);
		customerPanel.add(phoneNumField);
		customerPanel.add(creditCardFieldLabel);
		customerPanel.add(creditCardField);
		infoPanel.add(customerPanel);
		
		roomPrefPanel = new JPanel(new GridLayout(4, 2));
		numAdultsBoxLabel = new JLabel("Number of adults in the room: ");
		numChildrenBoxLabel = new JLabel("Number of children in the room: ");
		numBedsBoxLabel = new JLabel("Desired number of beds in the room: ");
		String[] adultNumbers = { "1", "2", "3", "4" };
		numAdultsBox = new JComboBox(adultNumbers);
		String[] childrenNumbers = { "0", "1", "2", "3", "4" };
		numChildrenBox = new JComboBox(childrenNumbers);
		String[] bedNumbers = adultNumbers;
		numBedsBox = new JComboBox(bedNumbers);
		wantsViewBox = new JCheckBox("Room with a view");
		roomPrefPanel.add(numAdultsBoxLabel);
		roomPrefPanel.add(numAdultsBox);
		roomPrefPanel.add(numChildrenBoxLabel);
		roomPrefPanel.add(numChildrenBox);
		roomPrefPanel.add(numBedsBoxLabel);
		roomPrefPanel.add(numBedsBox);
		roomPrefPanel.add(wantsViewBox);
		infoPanel.add(roomPrefPanel);
		
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		
		checkingPanel = new JPanel(new GridLayout(1, 2));
		
		checkInPanel = new JPanel(new GridLayout(6, 7));
		checkInPanel.setBorder(new TitledBorder("Check In"));
		checkInGroup = new ButtonGroup();
		String[] years = { "2016", "2017", "2018", "2019", "2020", "2021", "2022" };
		String[] months = { "January", "Febuary", "March", "April", "May", "June", "July", "August",
							"September", "October", "November", "December", "The secret one" };
		String[] normalPossibleMonths = { months[monthNum - 1], months[monthNum] };
		String[] decemberPossibleMonths = { months[11], months[0] };
		//checkInYearBox = new JComboBox(years);
		if (monthNum < 12) {
			checkInMonthBox = new JComboBox(normalPossibleMonths);
		}
		else {
			checkInMonthBox = new JComboBox(decemberPossibleMonths);
		}
		//checkInYearBox.setSelectedIndex(yearNum - 2016);
		//checkInMonthBox.setSelectedIndex(monthNum - 1);
		for (int i = 0; i < 3; i++) {
			checkInPanel.add(new JLabel(""));
		}
		checkInPanel.add(checkInMonthBox);
		//checkInPanel.add(checkInYearBox);
		for (int i = 0; i < 3; i++) {
			checkInPanel.add(new JLabel(""));
		}
		for (int i = 1; i < 32; i++) {
			JRadioButton button = new JRadioButton(i + "");
			if (i < dayNum) {
				button.setEnabled(false);
			}
			button.setActionCommand(i + "");
			checkInPanel.add(button);
			checkInGroup.add(button);
		}
		checkingPanel.add(checkInPanel);
		
		checkOutPanel = new JPanel(new GridLayout(6, 7));
		checkOutPanel.setBorder(new TitledBorder("Check Out"));
		checkOutGroup = new ButtonGroup();
		//checkOutYearBox = new JComboBox(years);
		if (monthNum < 12) {
			checkOutMonthBox = new JComboBox(normalPossibleMonths);
		}
		else {
			checkOutMonthBox = new JComboBox(decemberPossibleMonths);
		}
		//checkOutYearBox.setSelectedIndex(yearNum - 2016);
		//checkOutMonthBox.setSelectedIndex(monthNum - 1);
		for (int i = 0; i < 3; i++) {
			checkOutPanel.add(new JLabel(""));
		}
		checkOutPanel.add(checkOutMonthBox);
		//checkOutPanel.add(checkOutYearBox);
		for (int i = 0; i < 3; i++) {
			checkOutPanel.add(new JLabel(""));
		}
		for (int i = 1; i < 32; i++) {
			JRadioButton button = new JRadioButton(i + "");
			if (i <= dayNum) {
				button.setEnabled(false);
			}
			button.setActionCommand(i + "");
			checkOutPanel.add(button);
			checkOutGroup.add(button);
		}
		checkingPanel.add(checkOutPanel);
		
		mainPanel.add(checkingPanel, BorderLayout.CENTER);
		
		//ENABLE ALL BUTTONS IF A NEW MONTH IS SELECTED
		class MonthSelectorListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				
			}
		}
		
		class SearchButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				String name = "";
				String phoneNum = "";
				String creditCard = "";
				int checkInDay = 0;
				int checkInMonth = 0;
				int checkInYear = 0;
				int checkOutDay = 0;
				int checkOutMonth = 0;
				int checkOutYear = 0;
				int adultsInRoom;
				int childrenInRoom;
				int desiredNumBeds;
				boolean wantsView;
				
				String potentialName = nameField.getText();
				boolean warned = false;
				if (!potentialName.equals("")) {
					name = potentialName;
				}
				else {
					infoBox("Field(s) left Blank\nFill out all fields to continue.", "Error");
					warned = true;
				}
				
				String potentialPhoneNum = phoneNumField.getText();
				boolean isAllDigits = true;
				if (potentialPhoneNum.length() == 10) {
					for (int i = 0; i < 10; i++) {
						if (!Character.isDigit(potentialPhoneNum.charAt(i))) {
							isAllDigits = false;
							break;
						}
					}
				}
				if (potentialPhoneNum.length() != 10 || !isAllDigits) {
					if (!warned) {
						infoBox("Invalid phone number.\nInlude area code, do not include dashes or spaces.", "Error");
						warned = true;
					}
				}
				else {
					phoneNum = potentialPhoneNum;
				}
				
				String potentialCreditCardNumber = creditCardField.getText();
				boolean isACreditCard = true;
				if (potentialCreditCardNumber.length() == 19) {
					for (int i = 0; i < 19; i++) {
						if (i == 4 || i == 9 || i == 14) {
							if (!(potentialCreditCardNumber.charAt(i) + "").equals(" ")) {
								isACreditCard = false;
								break;
							}
						}
						else {
							if (!Character.isDigit(potentialCreditCardNumber.charAt(i))) {
								isACreditCard = false;
								break;
							}
						}
					}
				}
				else
					isACreditCard = false;
				if (!isACreditCard) {
					if (!warned) {
						infoBox("Invalid credit card number.\nPlease include spaces.", "Error");
						warned = true;
					}
				}
				else {
					creditCard = potentialCreditCardNumber;
				}
				
				boolean datesAreValid = true;
				
				int potentialCheckInDay = Integer.parseInt(checkInGroup.getSelection().getActionCommand());
				int potentialCheckInMonth = monthNum + checkInMonthBox.getSelectedIndex();	
				if (potentialCheckInMonth == 13) {
					potentialCheckInMonth = 1;
				}
				int potentialCheckInYear = yearNum;
				
				int potentialCheckOutDay = Integer.parseInt(checkOutGroup.getSelection().getActionCommand());
				int potentialCheckOutMonth = monthNum + checkOutMonthBox.getSelectedIndex();
				if (potentialCheckOutMonth == 13) {
					potentialCheckOutMonth = 1;
				}
				int potentialCheckOutYear = yearNum;
				if (monthNum == 12) {
					if (checkInMonthBox.getSelectedIndex() == 1) {
						potentialCheckInYear++; 
					}
					if (checkOutMonthBox.getSelectedIndex() == 1) {
						potentialCheckOutYear++;
					}
				}
				
				
				if (potentialCheckOutYear < potentialCheckInYear || potentialCheckOutYear - potentialCheckInYear > 1) {
					datesAreValid = false;
				}
				else if (potentialCheckOutYear == potentialCheckInYear) {
					if (potentialCheckOutMonth < potentialCheckInMonth) {
						datesAreValid = false;
					}
					else if (potentialCheckOutMonth == potentialCheckInMonth) {
						if (potentialCheckOutDay <= potentialCheckInDay) {
							datesAreValid = false;
						}
					}
				}
				if (!datesAreValid && !warned) {
					infoBox("Invalid check-in/check-out dates.\nEnsure that check-out occurs after check-in.", "Error");
					warned = true;
				}
				else {
					checkInDay = potentialCheckInDay;
					checkInMonth = potentialCheckInMonth;
					checkInYear = potentialCheckInYear;
					checkOutDay = potentialCheckOutDay;
					checkOutMonth = potentialCheckOutMonth;
					checkOutYear = potentialCheckOutYear;
				}
				
				adultsInRoom = Integer.parseInt(numAdultsBox.getSelectedItem() + "");
				childrenInRoom = Integer.parseInt(numChildrenBox.getSelectedItem() + "");
				desiredNumBeds = Integer.parseInt(numBedsBox.getSelectedItem() + "");

				if (wantsViewBox.isSelected())
					wantsView = true;
				else
					wantsView = false;
				reservation = new Reservation(name, phoneNum, creditCard, checkInDay, checkInMonth, checkInYear, checkOutDay, 
											checkOutMonth, checkOutYear, adultsInRoom, childrenInRoom, desiredNumBeds, 
											wantsView);
				System.out.println(reservation.toString());
				
				ArrayList<Room> possibleRooms = reservation.searchForRoom();
				if (possibleRooms.size() > 0) {
					for (int i = 0; i < possibleRooms.size(); i++) {
						System.out.println(possibleRooms.get(i).toString());
					}
				}
				else {
					System.out.println("No rooms of these specifications available. Please try again.");
				}
			}	
		}
		
		searchButton = new JButton("Search");
		SearchButtonListener listener = new SearchButtonListener();
		searchButton.addActionListener(listener);
		mainPanel.add(searchButton, BorderLayout.SOUTH);
		
		/**

		
		
		//do this part in a separate frame, probably
		System.out.println("Enter the room number you would like to reserve: ");
		int desiredRoom = in.nextInt();
		thing.reserve(desiredRoom);
		*/
		
	}
}
