package userside;

import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;

public class HotelCliController {
    private final SortieCli sortie;
    private final ConsulterChambres consulterChambres;
    private final ModifierPrixRezDeChaussee modifierPrixRezDeChaussee;

    public HotelCliController(SortieCli sortie, ConsulterChambres consulterChambres, ModifierPrixRezDeChaussee modifierPrixRezDeChaussee) {
        this.sortie = sortie;
        this.consulterChambres = consulterChambres;
        this.modifierPrixRezDeChaussee = modifierPrixRezDeChaussee;
    }

    public void executerCommande(String commande) {
        if (commande.equals("chambres")) {
            var chambres = consulterChambres.executer();

            sortie.afficherLigne("Liste des chambres disponibles :");
            chambres.forEach(chambre -> {
                String formatAffichage = "Etage: %d, Numéro: %d, Prix: %.2f€";
                sortie.afficherLigne(String.format(formatAffichage, chambre.etage(), chambre.numero(), chambre.prix()));
            });
            return;
        }

        if (commande.startsWith("rdc")) {
            String[] parties = commande.split(" ");
            if (parties.length == 2) {
                double nouveauPrix = Double.parseDouble(parties[1]);
                modifierPrixRezDeChaussee.executer(nouveauPrix);
            }
        }

        sortie.afficherLigne("Erreur : commande inconnue.");
    }
}
