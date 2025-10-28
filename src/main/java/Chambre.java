public class Chambre {
    private final int etage;
    private final int numero;
    private double prix;

    public Chambre(int etage, int numero, double prix) {
        this.etage = etage;
        this.numero = numero;
        this.prix = prix;
    }

    public static Chambre creer(CreationInput input) {
        return new Chambre(input.etage(), input.numero(), calculerPrixChambreDepuisPrixRdcEtEtage(100.00, input.etage()));
    }

    public static Chambre reconstruire(int etage, int numero, double prix) {
        return new Chambre(etage, numero, prix);
    }

    public void indiquerPrixRezDeChaussee(double nouveauPrix) {
        this.prix = calculerPrixChambreDepuisPrixRdcEtEtage(nouveauPrix, this.etage);
    }

    private static double calculerPrixChambreDepuisPrixRdcEtEtage(double prixRdc, int etage) {
        switch (etage) {
            case 0 -> {
                return prixRdc;
            }
            // arrondir au centime le plus proche
            case 1 -> {
                return Math.round(prixRdc * 1.22 * 100.0) / 100.0;
            }
            default -> {
                return Math.round(prixRdc * 1.33 * 100.0) / 100.0;
            }
        }
    }

    public record CreationInput(int etage, int numero) {
    }

    public record Input(int etage, int numero, double prix) {

    }

    public record State(int etage, int numero, double prix) {

    }

    public State state() {
        return new State(etage, numero, prix);
    }
}
