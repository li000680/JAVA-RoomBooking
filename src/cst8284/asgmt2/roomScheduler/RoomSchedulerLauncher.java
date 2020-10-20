/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:RoomSchedulerLauncher
 * Date:03/02/2020
 */
package cst8284.asgmt2.roomScheduler;

import  cst8284.asgmt2.room.ComputerLab;

public class RoomSchedulerLauncher  {

	public static void main(String[] args) {
		ComputerLab cl = new ComputerLab();
		cl.setRoomNumber("B119");
		(new RoomScheduler(cl)).launch();
  }
}
