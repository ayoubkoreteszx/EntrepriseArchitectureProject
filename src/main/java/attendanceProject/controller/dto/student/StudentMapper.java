package attendanceProject.controller.dto.student;

import attendanceProject.controller.dto.faculty.FacultyMapper;
import attendanceProject.domain.Student;

import java.util.List;
import java.util.stream.Collectors;

public  class StudentMapper {
    public static Student mapToStudent(StudentRequest studentDtoRequest) {
        Student student = new Student();
        student.setStudentId(studentDtoRequest.getStudentId());
        student.setFirstName(studentDtoRequest.getFirstName());
        student.setLastName(studentDtoRequest.getLastName());
        student.setGender(studentDtoRequest.getGender());
        student.setAlternativeId(studentDtoRequest.getAlternativeId());
        student.setApplicationId(studentDtoRequest.getApplicationId());
        student.setAudit(studentDtoRequest.getAudit());
        student.setBirthDay(studentDtoRequest.getBirthDay());
        student.setEmail(studentDtoRequest.getEmail());
        student.setEntry(studentDtoRequest.getEntry());
        student.setUsername(studentDtoRequest.getUsername());
        student.setPassword(studentDtoRequest.getPassword());
        return student;
    }
    public static StudentResponse mapToStudentResponse(Student student) {

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setGender(student.getGender());
        studentResponse.setAlternativeId(student.getAlternativeId());
        studentResponse.setApplicationId(student.getApplicationId());
        studentResponse.setBirthDay(student.getBirthDay());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setEntry(student.getEntry());
        studentResponse.setUsername(student.getUsername());
        studentResponse.setAdvisor(FacultyMapper.mapToFacultyResponse(student.getAdvisor()));
        return studentResponse;
    }
    public static List<StudentResponse> mapToStudentResponseList(List<Student> students) {
        return students.stream().map(StudentMapper::mapToStudentResponse).collect(Collectors.toList());
    }
}
