package com;

import java.util.List;
import java.util.Random;


public class ProbablitySimulation {
    private List<Outcome> outcomes;
    private Random random;

    public ProbablitySimulation(List<Outcome> outcomes) {
        this.outcomes = outcomes;
        this.random = new Random();
    }

    public Outcome simulateEvent() {
        int totalProbability = outcomes.stream().mapToInt(Outcome::getProbability).sum();
        int randomValue = random.nextInt(totalProbability);

        int cumulativeProbability = 0;
        for (Outcome outcome : outcomes) {
            cumulativeProbability += outcome.getProbability();
            if (randomValue < cumulativeProbability) {
                return outcome;
            }
        }

        // In case the probabilities do not add up to 100, return the last outcome
        return outcomes.get(outcomes.size() - 1);
    }
}

