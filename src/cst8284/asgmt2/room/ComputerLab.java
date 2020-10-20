/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:ComputerLab
 * Date:03/02/2020
 */
package cst8284.asgmt2.room;

public final class ComputerLab extends Room {
	private int DEFAULT_SEATS=30;
	private int seats = DEFAULT_SEATS;
	
	public ComputerLab() {
		super();
	}
	@Override
	protected int getSeats() {
		return seats;
	}
	@Override
	protected String getRoomType() {
		return "computer lab";
	}
	@Override
	protected String getDetails() {
		return "contains outlets for 30 laptops";
	}

}
