public class Chambre {
    private final int etage;
    private final int numero;
    private PrixEnEuros prix;

    private static final PrixEnEuros PRIX_REZ_DE_CHAUSSEE_INITIAL = new PrixEnEuros(100.00);

    private Chambre(int etage, int numero, PrixEnEuros prix) {
        this.etage = etage;
        this.numero = numero;
        this.prix = prix;
    }

    public static Chambre creer(CreationInput input) {
        return new Chambre(input.etage(), input.numero(), calculerPrixChambreDepuisPrixRdcEtEtage(PRIX_REZ_DE_CHAUSSEE_INITIAL, input.etage()));
    }

    public static Chambre reconstruire(int etage, int numero, double prix) {
        return new Chambre(etage, numero, new PrixEnEuros(prix));
    }

    public void indiquerPrixRezDeChaussee(double nouveauPrix) {
        this.prix = calculerPrixChambreDepuisPrixRdcEtEtage(new PrixEnEuros(nouveauPrix), etage);
    }

    private static PrixEnEuros calculerPrixChambreDepuisPrixRdcEtEtage(PrixEnEuros prixRdc, int etage) {
        switch (etage) {
            case 0 -> {
                return prixRdc;
            }
            case 1 -> {
                return prixRdc.augmenteDe(22);
            }
            default -> {
                return prixRdc.augmenteDe(33);
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
        return new State(etage, numero, prix.valeur());
    }
}
