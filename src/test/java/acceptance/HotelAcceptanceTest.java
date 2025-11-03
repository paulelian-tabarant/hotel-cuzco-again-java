package acceptance;

import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;
import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;
import org.junit.jupiter.api.Test;
import serverside.InMemoryHotelRepository;
import userside.HotelController;
import userside.SortieCliSpy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HotelAcceptanceTest {
    @Test
    void doitAfficherChambresEtModifierPrixRdc() {
        Hotel hotelInitial = Hotel.creer(List.of(
                new Chambre.CreationDto(0, 1),
                new Chambre.CreationDto(0, 2),
                new Chambre.CreationDto(1, 101),
                new Chambre.CreationDto(1, 102),
                new Chambre.CreationDto(2, 201),
                new Chambre.CreationDto(2, 202)
        ));
        HotelRepository hotelRepository = new InMemoryHotelRepository();
        hotelRepository.enregistrerHotel(hotelInitial);

        SortieCliSpy sortie = new SortieCliSpy();
        ConsulterChambres consulterChambres = new ConsulterChambres(hotelRepository);
        ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = new ModifierPrixRezDeChaussee(hotelRepository);
        HotelController controller = new HotelController(sortie, consulterChambres, modifierPrixRezDeChaussee);

        controller.executerCommande("rdc 120.00");
        controller.executerCommande("chambres");

        List<String> lignesAttendues = List.of(
                "Modification prise en compte. Nouveau prix du rez-de-chaussée : 120,00€",
                "Liste des chambres disponibles :",
                "Etage: 0, Numéro: 1, Prix: 120,00€",
                "Etage: 0, Numéro: 2, Prix: 120,00€",
                "Etage: 1, Numéro: 101, Prix: 146,40€",
                "Etage: 1, Numéro: 102, Prix: 146,40€",
                "Etage: 2, Numéro: 201, Prix: 159,60€",
                "Etage: 2, Numéro: 202, Prix: 159,60€"
        );

        assertThat(sortie.lignes()).isEqualTo(lignesAttendues);
    }
}
