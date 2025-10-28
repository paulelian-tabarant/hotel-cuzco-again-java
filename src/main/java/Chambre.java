import java.util.HashMap;
import java.util.Map;

public class Chambre {
    private static final PrixEnEuros PRIX_REZ_DE_CHAUSSEE_INITIAL = new PrixEnEuros(100.00);
    private static final Map<Integer, Double> POURCENTAGE_AUGMENTATION_PRIX_PAR_ETAGE = new HashMap<>() {{
        put(0, 0.0);
        put(1, 22.0);
        put(2, 33.0);
    }};

    private final int etage;
    private final int numero;
    private PrixEnEuros prix;

    private Chambre(int etage, int numero, PrixEnEuros prix) {
        this.etage = etage;
        this.numero = numero;
        this.prix = prix;
    }

    public static Chambre creer(CreationInput input) {
        return new Chambre(input.etage(), input.numero(), PRIX_REZ_DE_CHAUSSEE_INITIAL.augmenteDe(POURCENTAGE_AUGMENTATION_PRIX_PAR_ETAGE.get(input.etage())));
    }

    public static Chambre reconstruire(int etage, int numero, double prix) {
        return new Chambre(etage, numero, new PrixEnEuros(prix));
    }

    public void indiquerPrixRezDeChaussee(double nouveauPrix) {
        PrixEnEuros prixRdc = new PrixEnEuros(nouveauPrix);
        this.prix = prixRdc.augmenteDe(POURCENTAGE_AUGMENTATION_PRIX_PAR_ETAGE.get(etage));
    }

    public State state() {
        return new State(etage, numero, prix.valeur());
    }

    public record CreationInput(int etage, int numero) {
    }

    public record Input(int etage, int numero, double prix) {
    }

    public record State(int etage, int numero, double prix) {
    }
}
