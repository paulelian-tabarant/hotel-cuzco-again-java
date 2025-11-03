package domain.usecase;

import domain.entity.Hotel;
import domain.repository.HotelRepository;

public class ModifierPrixRezDeChaussee {

    public interface Presenter {
        void prixModifie(double nouveauPrix);

        void prixInvalide();
    }

    private final HotelRepository hotelRepository;

    public ModifierPrixRezDeChaussee(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void executer(double nouveauPrix, Presenter presenter) {
        Hotel hotel = hotelRepository.recupererHotel();

        try {
            hotel.modifierPrixChambresRezDeChaussee(nouveauPrix);
        } catch (IllegalArgumentException e) {
            presenter.prixInvalide();
            return;
        }

        hotelRepository.enregistrerHotel(hotel);

        presenter.prixModifie(nouveauPrix);
    }
}
