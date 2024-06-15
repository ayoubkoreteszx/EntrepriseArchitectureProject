package attendanceProject.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class CourseOfferingTest {
    private final CourseOffering courseOffering = new CourseOffering();

    @Test
    public void testGenerateSessions_WeekdaysAndWeekends() {
        LocalDate startDate = LocalDate.of(2024, 6, 1); // Saturday
        LocalDate endDate = LocalDate.of(2024, 6, 10); // Monday
        courseOffering.setStartDate(startDate);
        courseOffering.setEndDate(endDate);

        List<Session> sessions = courseOffering.generateSessions();

        assertThat(sessions).hasSize(15); // Expects 15 sessions (excluding 2 Sundays)

        // Check first session date, end and start times
        assertThat(sessions.getFirst().getStartTime()).isEqualTo(LocalTime.of( 10, 0));
        assertThat(sessions.getFirst().getEndTime()).isEqualTo(LocalTime.of( 12, 30));
        assertThat(sessions.getFirst().getDate()).isEqualTo(LocalDate.of(2024, 6, 1));

        // Check second session on Saturday
        assertThat(sessions.get(1).getStartTime()).isEqualTo(LocalTime.of(13, 30));
        assertThat(sessions.get(1).getEndTime()).isEqualTo(LocalTime.of(15, 30));
        assertThat(sessions.get(1).getDate()).isEqualTo(LocalDate.of(2024, 6, 1));

        // Check morning session on Monday (last day)
        assertThat(sessions.getLast().getStartTime()).isEqualTo(LocalTime.of(10, 0));
        assertThat(sessions.getLast().getEndTime()).isEqualTo(LocalTime.of(12, 30));
        assertThat(sessions.getLast().getDate()).isEqualTo(LocalDate.of(2024, 6, 10));
    }

    @Test
    public void testGenerateSessions_SingleDay() {
        LocalDate startDate = LocalDate.of(2024, 6, 3);
        LocalDate endDate = LocalDate.of(2024, 6, 3);
        courseOffering.setStartDate(startDate);
        courseOffering.setEndDate(endDate);

        List<Session> sessions = courseOffering.generateSessions();

        // check if only one session is created
        assertThat(sessions).hasSize(1);

        // check if created session is morning session
        assertThat(sessions.getFirst().getStartTime()).isEqualTo(LocalTime.of( 10, 0));
        assertThat(sessions.getFirst().getEndTime()).isEqualTo(LocalTime.of( 12, 30));
        assertThat(sessions.getLast().getDate()).isEqualTo(LocalDate.of(2024, 6, 3));
    }

    @Test
    public void testGenerateSessions_SingleSunday() {
        LocalDate startDate = LocalDate.of(2024, 6, 2); // Sunday
        LocalDate endDate = LocalDate.of(2024, 6, 2); // Sunday
        courseOffering.setStartDate(startDate);
        courseOffering.setEndDate(endDate);

        List<Session> sessions = courseOffering.generateSessions();

        assertThat(sessions).isEmpty(); // Expects no sessions (all Sundays)
    }
}