package com.flc.system;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private String lessonName;
    private String day; // Saturday / Sunday
    private TimeSlot timeSlot;
    private double price;

    private final int capacity = 4;
    private List<Member> bookedMembers = new ArrayList<>();

    public Lesson(String lessonName, String day, TimeSlot timeSlot, double price) {
        this.lessonName = lessonName;
        this.day = day;
        this.timeSlot = timeSlot;
        this.price = price;
    }

    public boolean bookMember(Member member) {
        // prevent duplicate booking
        if (bookedMembers.contains(member)) {
            System.out.println("Member already booked!");
            return false;
        }

        if (bookedMembers.size() < capacity) {
            bookedMembers.add(member);
            return true;
        }
        return false;
    }

    public void cancelBooking(Member member) {
        bookedMembers.remove(member);
    }

    public int getAvailableSlots() {
        return capacity - bookedMembers.size();
    }

    public String getDetails() {
        return lessonName + " | " + day + " | " + timeSlot +
                " | Price: £" + price +
                " | Slots left: " + getAvailableSlots();
    }

    public String getLessonName() { return lessonName; }
    public String getDay() { return day; }
    public TimeSlot getTimeSlot() { return timeSlot; }
    public double getPrice() { return price; }
    public List<Member> getBookedMembers() { return bookedMembers; }
}