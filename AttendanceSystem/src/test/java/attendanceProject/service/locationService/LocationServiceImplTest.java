package attendanceProject.service.locationService;

import attendanceProject.controller.dto.location.CreateLocationParameters;
import attendanceProject.controller.dto.locationType.LocationTypeResponse;
import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationRepository;
import attendanceProject.controller.dto.location.LocationDTO;
import attendanceProject.repository.LocationTypeRepository;
import jakarta.persistence.EntityNotFoundException;
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

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {
    @Mock
    private LocationRepository locationRepository;

    @Mock
    private LocationTypeRepository locationTypeRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    private LocationTypeResponse locationTypeResponse;
    private Location location1;
    private Location location2;
    private CreateLocationParameters parameters;
    private LocationType locationType;

    @BeforeEach
    void setUp(){
        locationType = new LocationType();
        locationType.setId(1);
        location1 = new Location();
        location1.setId(0L);
        location1.setName("Location1");
        location1.setLocationType(locationType);
        location2 = new Location();
        location2.setId(2L);
        location2.setName("Location2");
        location2.setLocationType(locationType);
        locationTypeResponse = new LocationTypeResponse();
        locationTypeResponse.setId(1L);

        parameters = new CreateLocationParameters();
        parameters.setLocationTypeId(1L);
        parameters.setName("Location1");
    }

    @Test
    void findAllLocations() {

        when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2));

        List<LocationDTO> result = locationService.findAllLocations();

        assertEquals(2, result.size());
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void findLocationByIdFound() {
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location1));

        LocationDTO result = locationService.findLocationById(1L);

        assertNotNull(result);
        assertEquals("Location1", result.getName());
        verify(locationRepository, times(1)).findById(1L);
    }

    @Test
    void findLocationByIdNotFound() {

        when(locationRepository.findById(1L)).thenReturn(Optional.empty());

        LocationDTO result = locationService.findLocationById(1L);

        assertNull(result);
        verify(locationRepository, times(1)).findById(1L);
    }

    @Test
    void createLocation() {

        when(locationTypeRepository.findById(1L)).thenReturn(Optional.of(locationType));
        when(locationRepository.save(location1)).thenReturn(location1);

        LocationDTO result = locationService.createLocation(parameters);

        assertNotNull(result);
//        assertEquals("Location1", result.getName());
//        assertEquals(locationType, result.getLocationType());
        verify(locationTypeRepository, times(1)).findById(1L);
        verify(locationRepository, times(1)).save(location1);
    }

    @Test
    public void testCreateLocationWithNonExistentLocationType() {
        when(locationTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            locationService.createLocation(parameters);
        });

        verify(locationTypeRepository, times(1)).findById(1L);
        verify(locationRepository, never()).save(any());
    }

    @Test
    void deleteLocation() {
        doNothing().when(locationRepository).deleteById(1L);

        locationService.deleteLocation(1L);

        verify(locationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateLocation() {

        Location newLocation = new Location();
        newLocation.setName("NewLocation");
        newLocation.setId(1L);
        newLocation.setLocationType(locationType);

        when(locationTypeRepository.findById(1L)).thenReturn(Optional.of(locationType));
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location1));
        when(locationRepository.save(any(Location.class))).thenReturn(newLocation);

        parameters.setName("NewLocation");
        LocationDTO result = locationService.updateLocation(1L, parameters);

        assertNotNull(result);
        assertEquals("NewLocation", result.getName());
        assertEquals(1L, result.getLocationTypeId());
        verify(locationTypeRepository, times(1)).findById(1L);
        verify(locationRepository, times(1)).save(newLocation);
    }

}