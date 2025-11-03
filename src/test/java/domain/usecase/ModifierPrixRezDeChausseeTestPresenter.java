package domain.usecase;

public class ModifierPrixRezDeChausseeTestPresenter implements ModifierPrixRezDeChaussee.Presenter {
    public Exception erreurPresentee = null;

    @Override
    public void prixModifie(double nouveauPrix) {

    }

    @Override
    public void prixInvalide() {
        erreurPresentee = new IllegalArgumentException("Prix invalide");
    }
}
