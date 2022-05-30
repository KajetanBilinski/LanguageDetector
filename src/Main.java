import java.io.*;
import java.util.*;

public class Main {

    public static double alfa = 0.5;
    public static double theta = 5;

    public static void main(String[] args)
    {
        ArrayList<Perceptron> perceptrons = new ArrayList<>();
        perceptrons.add(new Perceptron("German"));
        perceptrons.add(new Perceptron("Polish"));
        perceptrons.add(new Perceptron("English"));
        ArrayList<String> texts1 = getTexts("German", "lang");
        ArrayList<String> texts2 = getTexts("Polish", "lang");
        ArrayList<String> texts3 = getTexts("English", "lang");
        ArrayList<ArrayList<Double>> doubles1 = new ArrayList<>();
        ArrayList<ArrayList<Double>> doubles2 = new ArrayList<>();
        ArrayList<ArrayList<Double>> doubles3 = new ArrayList<>();

        for (String s : texts1) {
            doubles1.add(new ArrayList<>(countLetters(s)));
        }
        for (String s : texts2) {
            doubles2.add(new ArrayList<>(countLetters(s)));
        }
        for (String s : texts3) {
            doubles3.add(new ArrayList<>(countLetters(s)));
        }

        for (int i = 0; i < 100; i++)
        {
            for (ArrayList<Double> doubles : doubles3) {
                perceptrons.get(0).train(doubles, 0);
                perceptrons.get(1).train(doubles, 0);
                perceptrons.get(2).train(doubles, 1);
            }
            for (ArrayList<Double> doubles : doubles1) {
                perceptrons.get(0).train(doubles, 1);
                perceptrons.get(1).train(doubles, 0);
                perceptrons.get(2).train(doubles, 0);
            }

            for (ArrayList<Double> doubles : doubles2) {
                perceptrons.get(0).train(doubles, 0);
                perceptrons.get(1).train(doubles, 1);
                perceptrons.get(2).train(doubles, 0);
            }

        }
        ArrayList<String> t1 = getTexts("German", "test");
        ArrayList<String> t2 = getTexts("Polish", "test");
        ArrayList<String> t3 = getTexts("English", "test");
        System.out.println(perceptrons.get(0).weightVec);
        System.out.println(perceptrons.get(1).weightVec);
        System.out.println(perceptrons.get(2).weightVec);
        check(perceptrons, t1, "German");
        check(perceptrons, t2, "Polish");
        check(perceptrons, t3, "English");
    }

    private static void check(ArrayList<Perceptron> perceptrons, ArrayList<String> t1, String lang) {
        for (String s : t1) {
            ArrayList<Double> doubles = countLetters(s);
            System.out.println(lang);
            for (Perceptron perceptron : perceptrons) {
                System.out.println(perceptron.returnD(doubles) + " " + perceptron.language);
            }
        }
    }

    public static ArrayList<String> getTexts(String language, String test) {
        File file = new File("src/" + test + "/" + language);
        File[] files = file.listFiles();
        ArrayList<String> filesText = new ArrayList<>();
        try {
            if (files == null)
                throw new IllegalArgumentException();
            for (File value : files) {
                FileReader fr = new FileReader(value);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line);
                filesText.add(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesText;
    }

    public static ArrayList<Double> countLetters(String text) {
        ArrayList<Double> letters = new ArrayList<>();
        text = text.toLowerCase();
        double suma = 0;
        for (int i = 97; i < 123; i++) {
            double a = (text.length() - (text.replaceAll(String.valueOf((char) (i)), "").length()));
            suma += Math.pow(a, 2);
            letters.add(a);
        }
        double result = Math.sqrt(suma);
        for (int i = 0; i < letters.size(); i++)
            letters.set(i, letters.get(i) / result);
        return letters;
    }
}
