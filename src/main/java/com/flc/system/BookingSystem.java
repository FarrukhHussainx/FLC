package com.flc.system;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {

    private List<Member> members = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    // Add member
    public void addMember(Member member) {
        if (member != null) {
            members.add(member);
        }
    }

    // Generate 8-week schedule
    public void generate8WeekSchedule() {

        double yogaPrice = 10;
        double zumbaPrice = 12;
        double boxFitPrice = 15;

        for (int week = 1; week <= 8; week++) {

            // Saturday
            lessons.add(new Lesson("Yoga", "Saturday", TimeSlot.MORNING, yogaPrice, week));
            lessons.add(new Lesson("Zumba", "Saturday", TimeSlot.AFTERNOON, zumbaPrice, week));
            lessons.add(new Lesson("Box Fit", "Saturday", TimeSlot.EVENING, boxFitPrice, week));

            // Sunday
            lessons.add(new Lesson("Yoga", "Sunday", TimeSlot.MORNING, yogaPrice, week));
            lessons.add(new Lesson("Zumba", "Sunday", TimeSlot.AFTERNOON, zumbaPrice, week));
            lessons.add(new Lesson("Box Fit", "Sunday", TimeSlot.EVENING, boxFitPrice, week));
        }
    }

    // Show lessons
    public void showLessons() {
        for (Lesson lesson : lessons) {
            System.out.println(lesson.getDetails());
        }
    }

    // Book lesson
    public boolean bookLesson(String memberId, String name, String day, TimeSlot slot, int week) {

        Member member = findMember(memberId);
        Lesson lesson = findLesson(name, day, slot, week);

        if (member == null) {
            System.out.println("Member not found!");
            return false;
        }

        if (lesson == null) {
            System.out.println("Lesson not found!");
            return false;
        }

        if (lesson.bookMember(member)) {
            bookings.add(new Booking(member, lesson));
            return true;
        } else {
            System.out.println("Lesson full!");
            return false;
        }
    }

    // Cancel booking
    public void cancelBooking(String memberId, String name, String day, TimeSlot slot, int week) {

        Member member = findMember(memberId);
        Lesson lesson = findLesson(name, day, slot, week);

        if (member == null || lesson == null) {
            System.out.println("Invalid cancellation!");
            return;
        }

        lesson.cancelBooking(member);

        bookings.removeIf(b ->
                b.getMember().equals(member) &&
                        b.getLesson().equals(lesson)
        );
    }

    // Find member
    private Member findMember(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    // Find lesson
    private Lesson findLesson(String name, String day, TimeSlot slot, int week) {
        for (Lesson l : lessons) {
            if (l.getLessonName().equalsIgnoreCase(name)
                    && l.getDay().equalsIgnoreCase(day)
                    && l.getTimeSlot() == slot
                    && l.getWeekNumber() == week) {
                return l;
            }
        }
        return null;
    }
}