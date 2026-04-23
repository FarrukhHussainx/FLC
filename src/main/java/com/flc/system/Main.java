package com.flc.system;

public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Add members
        system.addMember(new Member("M1", "Ali"));
        system.addMember(new Member("M2", "Sara"));
        system.addMember(new Member("M3", "John"));
        system.addMember(new Member("M4", "Emma"));
        system.addMember(new Member("M5", "David"));

        // Generate timetable
        system.generate8WeekSchedule();

        System.out.println("INITIAL LESSONS:");
        system.showLessons();

        // Book lessons
        System.out.println("\nBOOKING...");
        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M2", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M3", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M4", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M5", "Yoga", "Saturday", TimeSlot.MORNING, 1); // should fail

        System.out.println("\nAFTER BOOKING:");
        system.showLessons();

        // Cancel booking
        System.out.println("\nCANCELLING...");
        system.cancelBooking("M2", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        System.out.println("\nAFTER CANCELLATION:");
        system.showLessons();
    }
}

