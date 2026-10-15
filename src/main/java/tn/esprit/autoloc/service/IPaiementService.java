package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement ajouter(Paiement objet);
    Paiement modifier(Long id, Paiement objet);
    List<Paiement> recupererTous();
    Paiement recupererParId(Long id);
    void supprimer(Long id);
}
