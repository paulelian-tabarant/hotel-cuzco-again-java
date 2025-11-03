import domain.entity.Chambre;
import domain.entity.Hotel;
import domain.repository.HotelRepository;
import domain.usecase.ConsulterChambres;
import domain.usecase.ModifierPrixRezDeChaussee;
import serverside.InMemoryHotelRepository;
import userside.HotelCli;
import userside.Sortie;
import userside.SortieStdOut;

void main() {
    Hotel hotelInitial = Hotel.creer(List.of(
            new Chambre.CreationDto(0, 1),
            new Chambre.CreationDto(0, 2),
            new Chambre.CreationDto(1, 101),
            new Chambre.CreationDto(1, 102),
            new Chambre.CreationDto(2, 201),
            new Chambre.CreationDto(2, 202)
    ));
    HotelRepository hotelRepository = new InMemoryHotelRepository();
    hotelRepository.enregistrerHotel(hotelInitial);

    Sortie sortie = new SortieStdOut();
    ConsulterChambres consulterChambres = new ConsulterChambres(hotelRepository);
    ModifierPrixRezDeChaussee modifierPrixRezDeChaussee = new ModifierPrixRezDeChaussee(hotelRepository);
    HotelCli controller = new HotelCli(sortie, consulterChambres, modifierPrixRezDeChaussee);

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
