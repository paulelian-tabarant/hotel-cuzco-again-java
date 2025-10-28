public class Chambre {
    private final int etage;
    private final int numero;
    private double prix;

    public Chambre(int etage, int numero, double prix) {
        this.etage = etage;
        this.numero = numero;
        this.prix = prix;
    }

    public static Chambre reconstruire(int etage, int numero, double prix) {
        return new Chambre(etage, numero, prix);
    }

    public void indiquerPrixRezDeChaussee(double nouveauPrix) {
        double nouveauPrixFinal;

        switch (etage) {
            case 0 -> nouveauPrixFinal = nouveauPrix;
            // arrondir au centime le plus proche
            case 1 -> nouveauPrixFinal = Math.round(nouveauPrix * 1.22 * 100.0) / 100.0;
            default -> nouveauPrixFinal = Math.round(nouveauPrix * 1.33 * 100.0) / 100.0;
        }

        this.prix = nouveauPrixFinal;
    }

    public record Input(int etage, int numero, double prix) {
    }

    public record State(int etage, int numero, double prix) {
    }

    public record CreationInput(int etage, int numero) {
    }

    public State state() {
        return new State(etage, numero, prix);
    }
}
