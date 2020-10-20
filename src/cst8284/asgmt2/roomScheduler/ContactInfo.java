/*
 * Course Name:cst 8284 310,314
 * Student Name:Li Wei
 * Class Name:ContactInfo
 * Date:03/02/2020
 */
package cst8284.asgmt2.roomScheduler;

import java.io.Serializable;

public class ContactInfo implements Serializable{
     //all these values are passed as Strings to RoomScheduler's setters
	 private String firstName;
     private String lastName;
     private String phoneNumber;
     private String organization;
     public static final long seriaVersionUID = 1L;
     
     public ContactInfo(String firstName,String lastName,String phoneNumber) {
    	 this(firstName,lastName,phoneNumber,"Algonquin College");
     }
     
     public ContactInfo(String firstName,String lastName,String phoneNumber,String organization) {
    	 setFirstName(firstName);
    	 setLastName(lastName);
    	 setPhoneNumber(phoneNumber);
    	 setOrganization(organization);
     }
     
     public void setFirstName(String firstName) {
    	 this.firstName=firstName;
     }
     
     public void setLastName(String lastName) {
    	 this.lastName=lastName;
     }
     
     public void setPhoneNumber(String phoneNumber) {
    	 this.phoneNumber=phoneNumber;
     }
     
     public void setOrganization(String organization) {
    	 this.organization=organization;
    	 
     }
     
     public String getFirstName(String firstName) {
    	 return firstName;
     }
     
     public String getLastName(String lastName) {
    	 return lastName;
     }
     
     public String getPhoneNumber(String phoneNumber) {
    	 return phoneNumber;
     }
     
     public String getOrganization(String organization) {
    	 return organization;
    	 
     }
     
     public String toString() {
    	 return ("Contact Informatio: "+firstName+" "+lastName+"\nPhone: "+phoneNumber+"\n"+organization);
     }
     
}
