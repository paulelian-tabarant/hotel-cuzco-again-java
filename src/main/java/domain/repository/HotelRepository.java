package domain.repository;

import domain.entity.Hotel;

public interface HotelRepository {
    void enregistrerHotel(Hotel hotel);

    Hotel recupererHotel();
}
