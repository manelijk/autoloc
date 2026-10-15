package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceImplTest {

    @Mock
    private IMaintenanceRepository maintenanceRepository;

    @InjectMocks
    private MaintenanceServiceImpl maintenanceService;

    private Maintenance maintenance;

    @BeforeEach
    void setUp() {
        maintenance = new Maintenance(1L, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5), "Vidange moteur");
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerMaintenance() {
        when(maintenanceRepository.save(maintenance)).thenReturn(maintenance);
        Maintenance result = maintenanceService.ajouter(maintenance);
        assertEquals(maintenance, result);
        verify(maintenanceRepository).save(maintenance);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(maintenanceRepository.save(maintenance)).thenReturn(maintenance);
        Maintenance result = maintenanceService.modifier(1L, maintenance);
        assertEquals(1L, result.getIdMaintenance());
        verify(maintenanceRepository).save(maintenance);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(maintenanceRepository.findAll()).thenReturn(List.of(maintenance));
        List<Maintenance> result = maintenanceService.recupererTous();
        assertEquals(1, result.size());
    }

    @Test
    void recupererParId_devraitRetournerMaintenanceExistante() {
        when(maintenanceRepository.findById(1L)).thenReturn(Optional.of(maintenance));
        Maintenance result = maintenanceService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdMaintenance());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(maintenanceRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(maintenanceService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        maintenanceService.supprimer(1L);
        verify(maintenanceRepository).deleteById(1L);
    }
}
