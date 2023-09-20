package com;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Outcome {
    private Object value;
    private int probability;

    public Outcome(Object value, int probability) {
        this.value = value;
        this.probability = probability;
    }

    public Object getValue() {
        return value;
    }

    public int getProbability() {
        return probability;
    }
}

public class ProbablitySimulator {
    public static void main(String[] args) {
        // Rolling of a six-faced biased dice
        List<Outcome> diceOutcomes = new ArrayList<>();
        diceOutcomes.add(new Outcome(1, 10));
        diceOutcomes.add(new Outcome(2, 30));
        diceOutcomes.add(new Outcome(3, 15));
        diceOutcomes.add(new Outcome(4, 15));
        diceOutcomes.add(new Outcome(5, 30));
        diceOutcomes.add(new Outcome(6, 0));

        ProbablitySimulation diceSimulator = new ProbablitySimulation(diceOutcomes);

     // Simulation and counting occurrences
        int numSimulations = 1000;
        int[] diceCounts = new int[6]; // Array to store the counts for each face

        for (int i = 0; i < numSimulations; i++) {
            Outcome outcome = diceSimulator.simulateEvent();
            int faceValue = (int) outcome.getValue();
            diceCounts[faceValue - 1]++; // Decrement by 1 to match array indexing
        }

        // Output the counts for each face
        for (int i = 0; i < 6; i++) {
            int face = i + 1; // Increment by 1 to match face value
            System.out.println("Face " + face + " appeared " + diceCounts[i] + " times.");
        }

        // Flipping of a coin
        List<Outcome> coinOutcomes = new ArrayList<>();
        coinOutcomes.add(new Outcome("Head", 35));
        coinOutcomes.add(new Outcome("Tail", 65));

        ProbablitySimulation coinSimulator = new ProbablitySimulation(coinOutcomes);

        // Simulation and counting occurrences
        //int numSimulations = 1000;
        int headCount = 0;
        int tailCount = 0;

        for (int i = 0; i < numSimulations; i++) {
            Outcome outcome = coinSimulator.simulateEvent();
            if (outcome.getValue().equals("Head")) {
                headCount++;
            } else if (outcome.getValue().equals("Tail")) {
                tailCount++;
            }
        }

        System.out.println("Head appeared " + headCount + " times.");
        System.out.println("Tail appeared " + tailCount + " times.");
    }
}
