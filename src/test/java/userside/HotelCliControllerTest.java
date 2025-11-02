package userside;

import domain.entity.Chambre;
import domain.usecase.ConsulterChambres;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HotelCliControllerTest {

    @Test
    void doitExecuterLaFonctionnaliteDeListingDesChambres() {
        SortieCliSpy sortieCli = new SortieCliSpy();
        ConsulterChambres consulterChambres = Mockito.mock(ConsulterChambres.class);
        Mockito.when(consulterChambres.executer()).thenReturn(List.of(
                new Chambre.Lecture(0, 1, 100.00),
                new Chambre.Lecture(1, 101, 148.70)
        ));

        HotelCliController controller = new HotelCliController(sortieCli, consulterChambres);
        controller.executerCommande("chambres");

        assertThat(sortieCli.lignes()).isEqualTo(List.of(
                "Liste des chambres disponibles :",
                "Etage: 0, Numéro: 1, Prix: 100,00€",
                "Etage: 1, Numéro: 101, Prix: 148,70€"
        ));
    }

    @Test
    void doitAfficherMessageErreurQuandCommandeInconnue() {
        SortieCliSpy sortieCli = new SortieCliSpy();
        ConsulterChambres consulterChambres = Mockito.mock(ConsulterChambres.class);

        HotelCliController controller = new HotelCliController(sortieCli, consulterChambres);
        controller.executerCommande("commande_inconnue");

        assertThat(sortieCli.lignes()).isEqualTo(List.of(
                "Erreur : commande inconnue."
        ));
    }
}