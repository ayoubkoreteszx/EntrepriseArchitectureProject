package attendanceProject.service.courseRegistration;

import attendanceProject.domain.CourseOffering;
import attendanceProject.domain.CourseRegistration;
import attendanceProject.domain.Student;
import attendanceProject.repository.CourseRegistrationRepository;

import java.util.List;

public interface CourseRegistrationService {
    List<CourseRegistration> getAllCourseRegistrationsByStudentId(long StudentId);
    List<Student> getAllStudentsByCourseOfferingId(long courseOfferingId);
    void registerStudentToCourse(Student student, CourseOffering courseOffering);
    void updateCourseRegistration(long courseRegistrationId, CourseOffering courseOffering);
    void deleteCourseRegistration(long courseRegistrationId);
    void deleteAllCourseRegistrationsByStudentId(long studentId);
     List<Long>getRegisteredStudentsByCourseOffering(Long courseOfferingId);

}
