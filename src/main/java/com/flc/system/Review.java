package com.flc.system;

public class Review {

    private Member member;
    private Lesson lesson;
    private int rating; // 1–5
    private String comment;

    public Review(Member member, Lesson lesson, int rating, String comment) {
        this.member = member;
        this.lesson = lesson;
        this.rating = rating;
        this.comment = comment;
    }

    public Member getMember() {
        return member;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
