package userside;

import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;

import java.util.ArrayList;
import java.util.List;

public class HotelCli {
    private final Sortie sortie;
    private final ConsulterChambres consulterChambres;
    private final ModifierPrixRezDeChaussee modifierPrixRezDeChaussee;

    public HotelCli(Sortie sortie, ConsulterChambres consulterChambres, ModifierPrixRezDeChaussee modifierPrixRezDeChaussee) {
        this.sortie = sortie;
        this.consulterChambres = consulterChambres;
        this.modifierPrixRezDeChaussee = modifierPrixRezDeChaussee;
    }

    public void executerCommande(String commande) {
        List<String> lignes = new ArrayList<>();

        if (commande.equals("chambres")) {
            lignes = executerConsulterChambres();
        } else if (commande.startsWith("rdc")) {
            String[] parties = commande.split(" ");
            lignes = executerModifierPrixRdc(parties);
        } else {
            lignes.add("Erreur : commande inconnue.");
        }

        lignes.forEach(sortie::afficherLigne);
    }

    private List<String> executerConsulterChambres() {
        List<String> lignes = new ArrayList<>();

        var chambres = consulterChambres.executer();
        lignes.add("Liste des chambres disponibles :");

        chambres.forEach(chambre -> {
            String formatAffichage = "Etage: %d, Numéro: %d, Prix: %.2f€";
            lignes.add(String.format(formatAffichage, chambre.etage(), chambre.numero(), chambre.prix()));
        });

        return lignes;
    }

    private List<String> executerModifierPrixRdc(String[] args) {
        List<String> lignes = new ArrayList<>();

        try {
            double nouveauPrix = Double.parseDouble(args[1]);

            ModifierPrixRezDeChausseeCliPresenter presenter = new ModifierPrixRezDeChausseeCliPresenter();
            modifierPrixRezDeChaussee.executer(nouveauPrix, presenter);

            lignes.add(presenter.getResultat());
        } catch (Exception e) {
            lignes.add("Erreur : prix saisi invalide");
        }

        return lignes;
    }

    private static class ModifierPrixRezDeChausseeCliPresenter implements ModifierPrixRezDeChaussee.Presenter {
        private String resultat;

        @Override
        public void prixModifie(double nouveauPrix) {
            String formatConfirmation = "Modification prise en compte. Nouveau prix du rez-de-chaussée : %.2f€";
            resultat = String.format(formatConfirmation, nouveauPrix);
        }

        @Override
        public void prixInvalide() {
            resultat = "Erreur : prix saisi invalide";
        }

        public String getResultat() {
            return resultat;
        }
    }
}
