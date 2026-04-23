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

    // Add lesson
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    // Show all lessons
    public void showLessons() {
        for (Lesson lesson : lessons) {
            System.out.println(lesson.getDetails());
        }
    }

    // Book lesson
    public boolean bookLesson(String memberId, String lessonName) {
        Member member = findMember(memberId);
        Lesson lesson = findLesson(lessonName);

        if (member != null && lesson != null) {
            if (lesson.bookMember(member)) {
                bookings.add(new Booking(member, lesson));
                return true;
            } else {
                System.out.println("Lesson is full!");
            }
        }
        return false;
    }

    // Cancel booking
    public void cancelBooking(String memberId, String lessonName) {
        Member member = findMember(memberId);
        Lesson lesson = findLesson(lessonName);

        if (member != null && lesson != null) {
            lesson.cancelBooking(member);
            bookings.removeIf(b ->
                    b.getMember().equals(member) &&
                            b.getLesson().equals(lesson)
            );
        }
    }

    private Member findMember(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    private Lesson findLesson(String lessonName) {
        for (Lesson l : lessons) {
            if (l.getLessonName().equalsIgnoreCase(lessonName)) {
                return l;
            }
        }
        return null;
    }
}
