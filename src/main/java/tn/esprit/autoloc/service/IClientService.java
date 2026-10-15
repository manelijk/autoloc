package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {
    Client ajouter(Client objet);
    Client modifier(Long id, Client objet);
    List<Client> recupererTous();
    Client recupererParId(Long id);
    void supprimer(Long id);
}
