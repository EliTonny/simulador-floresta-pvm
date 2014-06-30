package atEscravos;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jpvm.jpvmBuffer;
import jpvm.jpvmEnvironment;
import jpvm.jpvmException;
import jpvm.jpvmMessage;
import jpvm.jpvmTaskId;
import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Dao;
import simuladorflorestapvm.etapasCiclo.Adulta;
import simuladorflorestapvm.etapasCiclo.Broto;
import simuladorflorestapvm.etapasCiclo.Morte;
import simuladorflorestapvm.etapasCiclo.Semente;

/**
 *
 * @author Eli
 */
public class executarEtapa {

    public static void main(String[] args) throws IOException {
        jpvmBuffer buf = new jpvmBuffer();
        try {
            jpvmEnvironment jpvm = new jpvmEnvironment();
            jpvmMessage message = jpvm.pvm_recv();
            jpvmTaskId parent = jpvm.pvm_parent();
            try {
                Armazem armazem = Dao.getInstancia().deserialize(message.buffer.upkstr(), Armazem.class);
                ArrayList arvores = null;

                switch (message.messageTag) {
                    case 0:
                        Morte morte = new Morte(armazem);
                        arvores = morte.run();
                        break;

                    case 1:
                        Semente semente = new Semente(armazem);
                        arvores = semente.run();
                        break;

                    case 2:
                        Broto broto = new Broto(armazem);
                        arvores = broto.run();
                        break;

                    case 3:
                        Adulta adulta = new Adulta(armazem);
                        arvores = adulta.run();
                        break;
                }
                
                buf.pack(Dao.getInstancia().serialize(arvores));
                jpvm.pvm_send(buf, parent, message.messageTag);

            } catch (Exception ex) {
                buf.pack(ex.getMessage());
                jpvm.pvm_send(buf, parent, 99);
            }

            jpvm.pvm_exit();

        } catch (jpvmException ex) {
            JOptionPane.showMessageDialog(null, "Erro no escravo etapa: \r\n" + ex.getMessage());
        }
    }
}
