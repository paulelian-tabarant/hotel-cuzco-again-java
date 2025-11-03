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
                new Chambre.CreationDto(0, 3),
                new Chambre.CreationDto(0, 4),
                new Chambre.CreationDto(1, 11),
                new Chambre.CreationDto(2, 25)
        )));

        ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = new ModifierPrixRezDeChaussee(repository);
        modifierPrixRezDeChaussee.executer(120.99, new ModifierPrixRezDeChausseeTestPresenter());

        ConsulterChambres consulterChambres = new ConsulterChambres(repository);
        List<Chambre.LectureDto> chambres = consulterChambres.executer();

        assertThat(chambres).hasSameElementsAs(List.of(
                new Chambre.LectureDto(0, 3, 120.99),
                new Chambre.LectureDto(0, 4, 120.99),
                new Chambre.LectureDto(1, 11, 147.61),
                new Chambre.LectureDto(2, 25, 160.92))
        );
    }
}