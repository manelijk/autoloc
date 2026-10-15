package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaiementServiceImplTest {

    @Mock
    private IPaiementRepository paiementRepository;

    @InjectMocks
    private PaiementServiceImpl paiementService;

    private Paiement paiement;

    @BeforeEach
    void setUp() {
        paiement = new Paiement(1L, new BigDecimal("200.00"), LocalDate.of(2024, 4, 20), ModePaiement.CARTE);
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerPaiement() {
        when(paiementRepository.save(paiement)).thenReturn(paiement);
        Paiement result = paiementService.ajouter(paiement);
        assertEquals(paiement, result);
        verify(paiementRepository).save(paiement);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(paiementRepository.save(paiement)).thenReturn(paiement);
        Paiement result = paiementService.modifier(1L, paiement);
        assertEquals(1L, result.getIdPaiement());
        verify(paiementRepository).save(paiement);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(paiementRepository.findAll()).thenReturn(List.of(paiement));
        List<Paiement> result = paiementService.recupererTous();
        assertEquals(1, result.size());
    }

    @Test
    void recupererParId_devraitRetournerPaiementExistant() {
        when(paiementRepository.findById(1L)).thenReturn(Optional.of(paiement));
        Paiement result = paiementService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdPaiement());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(paiementRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(paiementService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        paiementService.supprimer(1L);
        verify(paiementRepository).deleteById(1L);
    }
}
