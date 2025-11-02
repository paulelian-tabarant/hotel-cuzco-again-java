package serverside;

import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;

import java.util.List;

public class InMemoryHotelRepository implements HotelRepository {
    private Hotel hotel;

    @Override
    public void enregistrerHotel(Hotel hotel) {
        this.hotel = copierHotel(hotel);
    }

    @Override
    public Hotel recupererHotel() {
        return copierHotel(this.hotel);
    }

    private Hotel copierHotel(Hotel hotel) {
        List<Chambre.Reconstruction> chambreInputs = hotel.state().chambres().stream()
                .map(chambreState -> new Chambre.Reconstruction(
                        chambreState.etage(),
                        chambreState.numero(),
                        chambreState.prix()
                ))
                .toList();

        return Hotel.reconstruire(chambreInputs);
    }
}
