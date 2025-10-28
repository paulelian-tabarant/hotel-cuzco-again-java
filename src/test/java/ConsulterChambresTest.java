import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsulterChambresTest {
    @Test
    public void doitListerLesChambreAvecPrixAdapteAuxEtagesQuandLePrixEstCeluiParDefaut() {
        HotelRepository repository = new InMemoryHotelRepository();
        repository.enregistrerHotel(Hotel.creer(List.of(
                new Chambre.CreationInput(0, 3),
                new Chambre.CreationInput(0, 4),
                new Chambre.CreationInput(1, 11),
                new Chambre.CreationInput(2, 25)
        )));
        ConsulterChambres consulterChambres = new ConsulterChambres(repository);

        List<Chambre.State> chambres = consulterChambres.executer();

        assertThat(chambres).hasSameElementsAs(List.of(
                new Chambre.State(0, 3, 100.0),
                new Chambre.State(0, 4, 100.0),
                new Chambre.State(1, 11, 122.0),
                new Chambre.State(2, 25, 133.0))
        );
    }

    @Test
    public void doitPrendreEnCompteUneModificationDuPrixDuRezDeChaussee() {
        HotelRepository repository = new InMemoryHotelRepository();
        repository.enregistrerHotel(Hotel.creer(List.of(
                new Chambre.CreationInput(0, 3),
                new Chambre.CreationInput(0, 4),
                new Chambre.CreationInput(1, 11),
                new Chambre.CreationInput(2, 25)
        )));

        ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = new ModifierPrixRezDeChaussee(repository);
        modifierPrixRezDeChaussee.executer(120.99);

        ConsulterChambres consulterChambres = new ConsulterChambres(repository);
        List<Chambre.State> chambres = consulterChambres.executer();

        assertThat(chambres).hasSameElementsAs(List.of(
                new Chambre.State(0, 3, 120.99),
                new Chambre.State(0, 4, 120.99),
                new Chambre.State(1, 11, 147.61),
                new Chambre.State(2, 25, 160.92))
        );
    }
}
