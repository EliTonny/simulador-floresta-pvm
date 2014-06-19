package simuladorflorestapvm;

import java.io.Serializable;

public class Posicao implements Serializable {

    private int X;
    private int Y;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Posicao(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public String toString() {
        return "Posicao X: " + Integer.toString(X) + " Posicao Y: " + Integer.toString(Y);
    }
}
