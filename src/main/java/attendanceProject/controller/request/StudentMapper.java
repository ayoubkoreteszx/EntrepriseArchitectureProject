package attendanceProject.controller.request;

import attendanceProject.domain.Student;

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
}
