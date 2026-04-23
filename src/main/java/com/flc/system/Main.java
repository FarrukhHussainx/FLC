package com.flc.system;

public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        system.addMember(new Member("M1", "Ali"));
        system.addMember(new Member("M2", "Sara"));

        system.generate8WeekSchedule();

        // 🔍 Check timetable by day
        System.out.println("SATURDAY LESSONS:");
        system.showLessonsByDay("Saturday");

        // 🔍 Check timetable by exercise
        System.out.println("\nYOGA LESSONS:");
        system.showLessonsByExercise("Yoga");

        // Book lesson
        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        // Change booking
        system.changeBooking(
                "M1",
                "Yoga", "Saturday", TimeSlot.MORNING, 1,
                "Zumba", "Sunday", TimeSlot.AFTERNOON, 1
        );

        System.out.println("\nFINAL LESSONS:");
        system.showLessons();

        // Cancel booking
        System.out.println("\nCANCELLING BOOKING...");
        system.cancelBooking(
                "M1",
                "Zumba", "Sunday", TimeSlot.AFTERNOON, 1
        );

        System.out.println("\nAFTER CANCELLATION:");
        system.showLessons();
    }
}