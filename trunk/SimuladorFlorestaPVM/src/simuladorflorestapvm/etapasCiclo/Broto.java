package simuladorflorestapvm.etapasCiclo;

import java.util.logging.Level;
import java.util.logging.Logger;
import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Arvore;
import simuladorflorestapvm.EnumEtapaProcesso;
import simuladorflorestapvm.Galho;

public class Broto extends Etapa {

    public Broto(Armazem armazem) {
        super(armazem);
    }

    @Override
    public Arvore executar(Arvore arvore) {
        if (arvore == null) {
            return null;
        }
        try {
            //Custo de vida
            arvore.retiraEnergia(6);
            if (arvore.getEnergia() > 50) {
                arvore.retiraEnergia(50);
                arvore.setTamanho(5);
            }
            for (Galho galhos : arvore.getGalhos()) {
                if (arvore.getEnergia() > 100) {
                    galhos.addFolha();
                }
            }
            //Crescer galho
            if (arvore.getEnergia() > 500) {
                arvore.addGalho(true);
            }

            if (arvore.getTamanho() >= 200) {
                arvore.setEtapa(EnumEtapaProcesso.ADULTA);
            }
        } catch (Exception ex) {
            Logger.getLogger(Broto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arvore;
    }
}
