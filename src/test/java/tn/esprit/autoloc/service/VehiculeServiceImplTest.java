package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculeServiceImplTest {

    @Mock
    private IVehiculeRepository vehiculeRepository;

    @InjectMocks
    private VehiculeServiceImpl vehiculeService;

    private Vehicule vehicule;

    @BeforeEach
    void setUp() {
        vehicule = new Vehicule(1L, "TU123AB", "Renault", "Clio",
                CategorieVehicule.CITADINE, new BigDecimal("75.00"), StatutVehicule.DISPONIBLE);
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerVehicule() {
        when(vehiculeRepository.save(vehicule)).thenReturn(vehicule);
        Vehicule result = vehiculeService.ajouter(vehicule);
        assertEquals(vehicule, result);
        verify(vehiculeRepository).save(vehicule);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(vehiculeRepository.save(vehicule)).thenReturn(vehicule);
        Vehicule result = vehiculeService.modifier(1L, vehicule);
        assertEquals(1L, result.getIdVehicule());
        verify(vehiculeRepository).save(vehicule);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(vehiculeRepository.findAll()).thenReturn(List.of(vehicule));
        List<Vehicule> result = vehiculeService.recupererTous();
        assertEquals(1, result.size());
    }

    @Test
    void recupererParId_devraitRetournerVehiculeExistant() {
        when(vehiculeRepository.findById(1L)).thenReturn(Optional.of(vehicule));
        Vehicule result = vehiculeService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdVehicule());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(vehiculeRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(vehiculeService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        vehiculeService.supprimer(1L);
        verify(vehiculeRepository).deleteById(1L);
    }
}
