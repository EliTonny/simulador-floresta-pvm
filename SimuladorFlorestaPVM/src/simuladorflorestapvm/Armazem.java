package simuladorflorestapvm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Armazem implements Serializable {

    private int tam;
    private int ini;
    private Object[] arm;
    private Semaphore acesso;
    private Semaphore haElementos;

    public Armazem(ArrayList lista) {
        this.tam = lista.size();
        this.arm = lista.toArray();
        this.acesso = new Semaphore(1);
        this.haElementos = new Semaphore(tam);

    }

    public Object retira() throws InterruptedException {
        acesso.acquire();
        Object x = arm[ini];
        ini = (ini + 1) % tam;
        acesso.release();
        return x;
    }

    public Semaphore getHaElementos() {
        return haElementos;
    }
}
