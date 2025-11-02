import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;
import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;
import serverside.InMemoryHotelRepository;
import userside.HotelController;
import userside.SortieCli;
import userside.SortieCliStdOut;

void main() {
    Hotel hotelInitial = Hotel.creer(List.of(
            new Chambre.Creation(0, 1),
            new Chambre.Creation(0, 2),
            new Chambre.Creation(1, 101),
            new Chambre.Creation(1, 102),
            new Chambre.Creation(2, 201),
            new Chambre.Creation(2, 202)
    ));
    HotelRepository hotelRepository = new InMemoryHotelRepository();
    hotelRepository.enregistrerHotel(hotelInitial);

    SortieCli sortie = new SortieCliStdOut();
    ConsulterChambres consulterChambres = new ConsulterChambres(hotelRepository);
    ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = new ModifierPrixRezDeChaussee(hotelRepository);
    HotelController controller = new HotelController(sortie, consulterChambres, modifierPrixRezDeChaussee);

    while (true) {
        sortie.afficherLigne("Entrez une commande (chambres, rdc <prix>, quitter) : ");

        String commande = IO.readln();

        if (commande.equals("quitter")) {
            sortie.afficherLigne("Au revoir !");
            break;
        }

        controller.executerCommande(commande);
    }
}
