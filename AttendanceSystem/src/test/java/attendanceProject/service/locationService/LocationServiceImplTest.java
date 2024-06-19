package attendanceProject.service.locationService;

import attendanceProject.controller.dto.location.CreateLocationParameters;
import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationRepository;
import attendanceProject.controller.dto.location.LocationDTO;
import attendanceProject.controller.dto.location.LocationDTOMapper;
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
        LocationType locationType = new LocationType();
        locationType.setId(1);
        Location location1 = new Location();
        location1.setId(1L);
        location1.setName("Location1");
        location1.setLocationType(locationType);

        Location location2 = new Location();
        location2.setId(2L);
        location2.setName("Location2");
        location2.setLocationType(locationType);

        when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2));

        List<LocationDTO> result = locationService.findAllLocations();

        assertEquals(2, result.size());
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void findLocationByIdFound() {
        LocationType locationType = new LocationType();
        locationType.setId(1);
        Location location = new Location();
        location.setId(1L);
        location.setName("Location1");
        location.setLocationType(locationType);

        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));

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
        LocationType locationType = new LocationType();
        locationType.setId(1L);
        locationType.setType("Type1");

        Location location = new Location();
        location.setName("Location1");
        location.setLocationType(locationType);
        CreateLocationParameters parameters = new CreateLocationParameters();
        parameters.setLocationTypeId(1L);


        when(locationTypeService.findLocationTypeById(1L)).thenReturn(locationType);
        when(locationRepository.save(location)).thenReturn(location);

        LocationDTO result = locationService.createLocation(parameters);

        assertNotNull(result);
//        assertEquals("Location1", result.getName());
//        assertEquals(locationType, result.getLocationType());
        verify(locationTypeService, times(1)).findLocationTypeById(1L);
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    public void testCreateLocationWithNonExistentLocationType() {
        LocationType locationType = new LocationType();
        locationType.setId(1L);
        Location location = new Location();
        location.setName("Location1");
        location.setLocationType(locationType);

        CreateLocationParameters parameters = new CreateLocationParameters();
        parameters.setLocationTypeId(1L);

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            locationService.createLocation(parameters);
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
        oldLocation.setLocationType(locationType);

        Location newLocation = new Location();
        newLocation.setName("NewLocation");
        newLocation.setId(1L);
        newLocation.setLocationType(locationType);
        LocationDTO locationDTO = LocationDTOMapper.mapToDTO(newLocation);

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(locationType);
        when(locationRepository.findById(1L)).thenReturn(Optional.of(oldLocation));
        when(locationRepository.save(any(Location.class))).thenReturn(newLocation);
        CreateLocationParameters parameters = new CreateLocationParameters();
        parameters.setLocationTypeId(1L);
        parameters.setName("NewLocation");
        LocationDTO result = locationService.updateLocation(1L, parameters);

        assertNotNull(result);
        assertEquals("NewLocation", result.getName());
        assertEquals(1L, result.getLocationTypeId());
        assertEquals(oldLocation.getId(), newLocation.getId());
        verify(locationTypeService, times(1)).findLocationTypeById(1L);
        verify(locationRepository, times(1)).save(newLocation);
    }

}