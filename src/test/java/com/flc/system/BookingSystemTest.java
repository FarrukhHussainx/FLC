package com.flc.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookingSystemTest {

    private BookingSystem system;

    @BeforeEach
    void setUp() {
        system = new BookingSystem();

        // Add members
        system.addMember(new Member("M1", "Ali"));
        system.addMember(new Member("M2", "Sara"));
        system.addMember(new Member("M3", "John"));
        system.addMember(new Member("M4", "Emma"));
        system.addMember(new Member("M5", "David"));

        system.generate8WeekSchedule();
    }

    // =========================
    // BOOKING TEST
    // =========================
    @Test
    void testBookingSuccess() {
        boolean result = system.bookLesson(
                "M1", "Yoga", "Saturday", TimeSlot.MORNING, 1
        );

        assertTrue(result);
    }

    // =========================
    // CAPACITY TEST
    // =========================
    @Test
    void testBookingCapacityLimit() {

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M2", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M3", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M4", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        // 5th booking should fail
        boolean result = system.bookLesson(
                "M5", "Yoga", "Saturday", TimeSlot.MORNING, 1
        );

        assertFalse(result);
    }

    // =========================
    // CANCEL TEST
    // =========================
    @Test
    void testCancelBooking() {

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        system.cancelBooking(
                "M1", "Yoga", "Saturday", TimeSlot.MORNING, 1
        );

        // Try re-book → should succeed if cancel worked
        boolean result = system.bookLesson(
                "M1", "Yoga", "Saturday", TimeSlot.MORNING, 1
        );

        assertTrue(result);
    }

    // =========================
    // CHANGE BOOKING TEST
    // =========================
    @Test
    void testChangeBooking() {

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        boolean changed = system.changeBooking(
                "M1",
                "Yoga", "Saturday", TimeSlot.MORNING, 1,
                "Zumba", "Sunday", TimeSlot.AFTERNOON, 1
        );

        assertTrue(changed);
    }

    // =========================
    // REVIEW TEST
    // =========================
    @Test
    void testAddReview() {

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        boolean result = system.addReview(
                "M1",
                "Yoga",
                "Saturday",
                TimeSlot.MORNING,
                1,
                5,
                "Excellent"
        );

        assertTrue(result);
    }

    // =========================
    // INVALID REVIEW TEST
    // =========================
    @Test
    void testReviewWithoutBookingFails() {

        boolean result = system.addReview(
                "M1",
                "Yoga",
                "Saturday",
                TimeSlot.MORNING,
                1,
                5,
                "Should fail"
        );

        assertFalse(result);
    }

    // =========================
    // RATING RANGE TEST
    // =========================
    @Test
    void testInvalidRatingFails() {

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);

        boolean result = system.addReview(
                "M1",
                "Yoga",
                "Saturday",
                TimeSlot.MORNING,
                1,
                6, // invalid
                "Bad input"
        );

        assertFalse(result);
    }

    // =========================
    // REPORT TEST (BASIC)
    // =========================
    @Test
    void testReportsRunWithoutError() {

        system.bookLesson("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1);
        system.bookLesson("M2", "Zumba", "Sunday", TimeSlot.AFTERNOON, 2);

        system.addReview("M1", "Yoga", "Saturday", TimeSlot.MORNING, 1, 5, "Great");

        assertDoesNotThrow(() -> {
            system.generateLessonReportFirst4Weeks();
            system.generateHighestIncomeReportFirst4Weeks();
        });
    }
}