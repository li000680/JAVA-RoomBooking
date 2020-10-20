/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:RoomScheduler
 * Date:03/02/2020
 */
package cst8284.asgmt2.roomScheduler;

import java.util.Calendar;
import cst8284.asgmt2.room.Room;
import java.io.*;
import java.util.ArrayList;

public class RoomScheduler{
    private static java.util.Scanner scan = new java.util.Scanner(System.in);
    private ArrayList<RoomBooking> roomBookings = new ArrayList<>();
    private Room room;
    private static final int DISPLAY_ROOM_INFORMATION=1;
    private static final int ENTER_ROOM_BOOKING=2;
    private static final int DELETE_BOOKING=3;
    private static final int CHANGE_BOOKING=4;
    private static final int DISPLAY_BOOKING=5;
    private static final int DISPLAY_DAY_BOOKINGS=6;
    private static final int SAVE_BOOKINGS_TO_FILE=7;
    private static final int LOAD_BOOKINGS_FROM_FILE=8;
    private static final int EXIT=0;
    
    //cxuann(2018)Java createNewFile [WebPage].Retrieved from https://blog.csdn.net/qq_36894974/article/details/79046054
    public RoomScheduler(Room room) {
    	File f = new File("./src/CurrentRoomBookings.book");
    	if(!f.exists())
    	{
	    	try {
	    		f.createNewFile();
				FileOutputStream file = new FileOutputStream("./src/CurrentRoomBookings.book");
		    	ObjectOutputStream oos = new ObjectOutputStream(file);
		    	oos.close();
		    	file.close();
	    	} catch (IOException e){
	    		e.printStackTrace();
	    	}
    	}
    	loadBookingsFromFile();
    	setRoom(room);
    }
    
    public void launch() {
    	int choice;
    	do{ 
    		choice = displayMenu();
    		executeMenuItem(choice);
    	}while(choice != 0);
    }
    
    //display the menu to the user and returning their choice
    private int displayMenu() {
    	System.out.println("Enter a selection from the following menu:\n"
    			+DISPLAY_ROOM_INFORMATION+". Display room information\n"
    			+ ENTER_ROOM_BOOKING+". Enter a room booking\n"
    			+DELETE_BOOKING+". Remove a room booking\n"
    			+CHANGE_BOOKING+". Change a room booking\n"
    			+DISPLAY_BOOKING+". Display a booking\n"
    			+ DISPLAY_DAY_BOOKINGS+". Display room bookings for the whole day\n"
    			+SAVE_BOOKINGS_TO_FILE+". Backup current bookings to file\n"
    			+LOAD_BOOKINGS_FROM_FILE+". Load current bookings from file\n"
    			+EXIT+". Exit program");
    	int choice = scan.nextInt();
    	scan.nextLine();//an extra nextLine() to 'eat' the NL in the buffer after nextLine() executes
    	return choice;
   }
    
    //execute the menu choice selected
    private void executeMenuItem(int choice) {
    	switch(choice) {
    	case DISPLAY_ROOM_INFORMATION:
    		displayRoomInfo();
    		break;
    	case ENTER_ROOM_BOOKING:
    		if(saveRoomBooking(makeBookingFromUserInput()))
    			saveBookingsToFile();
    		break;
    	case DELETE_BOOKING:
    		System.out.println("\nEnter booking to delete ");
    		deleteBooking(Calendar.getInstance());
    		break;	
    	case CHANGE_BOOKING:
    		System.out.println("\nEnter booking to change ");
    		changeBooking(Calendar.getInstance());
    		break;
    	case DISPLAY_BOOKING:
    		displayBooking(makeCalendarFromUserInput(null, true));
    		break;
        case DISPLAY_DAY_BOOKINGS:
    		displayDayBookings(makeCalendarFromUserInput(null, false));
    		break;
        case SAVE_BOOKINGS_TO_FILE:
        	saveBookingsToFile();
        	System.out.println("Current room bookings backed up to file\n");
    		break;	
        case LOAD_BOOKINGS_FROM_FILE:
        	loadBookingsFromFile();
        	System.out.println("Current room bookings loaded from file\n");
        	break;	
    	case EXIT:
    		System.out.println("\nExiting Room Booking Application");
    		break;
    	default: System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
    	}
    }
    
    private void displayRoomInfo() {
    	System.out.println(room.toString());
    }
    
    //to save  a booking into the roomBookings array
    //once safety booked,saveRoomBooking() returns true to signify success
    private boolean saveRoomBooking(RoomBooking booking) {
    	Calendar startTime = booking.getTimeBlock().getStartTime();
    	Calendar endTime = booking.getTimeBlock().getEndTime();
    	Calendar time = Calendar.getInstance();
    	time.clear();
    	time.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
    	time.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
    	time.set(Calendar.DATE, startTime.get(Calendar.DATE));
    	for(int startHour = startTime.get(Calendar.HOUR_OF_DAY); startHour<endTime.get(Calendar.HOUR_OF_DAY); startHour++) {
        	time.set(Calendar.HOUR_OF_DAY, startHour);
    		if(findBooking(time) != null) {
    			System.out.println("Booking time conflicted.\n");
    		    return false;
    		    }
        }
    	
    	getRoomBookings().add(booking);
    	System.out.println("Booking time and date saved.\n");
    	return true;
    }
    
    private boolean deleteBooking(Calendar cal) {
    	cal=makeCalendarFromUserInput(null,true);
	    if(findBooking(cal) == null) return false;
	    else {
	    	System.out.println("---------------\n"+findBooking(cal).toString()+"---------------\n");
	        String confirm = getResponseTo("Press 'Y' to confirm deletion, any other key to absort: ");
	    	if(confirm.equals("Y"))
	    	{
	    		if(getRoomBookings().remove(findBooking(cal)))
	    			System.out.println("Booking deleted\n"); 
	    	}
	    	return true;
	    }
	}
    
    private boolean changeBooking(Calendar cal) {
    	cal=makeCalendarFromUserInput(null,true);
	    if(findBooking(cal) == null) return false;
	    System.out.println("---------------\n"+findBooking(cal).toString()+"---------------");
	    
	    Calendar startCalendar = Calendar.getInstance();startCalendar.clear();
	    startCalendar.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE), processTimeString(getResponseTo("Enter New Start Time: ")), 0);
	    Calendar endCalendar = Calendar.getInstance();endCalendar.clear();
	    endCalendar.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE), processTimeString(getResponseTo("Enter New End Time: ")), 0);
	    
	    findBooking(cal).getTimeBlock().setStartTime(startCalendar);
	    findBooking(startCalendar).getTimeBlock().setEndTime(endCalendar);
	    System.out.println("Booking has been changed to:\n"+"---------------\n"+findBooking(startCalendar).toString()+"---------------");
	    return true;
	}
    
    //to display RoomBooking
    private RoomBooking displayBooking(Calendar cal) {
    	RoomBooking booking = findBooking(cal);
		if(booking == null)
		{
			System.out.print("No booking scheduled between "+cal.get(Calendar.HOUR_OF_DAY)+":00 and "+(cal.get(Calendar.HOUR_OF_DAY)+1)+":00\n");
			return null;
		}
		System.out.print("---------------\n"+booking.toString()+"---------------\n");
		return booking;
    }
    
    private void displayDayBookings(Calendar cal) {
    	RoomBooking booking;
    	for(int hour = 8;hour < 24;)
    	{
    		cal.set(Calendar.HOUR_OF_DAY, hour);
    		booking = displayBooking(cal);
    		if(booking != null)
    			hour = booking.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY);//skip to next unbooked Calendar hour
    		else
    			hour++;
    	}
    }
    
    private boolean saveBookingsToFile() {
    	try (	
				FileOutputStream file = new FileOutputStream("./src/CurrentRoomBookings.book");
		    	ObjectOutputStream oos = new ObjectOutputStream(file);
			){
    			if(getRoomBookings().size() > 0)
    			{
					for(RoomBooking booking:roomBookings)
					{
						oos.writeObject(booking);
					}
    			}
				oos.close();
				file.close();
			} 
    	catch (FileNotFoundException ex) {
    		System.out.println("Room bookings file not found ");
		}
		catch (IOException ex) {
    		ex.printStackTrace();
		}
		return true;
    }
   
    private ArrayList<RoomBooking> loadBookingsFromFile(){
    	RoomBooking bookings = null; 
    	try(
    		FileInputStream file = new FileInputStream("./src/CurrentRoomBookings.book");
    		ObjectInputStream ois = new ObjectInputStream(file);
    	)
    	{
    		while(true)
    		{
    			try {
    					bookings = (RoomBooking)ois.readObject();
    			}catch (EOFException ex) {
					break;
                }
    			if(findBooking(bookings.getTimeBlock().getStartTime()) == null)
    				getRoomBookings().add(bookings);
    		}
			ois.close();
			file.close();
    	}
        catch (FileNotFoundException ex) {
       	    System.out.println("Room bookings file not found ");
		}	
       	catch (ClassNotFoundException ex) {
       	    System.out.println("Class not found ");  
		}
	    catch (IOException ex) {
            ex.printStackTrace();
        }
    	return getRoomBookings();
    }
    
    private static String getResponseTo(String s) {
      System.out.print(s);
      return (scan.nextLine());
     }
    
    private RoomBooking makeBookingFromUserInput() {
    	String [] str = getResponseTo("\nEnter Client Name (as FirstName LastName): ").split(" ");
    	ContactInfo contactInfo = new ContactInfo(str[0],str[1],getResponseTo("Phone Number (e.g. 613-555-1212): "),getResponseTo("Organization(optional): "));
    	
    	Activity activity=new Activity(getResponseTo("Enter event category: "),getResponseTo("Enter detailed description of event: "));
    	
        TimeBlock timeBlock=new TimeBlock();
        Calendar startTime=makeCalendarFromUserInput(null, true);
        timeBlock.setStartTime(startTime);
        timeBlock.setEndTime(makeCalendarFromUserInput(startTime, true));
        return (new RoomBooking(timeBlock,contactInfo,activity));
    }
    
    //prompt the user for the date and time of a booking by
    //calling getResponseTo()twice,once for day/month/year, a second time for the hour
    //and then instantiates a new Calendar object,which can then be used to 
    //set find and display RoomBooking objects from the roomBookings array
    private Calendar makeCalendarFromUserInput(Calendar cal,boolean requestHour) {
    	Calendar newCal = Calendar.getInstance();
    	newCal.clear();
    	if(requestHour)
    	{
	    	if(cal == null)
	    	{
	    		String s=getResponseTo("Event Date (entered as DDMMYYYY): ");
	    		int date=Integer.parseInt(s.substring(0,2));
	    		int month=Integer.parseInt(s.substring(2,4));
	    		int year=Integer.parseInt(s.substring(4,8));
	    		int hour=processTimeString(getResponseTo("Start Time: "));
	    		newCal.set(Calendar.YEAR, year);
	    		newCal.set(Calendar.MONTH, month);
	    		newCal.set(Calendar.DATE, date);
	    		newCal.set(Calendar.HOUR_OF_DAY, hour);
	    	}
	    	else
	    	{
	    		int hour=processTimeString(getResponseTo("End Time: "));
	    		newCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
	    		newCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
	    		newCal.set(Calendar.DATE, cal.get(Calendar.DATE));
	    		newCal.set(Calendar.HOUR_OF_DAY, hour);
	    	}
    	}
    	else
    	{
    		String s=getResponseTo("Event Date (entered as DDMMYYYY): ");
    		int date=Integer.parseInt(s.substring(0,2));
    		int month=Integer.parseInt(s.substring(2,4));
    		int year=Integer.parseInt(s.substring(4,8));
    		newCal.set(Calendar.YEAR, year);
    		newCal.set(Calendar.MONTH, month);
    		newCal.set(Calendar.DATE, date);
    	}
    	return newCal;
    }
   
  //A_book(2016)Java String [WebPage].Retrieved from https://www.cnblogs.com/ABook/p/5527341.html
    private static int processTimeString(String t) {
    	if(t.contains("p.m") || t.contains("pm"))
       	 return	(Integer.parseInt(t.substring(0,2).trim())+12);
       	else
       	 return Integer.parseInt(t.replaceAll("a.m.|am|:00", "").trim());
    }
    
    private RoomBooking findBooking(Calendar cal) {
    	Calendar endTime = Calendar.getInstance();
    	endTime.clear();
    	endTime.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    	endTime.set(Calendar.MONTH, cal.get(Calendar.MONTH));
    	endTime.set(Calendar.DATE, cal.get(Calendar.DATE));
    	endTime.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1);
    	TimeBlock timeBlock=new TimeBlock(cal, endTime);
    	
    	for(RoomBooking getRoomBooking:roomBookings) {
    		if(getRoomBooking == null) 
    			continue;
    		if(getRoomBooking.getTimeBlock().overlaps(timeBlock)) 
    			return getRoomBooking;
    	}
    	return null;
    }
   
    //store RoomBooking objects loaded sequentially in the order in which they were created
    private ArrayList<RoomBooking> getRoomBookings() {
        return  roomBookings;
    }
    
    private void setRoom(Room room) {
    	this.room=room;
    }
    
    private Room getRoom() {
    	return room;
    }
    
 }
