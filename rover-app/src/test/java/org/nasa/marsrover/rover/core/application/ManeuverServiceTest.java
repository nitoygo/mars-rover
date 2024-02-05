package org.nasa.marsrover.rover.core.application;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nasa.marsrover.rover.core.ports.in.types.ManeuverCommand;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManeuverServiceTest {

    @Mock
    private Rover rover;

    @InjectMocks
    private ManeuverService maneuverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void maneuverShouldMoveRoverForward() {
        maneuverService.maneuver(new ManeuverCommand("M"));
        verify(rover, times(1)).moveForward();
    }

    @Test
    void maneuverShouldFaceRoverLeft() {
        maneuverService.maneuver(new ManeuverCommand("L"));
        verify(rover, times(1)).faceLeft();
    }

    @Test
    void maneuverShouldFaceRoverRight() {
        maneuverService.maneuver(new ManeuverCommand("R"));
        verify(rover, times(1)).faceRight();
    }

    @Test
    void maneuverShouldHandleEmptyInstruction() {
        maneuverService.maneuver(new ManeuverCommand(""));
        // No interactions with rover should occur for empty data
        verifyNoInteractions(rover);
    }

    @Test
    void maneuverShouldHandleMultipleMoves() {
        maneuverService.maneuver(new ManeuverCommand("MLRM"));
        // Verify the expected sequence of interactions
        inOrder(rover).verify(rover).moveForward();
        inOrder(rover).verify(rover).faceLeft();
        inOrder(rover).verify(rover).faceRight();
        inOrder(rover).verify(rover).moveForward();
    }

    @Test
    void maneuverShouldRejectNullInstruction() {
        assertThrows(ValidationException.class, () -> maneuverService.maneuver(new ManeuverCommand(null)));
        verifyNoInteractions(rover);
    }

    @Test
    void maneuverShouldRejectInvalidMoves() {
        assertThrows(ValidationException.class, () ->
                maneuverService.maneuver(
                        new ManeuverCommand("XYZ")));

        // No interactions with rover should occur for invalid moves
        verifyNoInteractions(rover);
    }

}
