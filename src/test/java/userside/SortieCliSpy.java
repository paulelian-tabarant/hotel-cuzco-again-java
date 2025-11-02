package userside;

import java.util.ArrayList;
import java.util.List;

public class SortieCliSpy implements SortieCli {
    private final List<String> lignes = new ArrayList<>();

    List<String> lignes() {
        return lignes;
    }

    @Override
    public void afficherLigne(String ligne) {
        lignes.add(ligne);
    }
}
