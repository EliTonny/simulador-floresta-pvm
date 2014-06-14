package simuladorflorestapvm;

public class Galho {

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