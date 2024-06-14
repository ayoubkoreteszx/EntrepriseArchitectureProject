package attendanceProject.service.locationService;

import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationRepository;
import attendanceProject.service.locationTypeService.LocationTypeService;
import jakarta.persistence.EntityNotFoundException;
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
    private LocationTypeService locationTypeService;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    void findAllLocations() {
        Location location1 = new Location();
        location1.setId(1L);
        location1.setName("Location1");

        Location location2 = new Location();
        location2.setId(2L);
        location2.setName("Location2");

        when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2));

        List<Location> result = locationService.findAllLocations();

        assertEquals(2, result.size());
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void findLocationByIdFound() {
        Location location = new Location();
        location.setId(1L);
        location.setName("Location1");

        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));

        Location result = locationService.findLocationById(1L);

        assertNotNull(result);
        assertEquals("Location1", result.getName());
        verify(locationRepository, times(1)).findById(1L);
    }

    @Test
    void findLocationByIdNotFound() {

        when(locationRepository.findById(1L)).thenReturn(Optional.empty());

        Location result = locationService.findLocationById(1L);

        assertNull(result);
        verify(locationRepository, times(1)).findById(1L);
    }

    @Test
    void createLocation() {
        LocationType locationType = new LocationType();
        locationType.setId(1L);
        locationType.setType("Type1");

        Location location = new Location();
        location.setName("Location1");

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(locationType);
        when(locationRepository.save(location)).thenReturn(location);

        Location result = locationService.createLocation(location, 1L);

        assertNotNull(result);
        assertEquals("Location1", result.getName());
        assertEquals(locationType, result.getLocationType());
        verify(locationTypeService, times(1)).findLocationTypeById(1L);
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    public void testCreateLocationWithNonExistentLocationType() {
        Location location = new Location();
        location.setName("Location1");

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            locationService.createLocation(location, 1L);
        });

        verify(locationTypeService, times(1)).findLocationTypeById(1L);
        verify(locationRepository, never()).save(location);
    }

    @Test
    void deleteLocation() {
        doNothing().when(locationRepository).deleteById(1L);

        locationService.deleteLocation(1L);

        verify(locationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateLocation() {
        LocationType locationType = new LocationType();
        locationType.setId(1L);
        locationType.setType("Type1");

        Location oldLocation = new Location();
        oldLocation.setId(1L);
        oldLocation.setName("OldLocation");

        Location newLocation = new Location();
        newLocation.setName("NewLocation");
        newLocation.setId(1L);

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(locationType);
        //when(locationRepository.findById(1L)).thenReturn(Optional.of(oldLocation));
        when(locationRepository.save(any(Location.class))).thenReturn(newLocation);

        Location result = locationService.updateLocation(1L, newLocation, 1L);

        assertNotNull(result);
        assertEquals("NewLocation", result.getName());
        assertEquals(locationType, result.getLocationType());
        assertEquals(oldLocation.getId(), newLocation.getId());
        verify(locationTypeService, times(1)).findLocationTypeById(1L);
        verify(locationRepository, times(1)).save(newLocation);
    }

    @Test
    public void testUpdateLocationWithNonExistentLocationType() {
        Location newLocation = new Location();
        newLocation.setName("NewLocation");

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            locationService.updateLocation(1L, newLocation, 1L);
        });

        verify(locationTypeService, times(1)).findLocationTypeById(1L);
        verify(locationRepository, never()).save(newLocation);
    }
}