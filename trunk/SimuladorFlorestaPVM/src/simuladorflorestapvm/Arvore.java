package simuladorflorestapvm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Arvore implements Serializable {

    private final int TEMPO_ESPERA = 0;
    private int agua;
    private int luz;
    private int saisMinerais;
    private int energia;
    private int tamanhoMax;
    private int tamanho;
    private int raioMax;
    private Terreno terreno;
    private Posicao posicao;
    private Lock lock;
    private Condition temAgua;
    private Condition temLuz;
    private Condition temSaisMinerais;
    private int luzFotossintese;
    private int aguaFotossintese;
    private int saisFotossintese;
    private ArrayList<Galho> galhos;
    private EnumEtapaProcesso etapa;

    public Arvore(int tamanhoMax,
            int raioMax,
            int luzFot,
            int aguaFot,
            int saisFot,
            EnumEtapaProcesso etapa) {
        lock = new ReentrantLock();
        temAgua = lock.newCondition();
        temLuz = lock.newCondition();
        temSaisMinerais = lock.newCondition();
        this.tamanhoMax = tamanhoMax;
        this.raioMax = raioMax;
        this.luzFotossintese = luzFot;
        this.aguaFotossintese = aguaFot;
        this.saisFotossintese = saisFot;
        this.galhos = new ArrayList();
        this.etapa = etapa;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setEtapa(EnumEtapaProcesso etapa) {
        this.etapa = etapa;
    }

    public EnumEtapaProcesso getEtapa() {
        return etapa;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    public int getLuzFotossintese() {
        return luzFotossintese;
    }

    public int getAguaFotossintese() {
        return aguaFotossintese;
    }

    public int getSaisFotossintese() {
        return saisFotossintese;
    }

    public synchronized void retiraEnergia(int qtd) throws Exception {
        energia -= qtd;
    }

    public synchronized void setEnergia(int qtd) {
        this.energia += qtd;
    }

    public int getEnergia() {
        return energia;
    }
    
    public String ImprimeDados() {
        String saida = "";
        int qtdfolhas = 0;
        saida += this.etapa.toString() + "\n";
        saida += Integer.toString(this.tamanho) + "\n";
        saida += "Qtd. Galhos: " + Integer.toString(galhos.size()) + "\n";
        for (Galho galho : galhos) {
            qtdfolhas += galho.getQtdFolhas();
        }
        saida += "Qtd. Folhas: " + Integer.toString(qtdfolhas) + "\n";
        saida += this.getPosicao().toString() + "\n";
        saida += "Agua:" + this.agua + "\n";
        saida += "Luz:" + this.luz + "\n";
        saida += "Sais:" + this.saisMinerais + "\n";
        saida += "Energia:" + this.energia + "\n";
        return saida;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean setTamanho(int tamanho) {
        if (this.tamanho >= this.tamanhoMax) {
            return false;
        }
        this.tamanho += tamanho;
        if (this.tamanho > this.tamanhoMax) {
            this.tamanho = this.tamanhoMax;
        }
        return true;
    }

    public boolean retiraAgua(int qtd) throws Exception {
        lock.lock();
        try {
            while (agua < qtd) {
                if (!temAgua.await(TEMPO_ESPERA, TimeUnit.MILLISECONDS)) {
                    return false;
                }
            }
            agua -= qtd;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void setAgua(int qtd) {
        lock.lock();
        try {
            //falta fazer cálculo para verificar quanto 
            //a planta consegue absorver considerando
            //que existem outras plantas ao redor

            this.agua += qtd;
            temAgua.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean retiraLuz(int qtd) throws Exception {
        lock.lock();
        try {
            while (luz < qtd) {
                if (!temLuz.await(TEMPO_ESPERA, TimeUnit.MILLISECONDS)) {
                    return false;
                }
            }
            luz -= qtd;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void setLuz(int qtd) {
        lock.lock();
        try {

            //falta fazer cálculo para verificar quanto 
            //a planta consegue absorver considerando
            //que existem outras plantas ao redor
            this.luz += qtd;
            temLuz.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean retiraSaisMinerais(int qtd) throws Exception {
        lock.lock();
        try {
            while (saisMinerais < qtd) {
                if (!temSaisMinerais.await(TEMPO_ESPERA, TimeUnit.MILLISECONDS)) {
                    return false;
                }
            }
            saisMinerais -= qtd;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void setSaisMinerais(int qtd) {
        lock.lock();
        try {

            //falta fazer cálculo para verificar quanto 
            //a planta consegue absorver considerando
            //que existem outras plantas ao redor
            this.saisMinerais += qtd;
            temSaisMinerais.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean addGalho(boolean validarEnergia) throws Exception {
        if (this.energia > 500 || validarEnergia == false) {
            if (validarEnergia) {
                this.retiraEnergia(500);
            }
            this.galhos.add(new Galho(30));
        }
        return true;
    }

    public ArrayList<Galho> getGalhos() {
        return galhos;
    }
}
