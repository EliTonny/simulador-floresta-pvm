package simuladorflorestapvm;

public class Lenhador extends Thread {

    private boolean finalizado;
    private int numArvoresCortadas;
    private String nomeLenhador;

    public Lenhador(String nomeLenhador) {
        this.nomeLenhador = nomeLenhador;
    }

    @Override
    public void run() {
        try {
            while (!finalizado) {
                Arvore arvore = Terreno.getInstancia().retiraArvoreCorte();
                if (arvore != null) {
                    Thread.sleep(10);
                    if (Terreno.getInstancia().killArvore(arvore)) {
                        numArvoresCortadas++;
                        System.out.println(this.nomeLenhador + " cortou arvore \n" + arvore.ImprimeDados()
                                + "Arvores cortadas: " + numArvoresCortadas + "\n");

                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getNumArvoresCortadas() {
        return numArvoresCortadas;
    }

    public String getNomeLenhador() {
        return nomeLenhador;
    }

    public String getDados() {
        return "Lenhador " + nomeLenhador + " cortou " + numArvoresCortadas + " arvores";
    }
}
