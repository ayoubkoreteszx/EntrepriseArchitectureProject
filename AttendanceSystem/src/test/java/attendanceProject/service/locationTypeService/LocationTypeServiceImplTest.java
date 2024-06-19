package attendanceProject.service.locationTypeService;

import attendanceProject.controller.dto.locationType.LocationTypeRequest;
import attendanceProject.controller.dto.locationType.LocationTypeResponse;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationTypeServiceImplTest {

    @Mock
    private LocationTypeRepository locationTypeRepository;

    @InjectMocks
    private LocationTypeServiceImpl locationTypeService;

    private LocationTypeRequest request1;
    private LocationTypeRequest request2;
    private LocationType locationType1;
    private LocationType locationType2;

    @BeforeEach
    void setUp(){
        request1 = new LocationTypeRequest();
        request1.setType("Type1");
        request2 = new LocationTypeRequest();
        request2.setType("Updated Type");
        locationType1 = new LocationType();
        locationType1.setId(0L);
        locationType1.setType("Type1");
        locationType2 = new LocationType();
        locationType2.setId(2L);
        locationType2.setType("Type2");
    }

    @Test
    void findAllLocationTypes() {
        when(locationTypeRepository.findAll()).thenReturn(Arrays.asList(locationType1, locationType2));

        List<LocationTypeResponse> result = locationTypeService.findAllLocationTypes();

        assertEquals(2, result.size());
        verify(locationTypeRepository, times(1)).findAll();
    }

    @Test
    void testFindLocationTypeByIdFound() {
        when(locationTypeRepository.findById(1L)).thenReturn(Optional.of(locationType1));
        LocationTypeResponse result = locationTypeService.findLocationTypeById(1L);
        assertNotNull(result);
        assertEquals("Type1", result.getType());
        verify(locationTypeRepository, times(1)).findById(1L);
    }

    @Test
    void testFindLocationTypeByIdNotFound() {
        Long id = 1L;
        when(locationTypeRepository.findById(id)).thenReturn(Optional.empty());
        LocationTypeResponse result = locationTypeService.findLocationTypeById(id);
        assertNull(result);
        verify(locationTypeRepository, times(1)).findById(id);
    }

    @Test
    void createLocationType() {
        when(locationTypeRepository.save(locationType1)).thenReturn(locationType1);

        LocationTypeResponse result = locationTypeService.createLocationType(request1);

        assertNotNull(result);
        assertEquals("Type1", result.getType());
        verify(locationTypeRepository, times(1)).save(locationType1);
    }

    @Test
    void deleteLocationType() {
        doNothing().when(locationTypeRepository).deleteById(1L);

        locationTypeService.deleteLocationType(1L);

        verify(locationTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateLocationType() {
        when(locationTypeRepository.findById(1L)).thenReturn(Optional.of(locationType1));
        when(locationTypeRepository.save(any(LocationType.class))).thenReturn(locationType1);

        LocationTypeResponse result = locationTypeService.updateLocationType(1L, request1);

        assertNotNull(result);
        assertEquals("Type1", result.getType());
        verify(locationTypeRepository, times(1)).findById(1L);
        verify(locationTypeRepository, times(1)).save(locationType1);
    }

    @Test
    void updateLocationTypeNoTExisting() {

        when(locationTypeRepository.findById(1L)).thenReturn(Optional.empty());

        LocationTypeResponse result = locationTypeService.updateLocationType(1L, request2);

        assertNull(result);
        verify(locationTypeRepository, times(1)).findById(1L);
        verify(locationTypeRepository, never()).save(any());
    }
}