## JAVA-RoomBooking
* RoomScheduler & RoomBooking is an aggregation relationship; RoomScheduler & Room is a composition relationship
* RoomBooking and {ContactInfo, Activity, TimeBlock} is a composition relationship
* Implemented inheritance: Room is the abstract super class; Classroom, ComputerLab, and Boardroom are subclasses
* Used ArrayList to manage RoomBooking objects
* Implemented program Input/Output operations by storing RoomBooking objects to file “CurrentRoomBookings.book” using serialization; exception handling including
