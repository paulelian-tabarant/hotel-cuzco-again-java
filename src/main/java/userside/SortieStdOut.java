package userside;

public class SortieStdOut implements Sortie {
    @Override
    public void afficherLigne(String ligne) {
        IO.println(ligne);
    }
}
