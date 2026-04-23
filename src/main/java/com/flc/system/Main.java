package com.flc.system;

public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // =========================
        // ADD MEMBERS (ORIGINAL)
        // =========================
        system.addMember(new Member("M1", "Ali"));
        system.addMember(new Member("M2", "Sara"));
        system.addMember(new Member("M3", "John"));
        system.addMember(new Member("M4", "Emma"));

        // =========================
        // ADD MORE MEMBERS (NEW)
        // =========================
        system.addMember(new Member("M5", "David"));
        system.addMember(new Member("M6", "Aisha"));
        system.addMember(new Member("M7", "Tom"));
        system.addMember(new Member("M8", "Olivia"));
        system.addMember(new Member("M9", "Liam"));
        system.addMember(new Member("M10", "Sophia"));

        // =========================
        // GENERATE TIMETABLE
        // =========================
        system.generate8WeekSchedule();

        // =========================
        // VIEW TIMETABLE (ORIGINAL)
        // =========================
        System.out.println("===== SATURDAY LESSONS =====");
        system.showLessonsByDay("Saturday");

        System.out.println("\n===== YOGA LESSONS =====");
        system.showLessonsByExercise("Yoga");

        // =========================
        // BOOK LESSONS (ORIGINAL)
        // =========================
        System.out.println("\n===== BOOKING =====");

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M2", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M3", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M4", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        // This one should fail
        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        // =========================
        // EXTRA BOOKINGS (WEEKS 1–4)
        // =========================
        system.bookLesson("M5", "Zumba", "Sunday", TimeSlot.AFTERNOON, 1);
        system.bookLesson("M6", "Box Fit", "Saturday", TimeSlot.EVENING, 1);

        system.bookLesson("M7", "Yoga", "Sunday", TimeSlot.MORNING, 2);
        system.bookLesson("M8", "Zumba", "Saturday", TimeSlot.AFTERNOON, 2);
        system.bookLesson("M9", "Box Fit", "Sunday", TimeSlot.EVENING, 2);

        system.bookLesson("M10", "Yoga", "Saturday", TimeSlot.MORNING, 3);
        system.bookLesson("M1", "Zumba", "Sunday", TimeSlot.AFTERNOON, 3);

        system.bookLesson("M2", "Box Fit", "Saturday", TimeSlot.EVENING, 4);
        system.bookLesson("M3", "Yoga", "Sunday", TimeSlot.MORNING, 4);

        System.out.println("\n===== AFTER BOOKING =====");
        system.showLessons();

        // =========================
        // CHANGE BOOKING (ORIGINAL)
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
        // CANCEL BOOKING (ORIGINAL)
        // =========================
        System.out.println("\n===== CANCEL BOOKING =====");

        system.cancelBooking(
                "M2",
                "Yoga", "Saturday", TimeSlot.MORNING, 1
        );

        System.out.println("\n===== AFTER CANCELLATION =====");
        system.showLessons();

        // =========================
        // ADD REVIEWS (ORIGINAL)
        // =========================
        System.out.println("\n===== REVIEWS =====");

        system.addReview("M3", "Yoga", "Saturday", TimeSlot.MORNING, 1, 5, "Excellent session!");
        system.addReview("M4", "Yoga", "Saturday", TimeSlot.MORNING, 1, 4, "Very good workout!");

        system.addReview("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1, 3, "Nice"); // fail

        // =========================
        // ADD MORE REVIEWS (TOTAL ≥ 20)
        // =========================
        system.addReview("M5", "Zumba", "Sunday", TimeSlot.AFTERNOON, 1, 4, "Fun");
        system.addReview("M6", "Box Fit", "Saturday", TimeSlot.EVENING, 1, 5, "Great");

        system.addReview("M7", "Yoga", "Sunday", TimeSlot.MORNING, 2, 3, "Okay");
        system.addReview("M8", "Zumba", "Saturday", TimeSlot.AFTERNOON, 2, 5, "Amazing");
        system.addReview("M9", "Box Fit", "Sunday", TimeSlot.EVENING, 2, 4, "Good");

        system.addReview("M10", "Yoga", "Saturday", TimeSlot.MORNING, 3, 5, "Excellent");
        system.addReview("M1", "Zumba", "Sunday", TimeSlot.AFTERNOON, 3, 4, "Nice");

        system.addReview("M2", "Box Fit", "Saturday", TimeSlot.EVENING, 4, 3, "Average");
        system.addReview("M3", "Yoga", "Sunday", TimeSlot.MORNING, 4, 5, "Loved it");

        // extra to ensure 20+
        system.addReview("M6", "Box Fit", "Saturday", TimeSlot.EVENING, 1, 4, "Repeat good");
        system.addReview("M8", "Zumba", "Saturday", TimeSlot.AFTERNOON, 2, 4, "Again nice");
        system.addReview("M9", "Box Fit", "Sunday", TimeSlot.EVENING, 2, 5, "Strong");
        system.addReview("M10", "Yoga", "Saturday", TimeSlot.MORNING, 3, 4, "Solid");

        System.out.println("\n===== FINAL LESSON STATE =====");
        system.showLessons();

        // =========================
        // REPORTS (ORIGINAL)
        // =========================
        system.generateLessonReportFirst4Weeks();
        system.generateHighestIncomeReportFirst4Weeks();
    }
}