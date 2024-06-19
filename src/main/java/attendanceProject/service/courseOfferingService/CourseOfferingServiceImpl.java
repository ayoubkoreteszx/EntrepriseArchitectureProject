package attendanceProject.service.courseOfferingService;

import attendanceProject.controller.Dto.session.SessionResponse;
import attendanceProject.domain.CourseOffering;
import attendanceProject.domain.Session;
import attendanceProject.repository.CourseOfferingRepository;
import attendanceProject.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {

    private final CourseOfferingRepository courseOfferingRepository;
    private final SessionRepository sessionRepository;

    public CourseOfferingServiceImpl(CourseOfferingRepository courseOfferingRepository,
                                     SessionRepository sessionRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public CourseOffering getCourseOfferingById(Long id) {
        return courseOfferingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCourseOfferingById(Long id) {
        courseOfferingRepository.deleteById(id);
    }

    @Override
    public CourseOffering createCourseOffering(CourseOffering courseOffering) {
        CourseOffering result = courseOfferingRepository.save(courseOffering);
        saveSessions(result.generateSessions());
        return result;
    }

    private void saveSessions(List<Session> sessions) {
        sessionRepository.saveAll(sessions);
    }

    @Override
    public CourseOffering updateCourseOffering(Long id, CourseOffering courseOffering) {
        CourseOffering oldCourseOffering = getCourseOfferingById(id);
        if (Objects.nonNull(oldCourseOffering)) {
            courseOffering.setId(id);
            courseOfferingRepository.save(courseOffering);
        }
        return courseOffering;
    }

    @Override
    public List<CourseOffering> findAllCourseOfferings() {
        return courseOfferingRepository.findAll();
    }

    @Override
    public List<SessionResponse> findAllSessionsOfCourseOffering(Long courseOfferingId) {
        return SessionResponse.mapToSessionResponse(
                sessionRepository.findByCourseOffering_Id(courseOfferingId)
        );
    }

    @Override
    public List<SessionResponse> findAllPassedSessions(long courseOfferingId) {
        System.out.println(courseOfferingId);
        return SessionResponse.mapToSessionResponse(
                sessionRepository.findCompletedSessionByCourseOffering(courseOfferingId,
                        LocalDate.now())
        );
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingsByDate(LocalDate date) {
        return courseOfferingRepository.findAllCourseOfferingsByDate(date);
    }
}
