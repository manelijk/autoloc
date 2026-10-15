package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;

@Service
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    public ContratServiceImpl(IContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public Contrat ajouter(Contrat objet) {
        return contratRepository.save(objet);
    }

    @Override
    public Contrat modifier(Long id, Contrat objet) {
        objet.setIdContrat(id);
        return contratRepository.save(objet);
    }

    @Override
    public List<Contrat> recupererTous() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat recupererParId(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(Long id) {
        contratRepository.deleteById(id);
    }
}
