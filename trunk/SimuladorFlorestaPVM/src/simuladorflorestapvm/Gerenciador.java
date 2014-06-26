package simuladorflorestapvm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jpvm.jpvmBuffer;
import jpvm.jpvmEnvironment;
import jpvm.jpvmException;
import jpvm.jpvmMessage;
import jpvm.jpvmTaskId;
import simuladorflorestapvm.etapasCiclo.Adulta;
import simuladorflorestapvm.etapasCiclo.Broto;
import simuladorflorestapvm.etapasCiclo.Morte;
import simuladorflorestapvm.etapasCiclo.Semente;

public class Gerenciador {

    public static final int NUM_CLICOS_DIA = 50;
    private static final int ESCRAVOS_ARMAZEM = 4;
    public static Gerenciador instancia;
    private Terreno ter;
    private int larguraTerreno;
    private int comprimentoTerreno;
    private AtomicBoolean ambienteFinalizado;
    private jpvmEnvironment jpvm;
    private jpvmTaskId tids[];

    public static Gerenciador getinstancia() {
        if (instancia == null) {
            instancia = new Gerenciador();
        }
        return instancia;
    }

    private Gerenciador() {
        ambienteFinalizado = new AtomicBoolean(false);
    }

    public void Iniciar(
            int larguraTerreno,
            int comprimentoTerreno,
            int numArvores,
            int dias) {
        try {
            this.larguraTerreno = larguraTerreno;
            this.comprimentoTerreno = comprimentoTerreno;

            ter = Terreno.getInstancia();
            ter.Inicializa(larguraTerreno, comprimentoTerreno, ambienteFinalizado);

            for (int i = 0; i < numArvores; i++) {
                if (!ter.addArvore(new ArvorePauBrasil())) {
                    throw new Exception("Numero de arvores excede o limite permitido("
                            + ter.getNumMaxArvores()
                            + ") para o terreno");
                }
            }

            Lenhador lenhador1 = new Lenhador("João");
            Lenhador lenhador2 = new Lenhador("Paulo");
            Lenhador lenhador3 = new Lenhador("Ricardo");

            lenhador1.start();
            lenhador2.start();
            lenhador3.start();

            long tempoInicial = System.currentTimeMillis();
            for (int i = 0; i < dias; i++) {
                ProximoDia();
                System.out.println("Dia " + (i + 1));
            }
            lenhador1.setFinalizado(true);
            lenhador2.setFinalizado(true);
            lenhador3.setFinalizado(true);
            ter.setFinalizarProcesso(true);

            lenhador1.join();
            lenhador2.join();
            lenhador3.join();

            System.out.println("Tempo de execução: " + ((System.currentTimeMillis() - tempoInicial) / 1000.0) + " segundos");
            System.out.println("Processo finalizado.");
            System.out.println(ter.ImprimeDados());

            System.out.println(lenhador1.getDados());
            System.out.println(lenhador2.getDados());
            System.out.println(lenhador3.getDados());
            System.out.println("Número de árvores no terreno: " + ter.getNumArvores());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Vish5");
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (jpvmException ex) {
            JOptionPane.showMessageDialog(null, "Vish6");
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ProximoDia() throws Exception, jpvmException {

        AtomicInteger numArvoresProcessadas = new AtomicInteger(0);
        ambienteFinalizado.set(false);

        Ambiente amb1 = new Ambiente(ter, ambienteFinalizado, numArvoresProcessadas);
        Ambiente amb2 = new Ambiente(ter, ambienteFinalizado, numArvoresProcessadas);
        Ambiente amb3 = new Ambiente(ter, ambienteFinalizado, numArvoresProcessadas);
        Ambiente amb4 = new Ambiente(ter, ambienteFinalizado, numArvoresProcessadas);

        Fotossintese fot1 = new Fotossintese(ter);
        Fotossintese fot2 = new Fotossintese(ter);
        Fotossintese fot3 = new Fotossintese(ter);
        Fotossintese fot4 = new Fotossintese(ter);

        Armazem armMorte = null;
        Armazem armSemente = null;
        Armazem armBroto = null;
        Armazem armAdulta = null;

        amb1.start();
        amb2.start();
        amb3.start();
        amb4.start();

        fot1.start();
        fot2.start();
        fot3.start();
        fot4.start();

        amb1.join();
        amb2.join();
        amb3.join();
        amb4.join();

        fot1.join();
        fot2.join();
        fot3.join();
        fot4.join();

        this.ambienteFinalizado.set(false);
        jpvm = new jpvmEnvironment();
        tids = new jpvmTaskId[ESCRAVOS_ARMAZEM];
        //try {
        jpvm.pvm_spawn("atEscravos.carregaArmazem", ESCRAVOS_ARMAZEM, tids);
        jpvmBuffer buf = new jpvmBuffer();
        String arquivoSerializado = Dao.getInstancia().serialize(Terreno.getInstancia());
        buf.pack(arquivoSerializado);

        for (int i = 0; i < ESCRAVOS_ARMAZEM; i++) {
            jpvm.pvm_send(buf, tids[i], i);
        }
        for (int i = 0; i < ESCRAVOS_ARMAZEM; i++) {
            jpvmMessage message = jpvm.pvm_recv();

            switch (message.messageTag) {
                case 0:
                    armMorte = Dao.getInstancia().deserialize(message.buffer.upkstr(), Armazem.class);
                    break;
                case 1:
                    armSemente = Dao.getInstancia().deserialize(message.buffer.upkstr(), Armazem.class);
                    break;
                case 2:
                    armBroto = Dao.getInstancia().deserialize(message.buffer.upkstr(), Armazem.class);
                    break;
                case 3:
                    armAdulta = Dao.getInstancia().deserialize(message.buffer.upkstr(), Armazem.class);
                    break;
            }
        }
        jpvm.pvm_exit();

        System.out.println("Ate aqui blz11");
        
        jpvm = new jpvmEnvironment();
        tids = new jpvmTaskId[ESCRAVOS_ARMAZEM];

        jpvm.pvm_spawn("atEscravos.executarEtapa", ESCRAVOS_ARMAZEM, tids);
        buf = new jpvmBuffer();

        arquivoSerializado = Dao.getInstancia().serialize(armMorte);
        buf.pack(arquivoSerializado);
        jpvm.pvm_send(buf, tids[0], 0);

        arquivoSerializado = Dao.getInstancia().serialize(armSemente);
        buf.pack(arquivoSerializado);
        jpvm.pvm_send(buf, tids[1], 1);

        arquivoSerializado = Dao.getInstancia().serialize(armBroto);
        buf.pack(arquivoSerializado);
        jpvm.pvm_send(buf, tids[2], 2);

        arquivoSerializado = Dao.getInstancia().serialize(armAdulta);
        buf.pack(arquivoSerializado);
        jpvm.pvm_send(buf, tids[3], 3);

        for (int i = 0; i < ESCRAVOS_ARMAZEM; i++) {
            jpvmMessage message = jpvm.pvm_recv();

            ArrayList arvores = Dao.getInstancia().deserialize(message.buffer.upkstr(), ArrayList.class);
            if(arvores == null)
                System.out.println("aqui2 null");
            switch (message.messageTag) {
                case 0:
                    for (Object object : arvores) {
                        Terreno.getInstancia().killArvore((Arvore) object);
                    }
                    break;
                case 1:
                    for (Object object : arvores) {
                        Terreno.getInstancia().atualizaAtributos((Arvore) object);
                    }
                    break;
                case 2:
                    for (Object object : arvores) {
                        Terreno.getInstancia().atualizaAtributos((Arvore) object);
                    }
                    break;
                case 3:
                    for (Object object : arvores) {
                        Terreno.getInstancia().atualizaAtributos((Arvore) object);
                    }
                    break;
            }
        }

        jpvm.pvm_exit();
        

        System.out.println("Depois etapas");

        /*Armazem armMorte = new Armazem(ter.getArvoresEtapa());
         Armazem armSemente = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.SEMENTE));
         Armazem armBroto = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.BROTO));
         Armazem armAdulta = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.ADULTA));*/
        /*Morte morte1 = new Morte(armMorte);
         Morte morte2 = new Morte(armMorte);
         Morte morte3 = new Morte(armMorte);
            
         Semente semente1 = new Semente(armSemente);
         Semente semente2 = new Semente(armSemente);
         Semente semente3 = new Semente(armSemente);
            
         Broto broto1 = new Broto(armBroto);
         Broto broto2 = new Broto(armBroto);
         Broto broto3 = new Broto(armBroto);
            
         Adulta adulta1 = new Adulta(armAdulta);
         Adulta adulta2 = new Adulta(armAdulta);
         Adulta adulta3 = new Adulta(armAdulta);*/
    }

    public int getLarguraTerreno() {
        return larguraTerreno;
    }

    public int getComprimentoTerreno() {
        return comprimentoTerreno;
    }
}
