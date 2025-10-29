package domain.usecase;

import domain.entity.Hotel;
import domain.repository.HotelRepository;

public class ModifierPrixRezDeChaussee {
    private final HotelRepository hotelRepository;

    public ModifierPrixRezDeChaussee(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void executer(double nouveauPrix) {
        Hotel hotel = hotelRepository.recupererHotel();

        hotel.modifierPrixChambresRezDeChaussee(nouveauPrix);

        hotelRepository.enregistrerHotel(hotel);
    }
}
