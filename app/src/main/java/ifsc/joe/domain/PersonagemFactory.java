package ifsc.joe.domain;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.TipoPersonagem;

public class PersonagemFactory {

    public static Personagem criar(TipoPersonagem tipo, int x, int y) {
        return switch (tipo) {
            case ALDEAO -> new Aldeao(x, y);
            case ARQUEIRO -> new Arqueiro(x, y);
            case CAVALEIRO -> new Cavaleiro(x, y);
        };
    }
}