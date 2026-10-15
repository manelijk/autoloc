package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    public MaintenanceServiceImpl(IMaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance ajouter(Maintenance objet) {
        return maintenanceRepository.save(objet);
    }

    @Override
    public Maintenance modifier(Long id, Maintenance objet) {
        objet.setIdMaintenance(id);
        return maintenanceRepository.save(objet);
    }

    @Override
    public List<Maintenance> recupererTous() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance recupererParId(Long id) {
        return maintenanceRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(Long id) {
        maintenanceRepository.deleteById(id);
    }
}
