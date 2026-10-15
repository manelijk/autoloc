package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence ajouter(Agence objet);
    Agence modifier(Long id, Agence objet);
    List<Agence> recupererTous();
    Agence recupererParId(Long id);
    void supprimer(Long id);
}
