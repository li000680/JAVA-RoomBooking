/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:Classroom
 * Date:03/02/2020
 */
package cst8284.asgmt2.room;

public final class Classroom extends Room {
	private int DEFAULT_SEATS=120;
	private int seats = DEFAULT_SEATS;
	
	public Classroom () {
		super();
	}
	@Override
	protected int getSeats() {
		return seats;
	}
	@Override
	protected String getRoomType() {
		return "class room";
	}
	@Override
	protected String getDetails() {
		return "contains overhead projector";
	}

}
