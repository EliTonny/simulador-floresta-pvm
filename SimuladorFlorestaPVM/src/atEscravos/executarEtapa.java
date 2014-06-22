package atEscravos;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import jpvm.jpvmBuffer;
import jpvm.jpvmEnvironment;
import jpvm.jpvmException;
import jpvm.jpvmMessage;
import jpvm.jpvmTaskId;
import simuladorflorestapvm.Armazem;
import simuladorflorestapvm.Dao;
import simuladorflorestapvm.Terreno;
import simuladorflorestapvm.etapasCiclo.Morte;

/**
 *
 * @author Eli
 */
public class executarEtapa {

    public static void main(String[] args) throws IOException {
        try {
            jpvmEnvironment jpvm = new jpvmEnvironment();
            jpvmMessage message = jpvm.pvm_recv();
            jpvmTaskId parent = jpvm.pvm_parent();
            jpvmBuffer buf = new jpvmBuffer();
            Armazem armazem = Dao.getInstancia().deserialize(message.buffer.upkstr(), Armazem.class);
            ArrayList arvores = null;

            switch (message.messageTag) {
                case 0:
                    Morte morte = new Morte(armazem);
                    arvores = morte.run();
                    break;

                case 1:
                    // to do
                    break;

                case 2:
                    // to do
                    break;

                case 3:
                    // to do
                    break;
            }

            buf.pack(Dao.getInstancia().serialize(arvores));
            jpvm.pvm_send(buf, parent, message.messageTag);
            
            jpvm.pvm_exit();

        } catch (jpvmException | UnsupportedEncodingException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
