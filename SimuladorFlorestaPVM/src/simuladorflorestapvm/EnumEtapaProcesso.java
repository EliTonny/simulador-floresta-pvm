package simuladorflorestapvm;

public enum EnumEtapaProcesso {

    SEMENTE(1),
    BROTO(2),
    ADULTA(3),
    REPRODUCAO(4);
    public int valor;

    private EnumEtapaProcesso(int valor) {
        this.valor = valor;
    }
}