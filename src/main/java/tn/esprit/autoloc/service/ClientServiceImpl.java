package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client ajouter(Client objet) {
        return clientRepository.save(objet);
    }

    @Override
    public Client modifier(Long id, Client objet) {
        objet.setIdClient(id);
        return clientRepository.save(objet);
    }

    @Override
    public List<Client> recupererTous() {
        return clientRepository.findAll();
    }

    @Override
    public Client recupererParId(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(Long id) {
        clientRepository.deleteById(id);
    }
}
