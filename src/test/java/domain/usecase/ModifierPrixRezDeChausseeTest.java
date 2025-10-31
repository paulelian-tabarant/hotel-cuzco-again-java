package domain.usecase;

import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import serverside.InMemoryHotelRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ModifierPrixRezDeChausseeTest {

    @Test
    public void doitPrendreEnCompteUneModificationDuPrixDuRezDeChaussee() {
        HotelRepository repository = new InMemoryHotelRepository();
        repository.enregistrerHotel(Hotel.creer(List.of(
                new Chambre.Creation(0, 3),
                new Chambre.Creation(0, 4),
                new Chambre.Creation(1, 11),
                new Chambre.Creation(2, 25)
        )));

        ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = new ModifierPrixRezDeChaussee(repository);
        modifierPrixRezDeChaussee.executer(120.99);

        ConsulterChambres consulterChambres = new ConsulterChambres(repository);
        List<Chambre.Lecture> chambres = consulterChambres.executer();

        assertThat(chambres).hasSameElementsAs(List.of(
                new Chambre.Lecture(0, 3, 120.99),
                new Chambre.Lecture(0, 4, 120.99),
                new Chambre.Lecture(1, 11, 147.61),
                new Chambre.Lecture(2, 25, 160.92))
        );
    }
}