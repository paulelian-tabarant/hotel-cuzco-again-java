package serverside;

import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HotelRepositoryTest {

    @Test
    public void doitEnregistrerToutesLesChambresAvecLeurPrix() {
        HotelRepository repository = new InMemoryHotelRepository();

        repository.enregistrerHotel(Hotel.reconstruire(List.of(
                new Chambre.ReconstructionDto(0, 1, 100.0),
                new Chambre.ReconstructionDto(1, 10, 122.0),
                new Chambre.ReconstructionDto(2, 20, 133.0)
        )));
        Hotel hotelRecupere = repository.recupererHotel();

        List<Chambre.LectureDto> chambreStates = hotelRecupere.lire().chambres();
        assertThat(chambreStates).hasSameElementsAs(List.of(
                new Chambre.LectureDto(0, 1, 100.0),
                new Chambre.LectureDto(1, 10, 122.0),
                new Chambre.LectureDto(2, 20, 133.0))
        );
    }

    @Test
    public void neDoitPasModifierLesChambresTantQuellesNeSontPasEnregistrees() {
        Hotel hotel = Hotel.reconstruire(List.of(
                new Chambre.ReconstructionDto(0, 1, 100.0),
                new Chambre.ReconstructionDto(1, 10, 122.0)
        ));

        HotelRepository repository = new InMemoryHotelRepository();
        repository.enregistrerHotel(hotel);
        hotel.modifierPrixChambresRezDeChaussee(10.0);
        Hotel hotelRecupere = repository.recupererHotel();

        assertThat(hotelRecupere.lire().chambres()).hasSameElementsAs(List.of(
                new Chambre.LectureDto(0, 1, 100.0),
                new Chambre.LectureDto(1, 10, 122.0)
        ));
    }
}
