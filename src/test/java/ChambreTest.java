import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChambreTest {

    @Test
    void doitArrondirLePrixAuCentimeLePlusProche() {
        Chambre chambre = Chambre.reconstruire(1, 1, 100);

        chambre.indiquerPrixRezDeChaussee(120.99);

        assertThat(chambre.state().prix()).isEqualTo(147.61);
    }
}