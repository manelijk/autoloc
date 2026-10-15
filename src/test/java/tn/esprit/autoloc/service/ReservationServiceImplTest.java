package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private IReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation(1L, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 7), StatutReservation.CONFIRMEE);
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerReservation() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = reservationService.ajouter(reservation);
        assertEquals(reservation, result);
        verify(reservationRepository).save(reservation);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = reservationService.modifier(1L, reservation);
        assertEquals(1L, result.getIdReservation());
        verify(reservationRepository).save(reservation);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(reservationRepository.findAll()).thenReturn(List.of(reservation));
        List<Reservation> result = reservationService.recupererTous();
        assertEquals(1, result.size());
    }

    @Test
    void recupererParId_devraitRetournerReservationExistante() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        Reservation result = reservationService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdReservation());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(reservationRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(reservationService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        reservationService.supprimer(1L);
        verify(reservationRepository).deleteById(1L);
    }
}
