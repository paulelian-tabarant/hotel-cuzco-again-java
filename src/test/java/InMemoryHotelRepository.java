public class InMemoryHotelRepository implements HotelRepository {
    private Hotel hotel;

    @Override
    public void enregistrerHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public Hotel recupererHotel() {
        return hotel;
    }
}
