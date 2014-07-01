package simuladorflorestapvm;

import java.io.Serializable;

public enum EnumEtapaProcesso implements Serializable {

    ALL(0),
    SEMENTE(1),
    BROTO(2),
    ADULTA(3),
    REPRODUCAO(4);
    public int valor;

    private EnumEtapaProcesso(int valor) {
        this.valor = valor;
    }
}