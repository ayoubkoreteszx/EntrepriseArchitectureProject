package attendanceProject.service.courseRegistration;

import attendanceProject.domain.CourseOffering;
import attendanceProject.domain.CourseRegistration;
import attendanceProject.domain.Student;
import attendanceProject.repository.CourseRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseReigstrationServiceImplim implements CourseRegistrationService{
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public List<CourseRegistration> getAllCourseRegistrationsByStudentId(long StudentId) {
        return courseRegistrationRepository.findAllByStudentId(StudentId);
    }

    @Override
    public List<Student> getAllStudentsByCourseOfferingId(long courseOfferingId) {
        return courseRegistrationRepository.findAllByCourseOfferingId(courseOfferingId);
    }

    @Override
    public void registerStudentToCourse(Student studentId, CourseOffering courseOfferingId) {
        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setStudent(studentId);
        courseRegistration.setCourseOffering(courseOfferingId);
             courseRegistrationRepository.save(courseRegistration);
    }

    @Override
    public void updateCourseRegistration(long courseRegistrationId, CourseOffering courseOffering) {
        courseRegistrationRepository.updateCourseRegistration(courseRegistrationId, courseOffering.getId());
    }

    @Override
    public void deleteCourseRegistration(long courseRegistrationId) {
        courseRegistrationRepository.deleteById(courseRegistrationId);

    }

    @Override
    public void deleteAllCourseRegistrationsByStudentId(long studentId) {
        courseRegistrationRepository.deleteAllByStudentId(studentId);
    }
    @Override
    public List<Long> getRegisteredStudentsByCourseOffering(Long courseOfferingId) {
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findByCourseOfferingId(courseOfferingId);
        List<Long> studentIds = new ArrayList<>();
        for(CourseRegistration courseRegistration : courseRegistrations)
            studentIds.add(courseRegistration.getStudent().getId());
        return studentIds;
    }

}
