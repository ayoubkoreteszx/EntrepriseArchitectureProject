package attendanceProject.service.courseOfferingService;

import attendanceProject.domain.CourseOffering;
import attendanceProject.repository.CourseOfferingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseOfferingServiceTest {

    @Mock
    private CourseOfferingRepository courseOfferingRepository;

    @InjectMocks
    private CourseOfferingServiceImpl courseOfferingService;

    @Test
    void findAllCourseOfferings() {
        CourseOffering courseOffering1 = new CourseOffering();
        CourseOffering courseOffering2 = new CourseOffering();
        when(courseOfferingRepository.findAll()).thenReturn(List.of(courseOffering1, courseOffering2));
        List<CourseOffering> result = courseOfferingService.findAllCourseOfferings();
        assertEquals(2, result.size());
        verify(courseOfferingRepository, times(1)).findAll();
    }

    @Test
    void testGetCourseOfferingByIdFound() {
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setId(1L);
        courseOffering.setCapacity(100);

        when(courseOfferingRepository.findById(1L)).thenReturn(Optional.of(courseOffering));
        CourseOffering result = courseOfferingService.getCourseOfferingById(1L);
        assertNotNull(result);
        assertEquals(100, result.getCapacity());
        verify(courseOfferingRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCourseOfferingByIdNotFound() {
        when(courseOfferingRepository.findById(1L)).thenReturn(Optional.empty());
        CourseOffering result = courseOfferingService.getCourseOfferingById(1L);
        assertNull(result);
        verify(courseOfferingRepository, times(1)).findById(1L);
    }

    @Test
    void createCourseOffering() {
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setCapacity(100);

        when(courseOfferingRepository.save(courseOffering)).thenReturn(courseOffering);

        CourseOffering result = courseOfferingService.createCourseOffering(courseOffering);
        assertNotNull(result);
        assertEquals(100, result.getCapacity());
        verify(courseOfferingRepository, times(1)).save(courseOffering);
    }

    @Test
    void updateCourseOffering() {
        CourseOffering oldCourseOffering = new CourseOffering();
        oldCourseOffering.setId(1L);
        oldCourseOffering.setCapacity(100);

        CourseOffering newCourseOffering = oldCourseOffering;
        newCourseOffering.setCapacity(200);

        when(courseOfferingRepository.save(oldCourseOffering)).thenReturn(oldCourseOffering);
        when(courseOfferingRepository.findById(1L)).thenReturn(Optional.of(oldCourseOffering));

        CourseOffering courseOffering = courseOfferingService.updateCourseOffering(1L, newCourseOffering);
        assertNotNull(courseOffering);
        assertEquals(200, courseOffering.getCapacity());
        verify(courseOfferingRepository, times(1)).findById(1L);
        verify(courseOfferingRepository, times(1)).save(courseOffering);
    }

    @Test
    void updateCourseOfferingNotExisting() {
        // suppose there is no id with 1L
        long id = 1L;
        CourseOffering courseOfferingToUpdate = new CourseOffering();
        courseOfferingToUpdate.setCapacity(100);
        when(courseOfferingRepository.findById(id)).thenReturn(Optional.empty());
        CourseOffering courseOffering = courseOfferingService.updateCourseOffering(id, courseOfferingToUpdate);
        assertEquals(courseOffering.getId(), 0);
    }
}
