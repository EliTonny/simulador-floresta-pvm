package simuladorflorestapvm;

import java.io.Serializable;

public class Galho implements Serializable {

    private int qtdFolhas;
    private final int qtdMaxFolhas;

    public Galho(int qtdMaxFolhas) {
        this.qtdMaxFolhas = qtdMaxFolhas;
    }

    public int getQtdFolhas() {
        return qtdFolhas;
    }

    public boolean addFolha() {
        if (qtdFolhas < qtdMaxFolhas) {
            qtdFolhas++;
            return true;
        }
        return false;
    }
}