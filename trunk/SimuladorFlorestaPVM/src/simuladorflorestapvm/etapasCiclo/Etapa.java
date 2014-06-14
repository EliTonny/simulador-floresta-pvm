package simuladorflorestapvm.etapasCiclo;

import java.util.logging.Level;
import java.util.logging.Logger;
import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Arvore;

public abstract class Etapa extends Thread {

    private Armazem armazem;

    public Etapa(Armazem armazem) {
        this.armazem = armazem;
    }

    public abstract void executar(Arvore arvore);

    @Override
    public void run() {
        while (armazem.getHaElementos().tryAcquire()) {
            try {
                Object obj = armazem.retira();
                if (obj instanceof Arvore) {
                    executar((Arvore) obj);
                } else {
                    throw new Exception("Armazem com objetos incompativeis");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Morte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Morte.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
