package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private IClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1L, "Ben Ali", "Sami", "sami@mail.com", "22000000", "B123456", LocalDate.of(2024, 1, 10));
    }

    @Test
    void ajouter_devraitSauvegarderEtRetournerClient() {
        when(clientRepository.save(client)).thenReturn(client);
        Client result = clientService.ajouter(client);
        assertEquals(client, result);
        verify(clientRepository).save(client);
    }

    @Test
    void modifier_devraitAffecterIdEtSauvegarder() {
        when(clientRepository.save(client)).thenReturn(client);
        Client result = clientService.modifier(1L, client);
        assertEquals(1L, result.getIdClient());
        verify(clientRepository).save(client);
    }

    @Test
    void recupererTous_devraitRetournerListe() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        List<Client> result = clientService.recupererTous();
        assertEquals(1, result.size());
        verify(clientRepository).findAll();
    }

    @Test
    void recupererParId_devraitRetournerClientExistant() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        Client result = clientService.recupererParId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdClient());
    }

    @Test
    void recupererParId_devraitRetournerNullSiAbsent() {
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(clientService.recupererParId(99L));
    }

    @Test
    void supprimer_devraitAppelerDeleteById() {
        clientService.supprimer(1L);
        verify(clientRepository).deleteById(1L);
    }
}
