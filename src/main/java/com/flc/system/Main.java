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

        // Generate lessons
        system.generateWeekendSchedule();

        System.out.println("Initial Lessons:");
        system.showLessons();

        // Bookings
        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING);
        system.bookLesson("M2", "Yoga", "Saturday", TimeSlot.MORNING);
        system.bookLesson("M3", "Yoga", "Saturday", TimeSlot.MORNING);
        system.bookLesson("M4", "Yoga", "Saturday", TimeSlot.MORNING);
        system.bookLesson("M5", "Yoga", "Saturday", TimeSlot.MORNING); // should fail

        System.out.println("\nAfter Bookings:");
        system.showLessons();

        // Cancel booking
        system.cancelBooking("M2", "Yoga", "Saturday", TimeSlot.MORNING);

        System.out.println("\nAfter Cancellation:");
        system.showLessons();
    }
}

