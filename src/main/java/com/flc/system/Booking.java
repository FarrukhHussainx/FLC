package com.flc.system;

public class Booking {
    private Member member;
    private Lesson lesson;

    public Booking(Member member, Lesson lesson) {
        this.member = member;
        this.lesson = lesson;
    }

    public Member getMember() {
        return member;
    }

    public Lesson getLesson() {
        return lesson;
    }
}