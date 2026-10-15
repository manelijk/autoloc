package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {
    Employe ajouter(Employe objet);
    Employe modifier(Long id, Employe objet);
    List<Employe> recupererTous();
    Employe recupererParId(Long id);
    void supprimer(Long id);
}
