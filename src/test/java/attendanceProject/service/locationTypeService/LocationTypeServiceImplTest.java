package attendanceProject.service.locationTypeService;

import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationTypeRepository;
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

    @Test
    void findAllLocationTypes() {
        LocationType locationType1 = new LocationType();
        locationType1.setId(1L);
        locationType1.setType("Type1");

        LocationType locationType2 = new LocationType();
        locationType2.setId(2L);
        locationType2.setType("Type2");

        when(locationTypeRepository.findAll()).thenReturn(Arrays.asList(locationType1, locationType2));

        List<LocationType> result = locationTypeService.findAllLocationTypes();

        assertEquals(2, result.size());
        verify(locationTypeRepository, times(1)).findAll();
    }

    @Test
    void testFindLocationTypeByIdFound() {
        LocationType locationType1 = new LocationType();
        locationType1.setId(1L);
        locationType1.setType("Type1");
        when(locationTypeRepository.findById(1L)).thenReturn(Optional.of(locationType1));
        LocationType result = locationTypeService.findLocationTypeById(1L);
        assertNotNull(result);
        assertEquals("Type1", result.getType());
        verify(locationTypeRepository, times(1)).findById(1L);
    }

    @Test
    void testFindLocationTypeByIdNotFound() {
        Long id = 1L;
        when(locationTypeRepository.findById(id)).thenReturn(Optional.empty());
        LocationType result = locationTypeService.findLocationTypeById(id);
        assertNull(result);
        verify(locationTypeRepository, times(1)).findById(id);
    }

    @Test
    void createLocationType() {
        LocationType locationType = new LocationType();
        locationType.setType("Type1");

        when(locationTypeRepository.save(locationType)).thenReturn(locationType);

        LocationType result = locationTypeService.createLocationType(locationType);

        assertNotNull(result);
        assertEquals("Type1", result.getType());
        verify(locationTypeRepository, times(1)).save(locationType);
    }

    @Test
    void deleteLocationType() {
        doNothing().when(locationTypeRepository).deleteById(1L);

        locationTypeService.deleteLocationType(1L);

        verify(locationTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateLocationType() {
        LocationType oldLocationType = new LocationType();
        oldLocationType.setId(1L);
        oldLocationType.setType("OldType");

        LocationType newLocationType = new LocationType();
        newLocationType.setType("NewType");

        when(locationTypeRepository.findById(1L)).thenReturn(Optional.of(oldLocationType));
        when(locationTypeRepository.save(any(LocationType.class))).thenReturn(newLocationType);

        LocationType result = locationTypeService.updateLocationType(1L, newLocationType);

        assertNotNull(result);
        assertEquals("NewType", result.getType());
        verify(locationTypeRepository, times(1)).findById(1L);
        verify(locationTypeRepository, times(1)).save(newLocationType);
    }

    @Test
    void updateLocationTypeNoTExisting() {
//        LocationType oldLocationType = new LocationType();
//        oldLocationType.setId(1L);
//        oldLocationType.setType("OldType");

        LocationType newLocationType = new LocationType();
        newLocationType.setType("NewType");

        when(locationTypeRepository.findById(1L)).thenReturn(Optional.empty());

        LocationType result = locationTypeService.updateLocationType(1L, newLocationType);

        assertNotNull(result);
        assertEquals("NewType", result.getType());
        verify(locationTypeRepository, times(1)).findById(1L);
        verify(locationTypeRepository, times(0)).save(newLocationType);
    }
}