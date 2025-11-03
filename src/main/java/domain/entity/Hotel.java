package domain.entity;

import java.util.List;

public class Hotel {
    private final List<Chambre> chambres;

    private Hotel(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public static Hotel creer(List<Chambre.CreationDto> donneesCreationChambres) {
        List<Chambre> chambres = donneesCreationChambres.stream()
                .map(Chambre::creer)
                .toList();

        return new Hotel(chambres);
    }

    public static Hotel reconstruire(List<Chambre.ReconstructionDto> dtos) {
        List<Chambre> chambres = dtos.stream()
                .map(Chambre::reconstruire)
                .toList();

        return new Hotel(chambres);
    }

    public LectureDto lire() {
        return new LectureDto(this.chambres.stream().map(Chambre::lire).toList());
    }

    public void modifierPrixChambresRezDeChaussee(double nouveauPrix) {
        this.chambres.forEach(chambre -> chambre.indiquerPrixRezDeChaussee(nouveauPrix));
    }

    public record LectureDto(List<Chambre.LectureDto> chambres) {
    }
}
