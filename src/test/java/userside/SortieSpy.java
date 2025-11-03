package userside;

import java.util.ArrayList;
import java.util.List;

public class SortieSpy implements Sortie {
    private final List<String> lignes = new ArrayList<>();

    public List<String> lignes() {
        return lignes;
    }

    @Override
    public void afficherLigne(String ligne) {
        lignes.add(ligne);
    }
}
