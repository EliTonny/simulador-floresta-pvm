package simuladorflorestapvm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

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
    /**
     * Serialize any object
     *
     * @param obj
     * @return
     */
    public String serialize(Object obj) throws IOException {

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = new ObjectOutputStream(bo);
        so.writeObject(obj);
        so.flush();
        // This encoding induces a bijection between byte[] and String (unlike UTF-8)
        return bo.toString("ISO-8859-1");
    }

    /**
     * Deserialize any object
     *
     * @param str
     * @param cls
     * @return
     */
    public <T> T deserialize(String str, Class<T> cls) throws UnsupportedEncodingException, IOException, ClassNotFoundException {
        // deserialize the object

        // This encoding induces a bijection between byte[] and String (unlike UTF-8)
        byte b[] = str.getBytes("ISO-8859-1");
        ByteArrayInputStream bi = new ByteArrayInputStream(b);
        ObjectInputStream si = new ObjectInputStream(bi);
        return cls.cast(si.readObject());
    }
}
