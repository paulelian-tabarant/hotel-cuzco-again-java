package userside;

import domain.entity.Chambre;
import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HotelCliTest {
    ConsulterChambres consulterChambres = Mockito.mock(ConsulterChambres.class);
    ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = Mockito.mock(ModifierPrixRezDeChaussee.class);

    @Test
    void doitExecuterLaFonctionnaliteDeListingDesChambres() {
        SortieSpy sortieSpy = new SortieSpy();
        Mockito.when(consulterChambres.executer()).thenReturn(List.of(
                new Chambre.LectureDto(0, 1, 100.00),
                new Chambre.LectureDto(1, 101, 148.70)
        ));

        HotelCli controller = new HotelCli(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);
        controller.executerCommande("chambres");

        assertThat(sortieSpy.lignes()).isEqualTo(List.of(
                "Liste des chambres disponibles :",
                "Etage: 0, Numéro: 1, Prix: 100,00€",
                "Etage: 1, Numéro: 101, Prix: 148,70€"
        ));
    }

    @Test
    void doitConfirmerLaModificationDuPrixDuRezDeChaussee() {
        SortieSpy sortieSpy = new SortieSpy();

        HotelCli controller = new HotelCli(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);

        Mockito.doAnswer(invocation -> {
            double nouveauPrix = invocation.getArgument(0);
            ModifierPrixRezDeChaussee.Presenter presenter = invocation.getArgument(1);
            presenter.prixModifie(nouveauPrix);
            return null;
        }).when(modifierPrixRezDeChaussee).executer(Mockito.anyDouble(), Mockito.any(ModifierPrixRezDeChaussee.Presenter.class));

        controller.executerCommande("rdc 120.00");

        assertThat(sortieSpy.lignes()).isEqualTo(List.of("Modification prise en compte. Nouveau prix du rez-de-chaussée : 120,00€"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", ""})
    void doitAfficherMessageErreurQuandPrixFourniNEstPasUnNombre(String prixInvalide) {
        SortieSpy sortieSpy = new SortieSpy();

        HotelCli controller = new HotelCli(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);
        controller.executerCommande("rdc" + " " + prixInvalide);

        assertThat(sortieSpy.lignes()).isEqualTo(List.of("Erreur : prix saisi invalide"));
    }

    @Test
    void doitAfficherMessageErreurQuandCommandeInconnue() {
        SortieSpy sortieSpy = new SortieSpy();
        HotelCli controller = new HotelCli(sortieSpy, consulterChambres, modifierPrixRezDeChaussee);
        controller.executerCommande("commande_inconnue");

        assertThat(sortieSpy.lignes()).isEqualTo(List.of("Erreur : commande inconnue."));
    }
}