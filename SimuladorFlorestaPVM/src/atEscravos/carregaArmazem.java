package atEscravos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import jpvm.jpvmBuffer;
import jpvm.jpvmEnvironment;
import jpvm.jpvmException;
import jpvm.jpvmMessage;
import jpvm.jpvmTaskId;
import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Dao;
import simuladorflorestapvm.EnumEtapaProcesso;
import simuladorflorestapvm.Terreno;

public class carregaArmazem {

    public static void main(String args[]) throws IOException {
        try {
            jpvmEnvironment jpvm = new jpvmEnvironment();
            jpvmMessage message = jpvm.pvm_recv();
            jpvmTaskId parent = jpvm.pvm_parent();
            jpvmBuffer buf = new jpvmBuffer();
            Armazem armazem = null;
            Terreno ter = null;
            File arquivo = new File("c:/temp/terrenoe_" + message.messageTag + ".txt");
            File arquivoRet = new File("c:/temp/armazeme_" + message.messageTag + ".txt");
            String arquivoSerializado = "";

            try {

                FileWriter escritorArquivo = new FileWriter(arquivo);
                BufferedWriter escritor = new BufferedWriter(escritorArquivo);
                escritor.write(message.buffer.upkstr());
                escritor.close();

                Object obj = Dao.getInstancia().Carrega(arquivo);
                if (obj instanceof Terreno) {
                    ter = (Terreno) obj;
                }

                switch (message.messageTag) {
                    case 1:
                        //Armazem morte
                        armazem = new Armazem(ter.getArvoresEtapa());
                        break;
                    case 2:
                        //Armazem semente
                        armazem = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.SEMENTE));
                        break;
                    case 3:
                        //Armazem broto
                        armazem = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.BROTO));
                        break;
                    case 4:
                        //Armazem adulta
                        armazem = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.ADULTA));
                        break;
                }

                Dao.getInstancia().Persiste(arquivoRet, armazem);
                BufferedReader leitor = new BufferedReader(new FileReader(arquivoRet.getAbsolutePath()));
                while (leitor.ready()) {
                    arquivoSerializado += leitor.readLine();
                }
                leitor.close();

                buf.pack(arquivoSerializado);

                jpvm.pvm_send(buf, parent, message.messageTag);
            } catch (Exception ex) {
                buf.pack("Erro");
                jpvm.pvm_send(buf, parent, message.messageTag);
            }

            jpvm.pvm_exit();
        } catch (jpvmException jpe) {
            System.out.println("Error - jpvm exception");
        }
    }
}
