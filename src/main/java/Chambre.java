public class Chambre {
    private final int etage;
    private final int numero;
    private final double prix;

    public Chambre(int etage, int numero, double prix) {
        this.etage = etage;
        this.numero = numero;
        this.prix = prix;
    }

    public static Chambre reconstruire(int etage, int numero, double prix) {
        return new Chambre(etage, numero, prix);
    }

    public record Input(int etage, int numero, double prix) {
    }

    // TODO: simplifier par héritage ?
    public record State (int etage, int numero, double prix) {
    }

    public record CreationInput(int etage, int numero) {
    }

    public State state() {
        return new State(etage, numero, prix);
    }
}
