package simuladorflorestapvm.etapasCiclo;

import java.util.logging.Level;
import java.util.logging.Logger;
import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Arvore;
import simuladorflorestapvm.Terreno;

public class Morte extends Etapa {

    public Morte(Armazem armazem) {
        super(armazem);
    }

    @Override
    public void executar(Arvore arvore) {
        try {
            if (arvore == null) {
                return;
            }
            if (arvore.getEnergia() < 0) {
                Terreno.getInstancia().killArvore(arvore);
            }
        } catch (Exception ex) {
            Logger.getLogger(Morte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
