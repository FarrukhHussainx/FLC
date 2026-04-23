package com.flc.system;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {

    private List<Member> members = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    // Add member
    public void addMember(Member member) {
        members.add(member);
    }

    // Generate fixed weekend schedule (3 per day)
    public void generateWeekendSchedule() {

        double yogaPrice = 10;
        double zumbaPrice = 12;
        double boxFitPrice = 15;

        // Saturday
        lessons.add(new Lesson("Yoga", "Saturday", TimeSlot.MORNING, yogaPrice));
        lessons.add(new Lesson("Zumba", "Saturday", TimeSlot.AFTERNOON, zumbaPrice));
        lessons.add(new Lesson("Box Fit", "Saturday", TimeSlot.EVENING, boxFitPrice));

        // Sunday
        lessons.add(new Lesson("Yoga", "Sunday", TimeSlot.MORNING, yogaPrice));
        lessons.add(new Lesson("Zumba", "Sunday", TimeSlot.AFTERNOON, zumbaPrice));
        lessons.add(new Lesson("Box Fit", "Sunday", TimeSlot.EVENING, boxFitPrice));
    }

    // Show all lessons
    public void showLessons() {
        for (Lesson lesson : lessons) {
            System.out.println(lesson.getDetails());
        }
    }

    // Book lesson
    public boolean bookLesson(String memberId, String name, String day, TimeSlot slot) {

        Member member = findMember(memberId);
        Lesson lesson = findLesson(name, day, slot);

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
    public void cancelBooking(String memberId, String name, String day, TimeSlot slot) {
        Member member = findMember(memberId);
        Lesson lesson = findLesson(name, day, slot);

        if (member != null && lesson != null) {
            lesson.cancelBooking(member);
            bookings.removeIf(b ->
                    b.getMember().equals(member) &&
                            b.getLesson().equals(lesson)
            );
        }
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

    // Find lesson (correct version)
    private Lesson findLesson(String name, String day, TimeSlot slot) {
        for (Lesson l : lessons) {
            if (l.getLessonName().equalsIgnoreCase(name)
                    && l.getDay().equalsIgnoreCase(day)
                    && l.getTimeSlot() == slot) {
                return l;
            }
        }
        return null;
    }
}
