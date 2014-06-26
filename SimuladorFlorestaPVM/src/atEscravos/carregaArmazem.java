package atEscravos;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
            Terreno ter = Dao.getInstancia().deserialize(message.buffer.upkstr(), Terreno.class);

            try {

                switch (message.messageTag) {
                    case 0:
                        //Armazem morte
                        armazem = new Armazem(ter.getArvoresEtapa());
                        break;
                    case 1:
                        //Armazem semente
                        armazem = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.SEMENTE));
                        break;
                    case 2:
                        //Armazem broto
                        armazem = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.BROTO));
                        break;
                    case 3:
                        //Armazem adulta
                        armazem = new Armazem(ter.getArvoresEtapa(EnumEtapaProcesso.ADULTA));
                        break;
                }
                
                buf.pack(Dao.getInstancia().serialize(armazem));

                jpvm.pvm_send(buf, parent, message.messageTag);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Vish3");
                buf.pack("Erro");
                jpvm.pvm_send(buf, parent, message.messageTag);
            }

            jpvm.pvm_exit();
        } catch (jpvmException | UnsupportedEncodingException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Vish4");
            System.out.println(ex.getMessage());
        }
    }
}
