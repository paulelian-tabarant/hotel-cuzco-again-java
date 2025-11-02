package userside;

import domain.usecase.ConsulterChambres;

public class HotelCliController {
    private final CliOutput cliOutput;
    private final ConsulterChambres consulterChambres;

    public HotelCliController(CliOutput cliOutput, ConsulterChambres consulterChambres) {
        this.cliOutput = cliOutput;
        this.consulterChambres = consulterChambres;
    }

    public void executerCommande(String listerChambres) {
        if (!"chambres".equals(listerChambres)) {
            cliOutput.afficherLigne("Erreur : commande inconnue.");
            return;
        }

        var chambres = consulterChambres.executer();

        cliOutput.afficherLigne("Liste des chambres disponibles :");
        for (var chambre : chambres) {
            cliOutput.afficherLigne(String.format("Etage: %d, Numéro: %d, Prix: %.2f€",
                    chambre.etage(), chambre.numero(), chambre.prix()));
        }
    }
}
