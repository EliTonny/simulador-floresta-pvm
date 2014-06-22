package simuladorflorestapvm.etapasCiclo;

import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Arvore;

public class Morte extends Etapa {

    public Morte(Armazem armazem) {
        super(armazem);
    }

    @Override
    public Arvore executar(Arvore arvore) {
        if (arvore == null)
            return null;
        
        if (arvore.getEnergia() < 0)
            return arvore;
        else
            return null;
    }
}
