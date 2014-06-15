package simuladorflorestapvm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Dao {

    private static Dao instancia;

    private Dao() {
    }

    public static Dao getInstancia() {
        if (instancia == null) {
            instancia = new Dao();
        }
        return instancia;
    }

    public void Persiste(File arquivo, Object obj) throws FileNotFoundException, IOException {
        FileOutputStream fileOutput = new FileOutputStream(arquivo);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
        ObjectOutputStream objectOutput = new ObjectOutputStream(bufferedOutput);
        objectOutput.writeObject(obj);
        objectOutput.flush();
    }

    public Object Carrega(File arquivo) {
        if (arquivo.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(arquivo);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); //abre
                ObjectInputStream objetos = new ObjectInputStream(bufferedInputStream);
                Object obj = objetos.readObject();
                return obj;
            } catch (Exception ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
