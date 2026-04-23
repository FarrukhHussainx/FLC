package com.flc.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingSystem {

    private List<Member> members = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    // =========================
    // MEMBERS
    // =========================
    public void addMember(Member member) {
        if (member != null) members.add(member);
    }

    // =========================
    // 8 WEEK TIMETABLE
    // =========================
    public void generate8WeekSchedule() {

        double yogaPrice = 10;
        double zumbaPrice = 12;
        double boxFitPrice = 15;

        for (int week = 1; week <= 8; week++) {

            lessons.add(new Lesson("Yoga", "Saturday", TimeSlot.MORNING, yogaPrice, week));
            lessons.add(new Lesson("Zumba", "Saturday", TimeSlot.AFTERNOON, zumbaPrice, week));
            lessons.add(new Lesson("Box Fit", "Saturday", TimeSlot.EVENING, boxFitPrice, week));

            lessons.add(new Lesson("Yoga", "Sunday", TimeSlot.MORNING, yogaPrice, week));
            lessons.add(new Lesson("Zumba", "Sunday", TimeSlot.AFTERNOON, zumbaPrice, week));
            lessons.add(new Lesson("Box Fit", "Sunday", TimeSlot.EVENING, boxFitPrice, week));
        }
    }

    // =========================
    // DISPLAY
    // =========================
    public void showLessons() {
        for (Lesson lesson : lessons) {
            System.out.println(lesson.getDetails());
        }
    }

    public void showLessonsByDay(String day) {
        for (Lesson l : lessons) {
            if (l.getDay().equalsIgnoreCase(day)) {
                System.out.println(l.getDetails());
            }
        }
    }

    public void showLessonsByExercise(String exercise) {
        for (Lesson l : lessons) {
            if (l.getLessonName().equalsIgnoreCase(exercise)) {
                System.out.println(l.getDetails());
            }
        }
    }

    // =========================
    // BOOKING
    // =========================
    public boolean bookLesson(String memberId, String name, String day, TimeSlot slot, int week) {

        Member member = findMember(memberId);
        Lesson lesson = findLesson(name, day, slot, week);

        if (member == null || lesson == null) return false;

        if (lesson.bookMember(member)) {
            bookings.add(new Booking(member, lesson));
            return true;
        }

        System.out.println("Lesson full!");
        return false;
    }

    // =========================
    // CANCEL
    // =========================
    public void cancelBooking(String memberId, String name, String day, TimeSlot slot, int week) {

        Member member = findMember(memberId);
        Lesson lesson = findLesson(name, day, slot, week);

        if (member == null || lesson == null) return;

        lesson.cancelBooking(member);

        bookings.removeIf(b ->
                b.getMember().equals(member) &&
                        b.getLesson().equals(lesson)
        );
    }

    // =========================
    // CHANGE BOOKING
    // =========================
    public boolean changeBooking(String memberId,
                                 String oldName, String oldDay, TimeSlot oldSlot, int oldWeek,
                                 String newName, String newDay, TimeSlot newSlot, int newWeek) {

        Member member = findMember(memberId);

        Lesson oldLesson = findLesson(oldName, oldDay, oldSlot, oldWeek);
        Lesson newLesson = findLesson(newName, newDay, newSlot, newWeek);

        if (member == null || oldLesson == null || newLesson == null) return false;

        if (!oldLesson.getBookedMembers().contains(member)) return false;

        if (newLesson.getAvailableSlots() <= 0) return false;

        oldLesson.cancelBooking(member);
        newLesson.bookMember(member);

        bookings.removeIf(b ->
                b.getMember().equals(member) &&
                        b.getLesson().equals(oldLesson)
        );

        bookings.add(new Booking(member, newLesson));

        return true;
    }

    // =========================
    // REVIEWS
    // =========================
    public boolean addReview(String memberId,
                             String name,
                             String day,
                             TimeSlot slot,
                             int week,
                             int rating,
                             String comment) {

        Member member = findMember(memberId);
        Lesson lesson = findLesson(name, day, slot, week);

        if (member == null || lesson == null) return false;
        if (rating < 1 || rating > 5) return false;
        if (!lesson.getBookedMembers().contains(member)) return false;

        for (Review r : lesson.getReviews()) {
            if (r.getMember().equals(member)) return false;
        }

        lesson.addReview(new Review(member, lesson, rating, comment));
        return true;
    }

    // =========================
    // REPORT 1
    // =========================
    public void generateLessonReportFirst4Weeks() {

        System.out.println("\n===== LESSON REPORT (WEEKS 1–4) =====");

        for (Lesson lesson : lessons) {

            if (lesson.getWeekNumber() <= 4) {

                int memberCount = lesson.getBookedMembers().size();
                double avgRating = lesson.getAverageRating();

                System.out.println(
                        "Week " + lesson.getWeekNumber() + " | " +
                                lesson.getLessonName() + " | " +
                                lesson.getDay() + " | " +
                                lesson.getTimeSlot() +
                                " | Members: " + memberCount +
                                " | Avg Rating: " + String.format("%.1f", avgRating)
                );
            }
        }
    }

    // =========================
    // REPORT 2
    // =========================
    public void generateHighestIncomeReportFirst4Weeks() {

        System.out.println("\n===== INCOME REPORT (WEEKS 1–4) =====");

        Map<String, Double> incomeMap = new HashMap<>();

        for (Lesson lesson : lessons) {

            if (lesson.getWeekNumber() <= 4) {

                String exercise = lesson.getLessonName();
                double income = lesson.getBookedMembers().size() * lesson.getPrice();

                incomeMap.put(exercise,
                        incomeMap.getOrDefault(exercise, 0.0) + income);
            }
        }

        String topExercise = "";
        double maxIncome = 0;

        for (Map.Entry<String, Double> entry : incomeMap.entrySet()) {

            System.out.println(entry.getKey() + " | Total Income: £" + entry.getValue());

            if (entry.getValue() > maxIncome) {
                maxIncome = entry.getValue();
                topExercise = entry.getKey();
            }
        }

        System.out.println("\n🏆 Highest Income Exercise: " +
                topExercise + " (£" + maxIncome + ")");
    }

    // =========================
    // HELPERS
    // =========================
    private Member findMember(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) return m;
        }
        return null;
    }

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