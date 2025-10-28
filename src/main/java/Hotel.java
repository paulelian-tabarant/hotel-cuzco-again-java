import java.util.List;

public class Hotel {
    private final List<Chambre> chambres;

    public Hotel(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public static Hotel creer(List<Chambre.CreationInput> chambresInputs) {
        List<Chambre> chambres = chambresInputs.stream()
                .map(input -> {
                    double prix;
                    if (input.etage() == 0) {
                        prix = 100.0;
                    } else if (input.etage() == 1) {
                        prix = 122.0;
                    } else {
                        prix = 133.0;
                    }
                    return new Chambre(input.etage(), input.numero(), prix);
                })
                .toList();

        return new Hotel(chambres);
    }

    public static Hotel reconstruire(List<Chambre.Input> chambresExistantes) {
        List<Chambre> chambres = chambresExistantes.stream()
                .map(input -> Chambre.reconstruire(input.etage(), input.numero(), input.prix()))
                .toList();

        return new Hotel(chambres);
    }

    public record State(List<Chambre.State> chambres) {
    }

    public State state() {
        return new State(this.chambres.stream().map(Chambre::state).toList());
    }

    public void modifierPrixChambresRezDeChaussee(double nouveauPrix) {
        this.chambres.forEach(chambre -> chambre.indiquerPrixRezDeChaussee(nouveauPrix));
    }
}
