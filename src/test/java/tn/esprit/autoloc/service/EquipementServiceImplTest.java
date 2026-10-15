package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipementServiceImplTest {

    @Mock
    private IEquipementRepository equipementRepository;

    @InjectMocks
    private EquipementServiceImpl equipementService;

    private Equipement equipement;

    @BeforeEach
    void setUp() {
        equipement = new Equipement(1L, "GPS");
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerEquipement() {
        when(equipementRepository.save(equipement)).thenReturn(equipement);
        Equipement result = equipementService.ajouter(equipement);
        assertEquals(equipement, result);
        verify(equipementRepository).save(equipement);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(equipementRepository.save(equipement)).thenReturn(equipement);
        Equipement result = equipementService.modifier(1L, equipement);
        assertEquals(1L, result.getIdEquipement());
        verify(equipementRepository).save(equipement);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(equipementRepository.findAll()).thenReturn(List.of(equipement));
        List<Equipement> result = equipementService.recupererTous();
        assertEquals(1, result.size());
    }

    @Test
    void recupererParId_devraitRetournerEquipementExistant() {
        when(equipementRepository.findById(1L)).thenReturn(Optional.of(equipement));
        Equipement result = equipementService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdEquipement());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(equipementRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(equipementService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        equipementService.supprimer(1L);
        verify(equipementRepository).deleteById(1L);
    }
}
