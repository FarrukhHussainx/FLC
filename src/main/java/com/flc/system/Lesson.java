package com.flc.system;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String lessonName;
    private String day; // Saturday or Sunday
    private String time; // Morning, Afternoon, Evening
    private int capacity = 4;

    private List<Member> bookedMembers = new ArrayList<>();

    public Lesson(String lessonName, String day, String time) {
        this.lessonName = lessonName;
        this.day = day;
        this.time = time;
    }

    public boolean bookMember(Member member) {
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
        return lessonName + " - " + day + " - " + time +
                " | Slots left: " + getAvailableSlots();
    }

    public List<Member> getBookedMembers() {
        return bookedMembers;
    }

    public String getLessonName() {
        return lessonName;
    }
}