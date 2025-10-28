import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HotelRepositoryTest {

    @Test
    public void doitEnregistrerToutesLesChambresAvecLeurPrix() {
        HotelRepository repository = new InMemoryHotelRepository();
        repository.enregistrerHotel(Hotel.reconstruire(List.of(
                new Chambre.Input(0, 1, 100.0),
                new Chambre.Input(1, 10, 122.0),
                new Chambre.Input(2, 20, 133.0)
        )));

        Hotel hotelRecupere = repository.recupererHotel();
        List<Chambre.State> chambreStates = hotelRecupere.state().chambres();

        assertThat(chambreStates).hasSameElementsAs(List.of(
                new Chambre.State(0, 1, 100.0),
                new Chambre.State(1, 10, 122.0),
                new Chambre.State(2, 20, 133.0))
        );
    }

    @Test
    public void neDoitPasModifierLesChambresTantQuellesNeSontPasEnregistrees() {
        HotelRepository repository = new InMemoryHotelRepository();

        Hotel hotel = Hotel.reconstruire(List.of(
                new Chambre.Input(0, 1, 100.0),
                new Chambre.Input(1, 10, 122.0)
        ));

        repository.enregistrerHotel(hotel);

        hotel.modifierPrixChambresRezDeChaussee(10.0);

        Hotel hotelRecupere = repository.recupererHotel();

        assertThat(hotelRecupere.state().chambres()).hasSameElementsAs(List.of(
                new Chambre.State(0, 1, 100.0),
                new Chambre.State(1, 10, 122.0)
        ));
    }
}
