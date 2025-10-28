import java.util.List;

public class Hotel {
    private final List<Chambre> chambres;

    private Hotel(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public static Hotel creer(List<Chambre.CreationInput> donneesCreationChambres) {
        List<Chambre> chambres = donneesCreationChambres.stream()
                .map(Chambre::creer)
                .toList();

        return new Hotel(chambres);
    }

    public static Hotel reconstruire(List<Chambre.Input> chambresExistantes) {
        List<Chambre> chambres = chambresExistantes.stream()
                .map(input -> Chambre.reconstruire(input.etage(), input.numero(), input.prix()))
                .toList();

        return new Hotel(chambres);
    }

    public State state() {
        return new State(this.chambres.stream().map(Chambre::state).toList());
    }

    public void modifierPrixChambresRezDeChaussee(double nouveauPrix) {
        this.chambres.forEach(chambre -> chambre.indiquerPrixRezDeChaussee(nouveauPrix));
    }

    public record State(List<Chambre.State> chambres) {
    }
}
