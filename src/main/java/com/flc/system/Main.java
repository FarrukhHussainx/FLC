package com.flc.system;

public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // =========================
        // ADD MEMBERS
        // =========================
        system.addMember(new Member("M1", "Ali"));
        system.addMember(new Member("M2", "Sara"));
        system.addMember(new Member("M3", "John"));
        system.addMember(new Member("M4", "Emma"));

        // =========================
        // GENERATE TIMETABLE
        // =========================
        system.generate8WeekSchedule();

        // =========================
        // VIEW TIMETABLE
        // =========================
        System.out.println("===== SATURDAY LESSONS =====");
        system.showLessonsByDay("Saturday");

        System.out.println("\n===== YOGA LESSONS =====");
        system.showLessonsByExercise("Yoga");

        // =========================
        // BOOK LESSONS
        // =========================
        System.out.println("\n===== BOOKING =====");

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M2", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M3", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M4", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        // This one should fail (capacity = 4)
        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        System.out.println("\n===== AFTER BOOKING =====");
        system.showLessons();

        // =========================
        // CHANGE BOOKING
        // =========================
        System.out.println("\n===== CHANGE BOOKING =====");

        system.changeBooking(
                "M1",
                "Yoga", "Saturday", TimeSlot.MORNING, 1,
                "Zumba", "Sunday", TimeSlot.AFTERNOON, 1
        );

        System.out.println("\n===== AFTER CHANGE =====");
        system.showLessons();

        // =========================
        // CANCEL BOOKING
        // =========================
        System.out.println("\n===== CANCEL BOOKING =====");

        system.cancelBooking(
                "M2",
                "Yoga", "Saturday", TimeSlot.MORNING, 1
        );

        System.out.println("\n===== AFTER CANCELLATION =====");
        system.showLessons();

        // =========================
        // ADD REVIEWS
        // =========================
        System.out.println("\n===== REVIEWS =====");

        system.addReview("M3", "Yoga", "Saturday", TimeSlot.MORNING, 1, 5, "Excellent session!");
        system.addReview("M4", "Yoga", "Saturday", TimeSlot.MORNING, 1, 4, "Very good workout!");

        // This should fail (not booked or wrong lesson after change)
        system.addReview("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1, 3, "Nice");

        System.out.println("\n===== FINAL LESSON STATE =====");
        system.showLessons();
    }
}