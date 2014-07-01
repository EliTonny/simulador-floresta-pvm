package simuladorflorestapvm;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ambiente extends Thread {

    private Terreno terreno;
    private AtomicBoolean finalizou;
    private AtomicInteger numArvoresProcessadas;

    public Ambiente(Terreno terreno, AtomicBoolean finalizou, AtomicInteger numArvoresProcessadas) {
        this.terreno = terreno;
        this.finalizou = finalizou;
        this.numArvoresProcessadas = numArvoresProcessadas;
    }

    @Override
    public void run() {
        try {
            int numCiclos = this.terreno.getNumArvores() * Gerenciador.NUM_CLICOS_DIA;

            while (true) {
                if (numArvoresProcessadas.get() >= numCiclos) {
                    break;
                }
                numArvoresProcessadas.incrementAndGet();

                Arvore arv = this.terreno.retiraArvoreAmbiente();
                if (arv == null) {
                    finalizou.set(true);
                    break;
                }
                Processa(arv);
                this.terreno.setArvoreFotossintese(arv);
            }

            finalizou.set(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Processa(Arvore arvore) {
        arvore.setAgua(getAgua());
        arvore.setSaisMinerais(getSaisMinerais());
        arvore.setLuz(getLuz());
    }

    private int getAgua() {
        return getRandom(1, 10);
    }

    private int getLuz() {
        return getRandom(1, 10);
    }

    private int getSaisMinerais() {
        return getRandom(1, 10);
    }

    private int getRandom(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
