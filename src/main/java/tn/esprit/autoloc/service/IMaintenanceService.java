package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouter(Maintenance objet);
    Maintenance modifier(Long id, Maintenance objet);
    List<Maintenance> recupererTous();
    Maintenance recupererParId(Long id);
    void supprimer(Long id);
}
