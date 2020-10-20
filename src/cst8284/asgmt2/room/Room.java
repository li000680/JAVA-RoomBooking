/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:Room 
 * Date:03/02/2020
 */
package cst8284.asgmt2.room;

public abstract class Room {
	
	private static final String DEFAULT_ROOM_NUMBER = "unknown room number";
	private String roomNumber;
	public static final long seriaVersionUID = 1L;
	
	protected Room() {this(DEFAULT_ROOM_NUMBER);}
	protected Room(String roomNum) { setRoomNumber(roomNum); }
	
	public void setRoomNumber(String roomNum) {roomNumber = roomNum;}
	public String getRoomNumber() {return roomNumber;}
	
        protected abstract String getRoomType() ;
	protected abstract int getSeats();
	protected abstract String getDetails();
	
	public String toString( ) { return getRoomNumber() + " is a " +
		getRoomType() + " with " + getSeats() + " seats; " + getDetails() +"\n" ;}
}
