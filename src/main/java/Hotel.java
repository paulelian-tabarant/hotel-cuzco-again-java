import java.util.List;

public class Hotel {
    private List<Chambre> chambres;

    public Hotel(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public static Hotel creer(List<Chambre.CreationInput> chambresInputs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static Hotel reconstruire(List<Chambre.Input> chambresExistantes) {
        List<Chambre> chambres = chambresExistantes.stream()
                .map(input -> Chambre.reconstruire(input.etage(), input.numero(), input.prix()))
                .toList();

        return new Hotel(chambres);
    }

    record State(List<Chambre.State> chambres) {
    }

    public State state() {
        return new State(this.chambres.stream().map(Chambre::state).toList());
    }
}
