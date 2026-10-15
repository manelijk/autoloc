package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat ajouter(Contrat objet);
    Contrat modifier(Long id, Contrat objet);
    List<Contrat> recupererTous();
    Contrat recupererParId(Long id);
    void supprimer(Long id);
}
