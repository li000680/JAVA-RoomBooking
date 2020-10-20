/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:TimeBlock
 * Date:03/02/2020
 */
package cst8284.asgmt2.roomScheduler;
import java.io.Serializable;
import java.util.Calendar;

public class TimeBlock implements Serializable{
	private Calendar startTime;
	private Calendar endTime;
	public static final long seriaVersionUID = 1L;
	
	//no-arg TimeBlock constructor should instantiate a RoomBooking 
	//from 8am-midnight(endTime=24)
	//HuaWangXin(2016).Deeply understanding of Calendar's getters and setters[WebPage].Retrieved from https://blog.csdn.net/huawangxin/article/details/53232531
	public TimeBlock() {
		startTime=Calendar.getInstance();
		endTime=Calendar.getInstance();
		int year = startTime.get(Calendar.YEAR);
		int month = endTime.get(Calendar.MONTH);
		int date = startTime.get(Calendar.DATE);
		startTime.clear();
		endTime.clear();
		
		startTime.set(Calendar.YEAR, year);
		startTime.set(Calendar.MONTH, month);
		startTime.set(Calendar.DATE, date);
		startTime.set(Calendar.HOUR_OF_DAY, 8);
		
		endTime.set(Calendar.YEAR, year);
		endTime.set(Calendar.MONTH, month);
		endTime.set(Calendar.DATE, date);
		endTime.set(Calendar.HOUR_OF_DAY, 23);
	}
	
	public TimeBlock(Calendar start,Calendar end) {
		setStartTime(start);
		setEndTime(end);
	}
	
	public void setStartTime(Calendar startTime) {
		this.startTime=startTime;
	}
	
	public void setEndTime(Calendar endTime) {
		this.endTime=endTime;
	}
	
	public Calendar getStartTime() {
		return startTime;
	}
	
	public Calendar getEndTime() {
		return endTime;
	}
	
	//overlaps() allows the user to query whether a new TimeBlock will conflict with this TimeBlock
	//if conflict occurs,then return true,else return false
	//Development(2011)How to use Calendar compareTo()[WebPage].Retrieved from https://www.pocketdigi.com/20110712/383.html
	public boolean overlaps(TimeBlock newBlock) {
		Calendar newStartTime=newBlock.getStartTime();
		Calendar newEndTime=newBlock.getEndTime();
		return (newStartTime.compareTo(startTime)>=0)&& (endTime.compareTo(newEndTime)>=0);
	}
	
	//However, I did not use this method in Assignment1
	public int duration() {
		return(getEndTime().get(Calendar.HOUR_OF_DAY)-getStartTime().get(Calendar.HOUR_OF_DAY));
	}
    
	public String toString() {
		return (getStartTime().get(Calendar.HOUR_OF_DAY)+":00 - "+getEndTime().get(Calendar.HOUR_OF_DAY)+":00");
	}
	
	
}
