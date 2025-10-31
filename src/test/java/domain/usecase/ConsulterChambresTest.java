package domain.usecase;

import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import serverside.InMemoryHotelRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsulterChambresTest {
    @Test
    public void doitListerLesChambresAvecPrixAdapteAuxEtagesQuandLePrixEstCeluiParDefaut() {
        HotelRepository repository = new InMemoryHotelRepository();
        repository.enregistrerHotel(Hotel.creer(List.of(
                new Chambre.Creation(0, 3),
                new Chambre.Creation(0, 4),
                new Chambre.Creation(1, 11),
                new Chambre.Creation(2, 25)
        )));
        ConsulterChambres consulterChambres = new ConsulterChambres(repository);

        List<Chambre.Lecture> chambres = consulterChambres.executer();

        assertThat(chambres).hasSameElementsAs(List.of(
                new Chambre.Lecture(0, 3, 100.0),
                new Chambre.Lecture(0, 4, 100.0),
                new Chambre.Lecture(1, 11, 122.0),
                new Chambre.Lecture(2, 25, 133.0))
        );
    }
}
