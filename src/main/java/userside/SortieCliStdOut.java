package userside;

public class SortieCliStdOut implements SortieCli {
    @Override
    public void afficherLigne(String ligne) {
        IO.println(ligne);
    }
}
