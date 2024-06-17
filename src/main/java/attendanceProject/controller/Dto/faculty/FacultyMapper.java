package attendanceProject.controller.Dto.faculty;

import attendanceProject.domain.Faculty;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyMapper {
    public static Faculty mapToFaculty(FacultyRequest facultyDtoRequest) {
        Faculty faculty = new Faculty();
        faculty.setFirstName(facultyDtoRequest.getFirstName());
        faculty.setLastName(facultyDtoRequest.getLastName());
        faculty.setEmail(facultyDtoRequest.getEmail());
        faculty.setBirthDay(facultyDtoRequest.getBirthDay());
        faculty.setUsername(facultyDtoRequest.getUsername());
        faculty.setPassword(facultyDtoRequest.getPassword());
        faculty.setGender(facultyDtoRequest.getGender());
        faculty.setHobbies(facultyDtoRequest.getHobbies());
        return faculty;
    }
    public static FacultyResponse mapToFacultyResponse(Faculty faculty) {
        FacultyResponse facultyResponse = new FacultyResponse();
        facultyResponse.setFirstName(faculty.getFirstName());
        facultyResponse.setLastName(faculty.getLastName());
        facultyResponse.setEmail(faculty.getEmail());
        facultyResponse.setHobbies(faculty.getHobbies());
        facultyResponse.setFacultyId(faculty.getId());
        return facultyResponse;
    }
    public static List<FacultyResponse> mapToFacultiesResponse(List<Faculty> faculties) {
       return faculties.stream().map(FacultyMapper::mapToFacultyResponse).collect(Collectors.toList());
    }
}
