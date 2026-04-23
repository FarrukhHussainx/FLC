package com.flc.system;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();
        Scanner sc = new Scanner(System.in);

        // =========================
        // PRELOAD DATA (UNCHANGED)
        // =========================
        system.addMember(new Member("M1", "Ali"));
        system.addMember(new Member("M2", "Sara"));
        system.addMember(new Member("M3", "John"));
        system.addMember(new Member("M4", "Emma"));
        system.addMember(new Member("M5", "David"));
        system.addMember(new Member("M6", "Aisha"));
        system.addMember(new Member("M7", "Tom"));
        system.addMember(new Member("M8", "Olivia"));
        system.addMember(new Member("M9", "Liam"));
        system.addMember(new Member("M10", "Sophia"));

        system.generate8WeekSchedule();

        // =========================
        // MENU LOOP
        // =========================
        int choice;

        do {
            System.out.println("\n========= FLC BOOKING SYSTEM =========");
            System.out.println("1. View timetable by day");
            System.out.println("2. View timetable by exercise");
            System.out.println("3. Book lesson");
            System.out.println("4. Change booking");
            System.out.println("5. Cancel booking");
            System.out.println("6. Add review");
            System.out.println("7. Show all lessons");
            System.out.println("8. Generate reports (Weeks 1–4)");
            System.out.println("9. Auto book 20 lessons + add ratings");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter day (Saturday/Sunday): ");
                    String day = sc.nextLine();
                    system.showLessonsByDay(day);
                    break;

                case 2:
                    System.out.print("Enter exercise name: ");
                    String exercise = sc.nextLine();
                    system.showLessonsByExercise(exercise);
                    break;

                case 3:
                    System.out.print("Member ID: ");
                    String mId = sc.nextLine();
                    System.out.print("Lesson name: ");
                    String name = sc.nextLine();
                    System.out.print("Day: ");
                    String d = sc.nextLine();
                    System.out.print("Time (MORNING/AFTERNOON/EVENING): ");
                    TimeSlot slot = TimeSlot.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Week: ");
                    int w = sc.nextInt();

                    system.bookLesson(mId, name, d, slot, w);
                    break;

                case 4:
                    System.out.print("Member ID: ");
                    String cmId = sc.nextLine();

                    System.out.println("OLD BOOKING:");
                    System.out.print("Lesson name: ");
                    String oldName = sc.nextLine();
                    System.out.print("Day: ");
                    String oldDay = sc.nextLine();
                    System.out.print("Time: ");
                    TimeSlot oldSlot = TimeSlot.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Week: ");
                    int oldWeek = sc.nextInt();
                    sc.nextLine();

                    System.out.println("NEW BOOKING:");
                    System.out.print("Lesson name: ");
                    String newName = sc.nextLine();
                    System.out.print("Day: ");
                    String newDay = sc.nextLine();
                    System.out.print("Time: ");
                    TimeSlot newSlot = TimeSlot.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Week: ");
                    int newWeek = sc.nextInt();

                    system.changeBooking(cmId, oldName, oldDay, oldSlot, oldWeek,
                            newName, newDay, newSlot, newWeek);
                    break;

                case 5:
                    System.out.print("Member ID: ");
                    String cancelId = sc.nextLine();
                    System.out.print("Lesson name: ");
                    String cancelName = sc.nextLine();
                    System.out.print("Day: ");
                    String cancelDay = sc.nextLine();
                    System.out.print("Time: ");
                    TimeSlot cancelSlot = TimeSlot.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Week: ");
                    int cancelWeek = sc.nextInt();

                    system.cancelBooking(cancelId, cancelName, cancelDay, cancelSlot, cancelWeek);
                    break;

                case 6:
                    System.out.print("Member ID: ");
                    String rId = sc.nextLine();
                    System.out.print("Lesson name: ");
                    String rName = sc.nextLine();
                    System.out.print("Day: ");
                    String rDay = sc.nextLine();
                    System.out.print("Time: ");
                    TimeSlot rSlot = TimeSlot.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Week: ");
                    int rWeek = sc.nextInt();
                    System.out.print("Rating (1-5): ");
                    int rating = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Comment: ");
                    String comment = sc.nextLine();

                    system.addReview(rId, rName, rDay, rSlot, rWeek, rating, comment);
                    break;

                case 7:
                    system.showLessons();
                    break;

                case 8:
                    system.generateLessonReportFirst4Weeks();
                    system.generateHighestIncomeReportFirst4Weeks();
                    break;

                case 9:
                    System.out.println("\n===== AUTO BOOKING 20 LESSONS + REVIEWS =====");

                    // =========================
                    // AUTO BOOKINGS (20 LESSONS)
                    // =========================
                    system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);
                    system.bookLesson("M2", "Yoga", "Saturday", TimeSlot.MORNING, 1);
                    system.bookLesson("M3", "Zumba", "Sunday", TimeSlot.AFTERNOON, 1);
                    system.bookLesson("M4", "Box Fit", "Saturday", TimeSlot.EVENING, 1);
                    system.bookLesson("M5", "Yoga", "Sunday", TimeSlot.MORNING, 2);

                    system.bookLesson("M6", "Zumba", "Saturday", TimeSlot.AFTERNOON, 2);
                    system.bookLesson("M7", "Box Fit", "Sunday", TimeSlot.EVENING, 2);
                    system.bookLesson("M8", "Yoga", "Saturday", TimeSlot.MORNING, 2);
                    system.bookLesson("M9", "Zumba", "Sunday", TimeSlot.AFTERNOON, 3);
                    system.bookLesson("M10", "Box Fit", "Saturday", TimeSlot.EVENING, 3);

                    system.bookLesson("M1", "Yoga", "Sunday", TimeSlot.MORNING, 3);
                    system.bookLesson("M2", "Zumba", "Saturday", TimeSlot.AFTERNOON, 3);
                    system.bookLesson("M3", "Box Fit", "Sunday", TimeSlot.EVENING, 4);
                    system.bookLesson("M4", "Yoga", "Saturday", TimeSlot.MORNING, 4);
                    system.bookLesson("M5", "Zumba", "Sunday", TimeSlot.AFTERNOON, 4);

                    system.bookLesson("M6", "Box Fit", "Saturday", TimeSlot.EVENING, 4);
                    system.bookLesson("M7", "Yoga", "Sunday", TimeSlot.MORNING, 1);
                    system.bookLesson("M8", "Zumba", "Saturday", TimeSlot.AFTERNOON, 1);
                    system.bookLesson("M9", "Box Fit", "Sunday", TimeSlot.EVENING, 2);
                    system.bookLesson("M10", "Yoga", "Saturday", TimeSlot.MORNING, 3);

                    // =========================
                    // AUTO REVIEWS (20 REVIEWS)
                    // =========================
                    system.addReview("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1, 5, "Excellent");
                    system.addReview("M2", "Yoga", "Saturday", TimeSlot.MORNING, 1, 4, "Good");
                    system.addReview("M3", "Zumba", "Sunday", TimeSlot.AFTERNOON, 1, 3, "Ok");
                    system.addReview("M4", "Box Fit", "Saturday", TimeSlot.EVENING, 1, 5, "Great");
                    system.addReview("M5", "Yoga", "Sunday", TimeSlot.MORNING, 2, 4, "Nice");

                    system.addReview("M6", "Zumba", "Saturday", TimeSlot.AFTERNOON, 2, 5, "Amazing");
                    system.addReview("M7", "Box Fit", "Sunday", TimeSlot.EVENING, 2, 4, "Good");
                    system.addReview("M8", "Yoga", "Saturday", TimeSlot.MORNING, 2, 5, "Excellent");
                    system.addReview("M9", "Zumba", "Sunday", TimeSlot.AFTERNOON, 3, 3, "Ok");
                    system.addReview("M10", "Box Fit", "Saturday", TimeSlot.EVENING, 3, 4, "Nice");

                    system.addReview("M1", "Yoga", "Sunday", TimeSlot.MORNING, 3, 5, "Loved it");
                    system.addReview("M2", "Zumba", "Saturday", TimeSlot.AFTERNOON, 3, 4, "Good");
                    system.addReview("M3", "Box Fit", "Sunday", TimeSlot.EVENING, 4, 5, "Strong");
                    system.addReview("M4", "Yoga", "Saturday", TimeSlot.MORNING, 4, 4, "Nice");
                    system.addReview("M5", "Zumba", "Sunday", TimeSlot.AFTERNOON, 4, 3, "Ok");

                    system.addReview("M6", "Box Fit", "Saturday", TimeSlot.EVENING, 4, 4, "Good");
                    system.addReview("M7", "Yoga", "Sunday", TimeSlot.MORNING, 1, 5, "Excellent");
                    system.addReview("M8", "Zumba", "Saturday", TimeSlot.AFTERNOON, 1, 4, "Nice");
                    system.addReview("M9", "Box Fit", "Sunday", TimeSlot.EVENING, 2, 5, "Great");
                    system.addReview("M10", "Yoga", "Saturday", TimeSlot.MORNING, 3, 4, "Solid");

                    System.out.println("Auto booking + reviews completed!");
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}