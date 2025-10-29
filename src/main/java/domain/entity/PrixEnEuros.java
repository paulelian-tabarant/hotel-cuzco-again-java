package domain.entity;

public record PrixEnEuros(double valeur) {
    public PrixEnEuros(double valeur) {
        if (valeur < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif.");
        }

        this.valeur = arrondirAuCentime(valeur);
    }

    private static double arrondirAuCentime(double valeur) {
        return Math.round(valeur * 100.0) / 100.0;
    }

    public PrixEnEuros augmenteDe(double pourcentage) {
        double nouvelleValeur = this.valeur * (1 + pourcentage / 100);

        return new PrixEnEuros(nouvelleValeur);
    }
}
