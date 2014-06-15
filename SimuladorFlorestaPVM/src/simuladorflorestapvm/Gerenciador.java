package simuladorflorestapvm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    AtomicBoolean ambienteFinalizado;

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
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void ProximoDia() throws InterruptedException, FileNotFoundException, IOException {

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

        try {
            jpvmEnvironment jpvm = new jpvmEnvironment();
            jpvmTaskId tids[] = new jpvmTaskId[ESCRAVOS_ARMAZEM];
            jpvm.pvm_spawn("atEscravos.carregaArmazem", ESCRAVOS_ARMAZEM, tids);
            File arquivo = new File("c:/temp/terreno.txt");
            BufferedReader leitor = new BufferedReader(new FileReader(arquivo.getAbsolutePath()));
            jpvmBuffer buf = new jpvmBuffer();
            String arquivoSerializado = "";

            try {
                while (leitor.ready()) {
                    arquivoSerializado += leitor.readLine();
                }
                leitor.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            buf.pack(arquivoSerializado);

            for (int i = 0; i < ESCRAVOS_ARMAZEM; i++) {
                jpvm.pvm_send(buf, tids[i], i);
            }

            for (int i = 0; i < ESCRAVOS_ARMAZEM; i++) {
                jpvmMessage message = jpvm.pvm_recv();

                File arquivoArm = new File("c:/temp/armazemm_" + message.messageTag + ".txt");

                FileWriter escritorArquivo = new FileWriter(arquivoArm);
                BufferedWriter escritor = new BufferedWriter(escritorArquivo);
                escritor.write(message.buffer.upkstr());
                escritor.close();

                switch (message.messageTag) {
                    case 1:
                        armMorte = (Armazem) Dao.getInstancia().Carrega(arquivoArm);
                        break;
                    case 2:
                        armSemente = (Armazem) Dao.getInstancia().Carrega(arquivoArm);
                        break;
                    case 3:
                        armBroto = (Armazem) Dao.getInstancia().Carrega(arquivoArm);
                        break;
                    case 4:
                        armAdulta = (Armazem) Dao.getInstancia().Carrega(arquivoArm);
                        break;
                }
            }

            jpvm.pvm_exit();

        } catch (jpvmException ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Terminou PVM");
        Thread.sleep(10000000);

        /*Armazem armMorte = new Armazem(ter.getArvoresEtapa());
         Armazem armSemente = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.SEMENTE));
         Armazem armBroto = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.BROTO));
         Armazem armAdulta = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.ADULTA));*/
        Morte morte1 = new Morte(armMorte);
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
        Adulta adulta3 = new Adulta(armAdulta);

        morte1.start();
        morte2.start();
        morte3.start();

        semente1.start();
        semente2.start();
        semente3.start();

        broto1.start();
        broto2.start();
        broto3.start();

        adulta1.start();
        adulta2.start();
        adulta3.start();

        morte1.join();
        morte2.join();
        morte3.join();

        semente1.join();
        semente2.join();
        semente3.join();

        broto1.join();
        broto2.join();
        broto3.join();

        adulta1.join();
        adulta2.join();
        adulta3.join();
    }

    public int getLarguraTerreno() {
        return larguraTerreno;
    }

    public int getComprimentoTerreno() {
        return comprimentoTerreno;
    }
}
