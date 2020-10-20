/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:BoardRoom
 * Date:03/02/2020
 */
package cst8284.asgmt2.room;

public final class BoardRoom extends Room {
    private int seats;
	
    public BoardRoom() {
		super();
	}
    @Override
	protected int getSeats() {
    	return seats;
	}
    @Override
	protected String getRoomType() {
		return "board room";
	}
    @Override
	protected String getDetails() {
		return "conference call enabled";
	}
}
