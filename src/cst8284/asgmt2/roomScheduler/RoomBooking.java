/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:RoomBooking
 * Date:03/02/2020
 */
package cst8284.asgmt2.roomScheduler;
import java.io.*;

public class RoomBooking implements Serializable{
	public static final long serialVersionUID = 1L;  
	private ContactInfo contactInfo;
	private Activity activity;
	private TimeBlock timeBlock;
	
    public RoomBooking(TimeBlock timeBlock,ContactInfo contactInfo,Activity activity) {
		setTimeBlock(timeBlock);
		setContactInfo(contactInfo);
		setActivity(activity);
	}
	
	public void setTimeBlock(TimeBlock timeBlock) {
		this.timeBlock=timeBlock;
	}
	
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo=contactInfo;
	}
	
	public void setActivity(Activity activity) {
		this.activity=activity;
	}
	
	public TimeBlock getTimeBlock() {
		return timeBlock;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
	public Activity getActivity() {
		return this.activity;
	}
	
	public String toString() {
		return(getTimeBlock().toString()+"\n"+
		getActivity().toString()+"\n"+
		getContactInfo().toString()+"\n");
	}
	
}
