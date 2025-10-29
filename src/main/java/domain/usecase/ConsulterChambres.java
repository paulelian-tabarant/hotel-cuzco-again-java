package domain.usecase;

import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;

import java.util.List;

public class ConsulterChambres {
    private final HotelRepository hotelRepository;

    public ConsulterChambres(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Chambre.State> executer() {
        Hotel hotel = hotelRepository.recupererHotel();

        return hotel.state().chambres();
    }
}
