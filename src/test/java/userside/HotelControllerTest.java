package userside;

import domain.entity.Chambre;
import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HotelControllerTest {
    ConsulterChambres consulterChambres = Mockito.mock(ConsulterChambres.class);
    ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = Mockito.mock(ModifierPrixRezDeChaussee.class);

    @Test
    void doitExecuterLaFonctionnaliteDeListingDesChambres() {
        SortieCliSpy sortieSpy = new SortieCliSpy();
        Mockito.when(consulterChambres.executer()).thenReturn(List.of(
                new Chambre.Lecture(0, 1, 100.00),
                new Chambre.Lecture(1, 101, 148.70)
        ));

        HotelController controller = new HotelController(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);
        controller.executerCommande("chambres");

        assertThat(sortieSpy.lignes()).isEqualTo(List.of(
                "Liste des chambres disponibles :",
                "Etage: 0, Numéro: 1, Prix: 100,00€",
                "Etage: 1, Numéro: 101, Prix: 148,70€"
        ));
    }

    @Test
    void doitExecuterLaFonctionnaliteDeModificationDuPrixDuRezDeChaussee() {
        SortieCliSpy sortieSpy = new SortieCliSpy();

        HotelController controller = new HotelController(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);
        controller.executerCommande("rdc 120.00");

        Mockito.verify(modifierPrixRezDeChaussee).executer(120.00);
    }

    @Test
    void doitConfirmerLaModificationDuPrixDuRezDeChaussee() {
        SortieCliSpy sortieSpy = new SortieCliSpy();

        HotelController controller = new HotelController(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);
        controller.executerCommande("rdc 120.00");

        assertThat(sortieSpy.lignes()).isEqualTo(List.of("Modification prise en compte. Nouveau prix du rez-de-chaussée : 120,00€"));
    }

    // TODO: test pour gestion des erreurs de saisie (ex: "rdc abc", "rdc -50", etc.)

    @Test
    void doitAfficherMessageErreurQuandCommandeInconnue() {
        SortieCliSpy sortieSpy = new SortieCliSpy();
        HotelController controller = new HotelController(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);
        controller.executerCommande("commande_inconnue");

        assertThat(sortieSpy.lignes()).isEqualTo(List.of(
                "Erreur : commande inconnue."
        ));
    }
}