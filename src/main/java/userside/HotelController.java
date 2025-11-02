package userside;

import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;

public class HotelController {
    private final SortieCli sortie;
    private final ConsulterChambres consulterChambres;
    private final ModifierPrixRezDeChaussee modifierPrixRezDeChaussee;

    public HotelController(SortieCli sortie, ConsulterChambres consulterChambres, ModifierPrixRezDeChaussee modifierPrixRezDeChaussee) {
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
            try {
                double nouveauPrix = Double.parseDouble(parties[1]);
                if (nouveauPrix < 0) {
                    throw new IllegalArgumentException();
                }

                modifierPrixRezDeChaussee.executer(nouveauPrix);

                String formatConfirmation = "Modification prise en compte. Nouveau prix du rez-de-chaussée : %.2f€";
                sortie.afficherLigne(String.format(formatConfirmation, nouveauPrix));
            } catch (Exception e) {
                sortie.afficherLigne("Erreur : prix saisi invalide");
            }

            return;
        }

        sortie.afficherLigne("Erreur : commande inconnue.");
    }
}
