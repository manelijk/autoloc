package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.RoleEmploye;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeServiceImplTest {

    @Mock
    private IEmployeRepository employeRepository;

    @InjectMocks
    private EmployeServiceImpl employeService;

    private Employe employe;

    @BeforeEach
    void setUp() {
        employe = new Employe(1L, "Trabelsi", "Rim", RoleEmploye.AGENT);
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerEmploye() {
        when(employeRepository.save(employe)).thenReturn(employe);
        Employe result = employeService.ajouter(employe);
        assertEquals(employe, result);
        verify(employeRepository).save(employe);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(employeRepository.save(employe)).thenReturn(employe);
        Employe result = employeService.modifier(1L, employe);
        assertEquals(1L, result.getIdEmploye());
        verify(employeRepository).save(employe);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(employeRepository.findAll()).thenReturn(List.of(employe));
        List<Employe> result = employeService.recupererTous();
        assertEquals(1, result.size());
    }

    @Test
    void recupererParId_devraitRetournerEmployeExistant() {
        when(employeRepository.findById(1L)).thenReturn(Optional.of(employe));
        Employe result = employeService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdEmploye());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(employeRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(employeService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        employeService.supprimer(1L);
        verify(employeRepository).deleteById(1L);
    }
}
