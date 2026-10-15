package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgenceServiceImplTest {

    @Mock
    private IAgenceRepository agenceRepository;

    @InjectMocks
    private AgenceServiceImpl agenceService;

    private Agence agence;

    @BeforeEach
    void setUp() {
        agence = new Agence(1L, "Agence Tunis", "Tunis", "Av. Habib Bourguiba", "71000000");
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerAgence() {
        when(agenceRepository.save(agence)).thenReturn(agence);
        Agence result = agenceService.ajouter(agence);
        assertEquals(agence, result);
        verify(agenceRepository).save(agence);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(agenceRepository.save(agence)).thenReturn(agence);
        Agence result = agenceService.modifier(1L, agence);
        assertEquals(1L, result.getIdAgence());
        verify(agenceRepository).save(agence);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(agenceRepository.findAll()).thenReturn(List.of(agence));
        List<Agence> result = agenceService.recupererTous();
        assertEquals(1, result.size());
        verify(agenceRepository).findAll();
    }

    @Test
    void recupererParId_devraitRetournerAgenceExistante() {
        when(agenceRepository.findById(1L)).thenReturn(Optional.of(agence));
        Agence result = agenceService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdAgence());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(agenceRepository.findById(99L)).thenReturn(Optional.empty());
        Agence result = agenceService.recupererParId(99L);
        assertNull(result);
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        agenceService.supprimer(1L);
        verify(agenceRepository).deleteById(1L);
    }
}
