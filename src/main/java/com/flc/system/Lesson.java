package com.flc.system;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private String lessonName;
    private String day;
    private TimeSlot timeSlot;
    private double price;
    private int weekNumber;

    private final int capacity = 4;
    private List<Member> bookedMembers = new ArrayList<>();

    public Lesson(String lessonName, String day, TimeSlot timeSlot, double price, int weekNumber) {
        this.lessonName = lessonName;
        this.day = day;
        this.timeSlot = timeSlot;
        this.price = price;
        this.weekNumber = weekNumber;
    }

    public boolean bookMember(Member member) {
        if (member == null) return false;

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
        return "Week " + weekNumber + " | " +
                lessonName + " | " + day + " | " + timeSlot +
                " | Price: £" + price +
                " | Slots left: " + getAvailableSlots();
    }

    public String getLessonName() { return lessonName; }
    public String getDay() { return day; }
    public TimeSlot getTimeSlot() { return timeSlot; }
    public double getPrice() { return price; }
    public int getWeekNumber() { return weekNumber; }
    public List<Member> getBookedMembers() { return bookedMembers; }
}