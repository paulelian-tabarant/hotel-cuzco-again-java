public record PrixEnEuros(double valeur) {

    public PrixEnEuros augmenteDe(double pourcentage) {
        double nouvelleValeur = this.valeur * (1 + pourcentage / 100);
        double valeurArrondie = Math.round(nouvelleValeur * 100.0) / 100.0;

        return new PrixEnEuros(valeurArrondie);
    }
}
