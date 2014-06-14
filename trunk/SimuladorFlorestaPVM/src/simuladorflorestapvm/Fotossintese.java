package simuladorflorestapvm;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Fotossintese extends Thread {

    private Terreno terreno;

    public Fotossintese(Terreno terreno) {
        this.terreno = terreno;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Arvore arv = this.terreno.retiraArvoreFotossintese();
                if (arv == null) {
                    break;
                }
                Fotossintese(arv);
                this.terreno.setArvoreAmbiente(arv);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Fotossintese.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean Fotossintese(Arvore arv) throws Exception {
        boolean sucesso = true;
        if (arv.retiraAgua(arv.getAguaFotossintese())) {
            if (arv.retiraLuz(arv.getLuzFotossintese())) {
                if (arv.retiraSaisMinerais(arv.getSaisFotossintese())) {
                    arv.setEnergia(10);
                } else {
                    arv.setAgua(arv.getAguaFotossintese());
                    arv.setLuz(arv.getLuzFotossintese());
                    sucesso = false;
                }
            } else {
                arv.setAgua(arv.getAguaFotossintese());
                sucesso = false;
            }
        } else {
            sucesso = false;
        }
        return sucesso;
    }
}
