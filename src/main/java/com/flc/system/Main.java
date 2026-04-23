package com.flc.system;

public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Add members
        Member m1 = new Member("M1", "Ali");
        Member m2 = new Member("M2", "Sara");

        system.addMember(m1);
        system.addMember(m2);

        // Add lessons
        system.addLesson(new Lesson("Yoga", "Saturday", "Morning"));
        system.addLesson(new Lesson("Zumba", "Saturday", "Afternoon"));
        system.addLesson(new Lesson("Box Fit", "Sunday", "Evening"));

        // Show lessons
        system.showLessons();

        // Book lesson
        system.bookLesson("M1", "Yoga");
        system.bookLesson("M2", "Yoga");

        System.out.println("\nAfter booking:");
        system.showLessons();

        // Cancel booking
        system.cancelBooking("M1", "Yoga");

        System.out.println("\nAfter cancellation:");
        system.showLessons();
    }
}

