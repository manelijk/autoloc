package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    public ReservationServiceImpl(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation ajouter(Reservation objet) {
        return reservationRepository.save(objet);
    }

    @Override
    public Reservation modifier(Long id, Reservation objet) {
        objet.setIdReservation(id);
        return reservationRepository.save(objet);
    }

    @Override
    public List<Reservation> recupererTous() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation recupererParId(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(Long id) {
        reservationRepository.deleteById(id);
    }
}
