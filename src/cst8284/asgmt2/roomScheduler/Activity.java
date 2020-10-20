/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:Activity
 * Date:03/02/2020
 */
package cst8284.asgmt2.roomScheduler;

import java.io.Serializable;

public class Activity implements Serializable{
    private String descriptionOfWork;//a more general description to the event
    private String category;//a one or two-word category for the event
    public static final long seriaVersionUID = 1L;
    
    public Activity(String category,String description) {
    	setDescriptionOfWork(description);
    	setCategory(category);
    }
    
    public void setDescriptionOfWork(String description) {
    	this.descriptionOfWork=description;
    }
    
    public void setCategory(String category) {
    	this.category=category;
    }
    
    
    public String getDescriptionOfWork() {
    	return descriptionOfWork;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public String toString() {
    	return ("Event: "+category+"\nDescription: "+descriptionOfWork);
    }
}
