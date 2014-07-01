package simuladorflorestapvm.etapasCiclo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Arvore;

public abstract class Etapa {

    private Armazem armazem;

    public Etapa(Armazem armazem) {
        this.armazem = armazem;
    }

    public abstract Arvore executar(Arvore arvore) throws Exception;

    public ArrayList run() {
        ArrayList retorno = new ArrayList();
        while (armazem.getHaElementos().tryAcquire()) {
            try {
                Object obj = armazem.retira();
                if (obj instanceof Arvore) {
                    Arvore arvore = executar((Arvore) obj);
                    if(arvore != null)
                        retorno.add(arvore);
                } else {
                    throw new Exception("Armazem com objetos incompativeis");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Morte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Morte.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return retorno;
    }
}
