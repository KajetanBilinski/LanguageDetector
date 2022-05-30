import java.util.ArrayList;

public class Perceptron {
    public String language;
    public ArrayList<Double> weightVec = new ArrayList<>();

    Perceptron(String language) {
        this.language = language;
        for (int i = 97; i < 123; i++)
            weightVec.add(Math.random() > 0.5 ? 1.0 : 0);
    }

    public void train(ArrayList<Double> letterVec, int d) {
        double net = 0;
        for (int i = 0; i < letterVec.size(); i++) {
            net += weightVec.get(i) * letterVec.get(i);
        }
        net -= Main.theta;
        if (d == 1 && net < 0) //case when net < 0 || d=1
        {
            ArrayList<Double> tmpArr = new ArrayList<>(weightVec);
            weightVec.clear();
            for (int i = 0; i < tmpArr.size(); i++)
                weightVec.add(tmpArr.get(i) + (Main.alfa * 1) * letterVec.get(i));

            Main.theta = Main.theta - Main.alfa * 1;
        } else if (d == 0 && net >= 0) // case when net > 0 d=0
        {
            ArrayList<Double> tmpArr = new ArrayList<>(weightVec);
            weightVec.clear();
            for (int i = 0; i < tmpArr.size(); i++)
                weightVec.add(tmpArr.get(i) + (Main.alfa * -1) * letterVec.get(i));

            Main.theta = Main.theta - Main.alfa * -1;
        }
    }

    public int returnD(ArrayList<Double> letterVec) {
        double net = 0;
        for (int i = 0; i < letterVec.size(); i++) {
            net += weightVec.get(i) * letterVec.get(i);
        }
        net -= Main.theta;
        if (net >= 0)
            return 1;
        else
            return 0;
    }

}
