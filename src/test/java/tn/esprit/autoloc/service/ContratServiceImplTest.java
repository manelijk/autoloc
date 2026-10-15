package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratServiceImplTest {

    @Mock
    private IContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;

    @BeforeEach
    void setUp() {
        contrat = new Contrat(1L, LocalDate.of(2024, 3, 15), new BigDecimal("350.00"), true);
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerContrat() {
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat result = contratService.ajouter(contrat);
        assertEquals(contrat, result);
        verify(contratRepository).save(contrat);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat result = contratService.modifier(1L, contrat);
        assertEquals(1L, result.getIdContrat());
        verify(contratRepository).save(contrat);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(contratRepository.findAll()).thenReturn(List.of(contrat));
        List<Contrat> result = contratService.recupererTous();
        assertEquals(1, result.size());
    }

    @Test
    void recupererParId_devraitRetournerContratExistant() {
        when(contratRepository.findById(1L)).thenReturn(Optional.of(contrat));
        Contrat result = contratService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdContrat());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(contratRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(contratService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        contratService.supprimer(1L);
        verify(contratRepository).deleteById(1L);
    }
}
