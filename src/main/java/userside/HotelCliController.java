package userside;

import domain.usecase.ConsulterChambres;

public class HotelCliController {
    private final SortieCli sortie;
    private final ConsulterChambres consulterChambres;

    public HotelCliController(SortieCli sortie, ConsulterChambres consulterChambres) {
        this.sortie = sortie;
        this.consulterChambres = consulterChambres;
    }

    public void executerCommande(String commande) {
        if (!commande.equals("chambres")) {
            sortie.afficherLigne("Erreur : commande inconnue.");
            return;
        }

        var chambres = consulterChambres.executer();

        sortie.afficherLigne("Liste des chambres disponibles :");
        chambres.forEach(chambre -> {
            String formatAffichage = "Etage: %d, Numéro: %d, Prix: %.2f€";
            sortie.afficherLigne(String.format(formatAffichage, chambre.etage(), chambre.numero(), chambre.prix()));
        });
    }
}
