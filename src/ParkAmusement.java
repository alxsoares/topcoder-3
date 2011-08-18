import java.util.ArrayList;
import java.util.HashMap;

public class ParkAmusement {
    private class Node {
        public int i;
        public boolean exit;
        public boolean pond;
        public ArrayList<Node> succ = new ArrayList<Node>();
        public ArrayList<Node> pred = new ArrayList<Node>();
        public HashMap<Integer, Double> proba = new HashMap<Integer, Double>();
    }

    private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
    private ArrayList<Node> exits = new ArrayList<Node>();

    public double getProbability(String[] landings, int startLanding, int K) {
        for (int i = 0; i < landings.length; i++) {
            Node n = new Node();
            n.i = i;
            nodes.put(i, n);
        }
        dump();
        for (int i = 0; i < landings.length; i++) {
            String s = landings[i];
            Node n = nodes.get(i);
            n.exit = (s.charAt(i) == 'E');
            n.pond = (s.charAt(i) == 'P');
            for (int j = 0; j < landings.length; j++) {
                if (s.charAt(j) == '1') {
                    n.succ.add(nodes.get(j));
                    nodes.get(j).pred.add(n);
                }
            }
            if (n.exit) {
                exits.add(n);
            }
        }

        for (Node n : nodes.values()) {
            double proba;
            if (n.exit) {
                proba = 1;
            } else {
                proba = 0;
            }
            n.proba.put(0, proba);
        }
        System.out.println("***k=0");
        dump();
        for (int k = 1; k <= K; k++) {
            for (Node n : nodes.values()) {
                double proba = 0;
                for (Node s : n.succ) {
                    proba += s.proba.get(k - 1) / n.succ.size();
                }
                n.proba.put(k, proba);
            }
            System.out.println("***k=" + k);
            dump();
        }

        double sumproba = 0;
        for (Node n : nodes.values()) {
            sumproba += n.proba.get(K);
        }
        return nodes.get(startLanding).proba.get(K) / sumproba;
    }

    private void dump() {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            System.out.print(node.i);
            if (node.exit) {
                System.out.print("E");
            }
            if (node.pond) {
                System.out.print("P");
            }
            System.out.println(": proba=" + node.proba);
        }
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 50; i++) {
            System.out.print("\"");
            for (int j = 0; j < 50; j++) {
                if (j > i) {
                    System.out.print("1");
                } else if (j == 49 && i == 49) {
                    System.out.print("E");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println("\",");
        }       */

        ParkAmusement t = new ParkAmusement();
        System.out.println(t.getProbability(new String[]
                {"01111111111111111111111111111111111111111111111111",
"00111111111111111111111111111111111111111111111111",
"00011111111111111111111111111111111111111111111111",
"00001111111111111111111111111111111111111111111111",
"00000111111111111111111111111111111111111111111111",
"00000011111111111111111111111111111111111111111111",
"00000001111111111111111111111111111111111111111111",
"00000000111111111111111111111111111111111111111111",
"00000000011111111111111111111111111111111111111111",
"00000000001111111111111111111111111111111111111111",
"00000000000111111111111111111111111111111111111111",
"00000000000011111111111111111111111111111111111111",
"00000000000001111111111111111111111111111111111111",
"00000000000000111111111111111111111111111111111111",
"00000000000000011111111111111111111111111111111111",
"00000000000000001111111111111111111111111111111111",
"00000000000000000111111111111111111111111111111111",
"00000000000000000011111111111111111111111111111111",
"00000000000000000001111111111111111111111111111111",
"00000000000000000000111111111111111111111111111111",
"00000000000000000000011111111111111111111111111111",
"00000000000000000000001111111111111111111111111111",
"00000000000000000000000111111111111111111111111111",
"00000000000000000000000011111111111111111111111111",
"00000000000000000000000001111111111111111111111111",
"00000000000000000000000000111111111111111111111111",
"00000000000000000000000000011111111111111111111111",
"00000000000000000000000000001111111111111111111111",
"00000000000000000000000000000111111111111111111111",
"00000000000000000000000000000011111111111111111111",
"00000000000000000000000000000001111111111111111111",
"00000000000000000000000000000000111111111111111111",
"00000000000000000000000000000000011111111111111111",
"00000000000000000000000000000000001111111111111111",
"00000000000000000000000000000000000111111111111111",
"00000000000000000000000000000000000011111111111111",
"00000000000000000000000000000000000001111111111111",
"00000000000000000000000000000000000000111111111111",
"00000000000000000000000000000000000000011111111111",
"00000000000000000000000000000000000000001111111111",
"00000000000000000000000000000000000000000111111111",
"00000000000000000000000000000000000000000011111111",
"00000000000000000000000000000000000000000001111111",
"00000000000000000000000000000000000000000000111111",
"00000000000000000000000000000000000000000000011111",
"00000000000000000000000000000000000000000000001111",
"00000000000000000000000000000000000000000000000111",
"00000000000000000000000000000000000000000000000011",
"00000000000000000000000000000000000000000000000001",
"0000000000000000000000000000000000000000000000000E"}, 0, 49));
    }
}
