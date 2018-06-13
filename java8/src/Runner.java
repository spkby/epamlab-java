import by.gsu.epamlab.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {

    public static void main(String[] args) {

        // 1
        List<Trial> trials = new ArrayList<>(Arrays.asList(new Trial("Trial1", 45, 93),
                new Trial("Trial2", 51, 35),
                new Trial("Trial3", 55, 46),

                new LightTrial("LightTrial1", 76, 75),
                new LightTrial("LightTrial2", 65, 87),

                new StrongTrial("StrongTrial1", 85, 89),
                new StrongTrial("StrongTrial2", 89, 75),

                new ExtraTrial("ExtraTrial1", 46, 69, 78),
                new ExtraTrial("ExtraTrial2", 75, 77, 79)));

        // 2
        System.out.println(2);

        trials.forEach(System.out::println);

        // 3
        System.out.println("\n" + 3);

        System.out.println(trials.stream()
                .filter(Trial::isPassed)
                .count());

        // 4
        trials = trials.stream()
                .sorted((x, y) -> (x.getMark1() + x.getMark2()) - (y.getMark1() + y.getMark2()))
                .collect(Collectors.toList());

        // 5
        System.out.println("\n" + 5);

        trials.stream()
                .map((x) -> x.getMark2() + x.getMark1())
                .forEach(System.out::println);

        // 6
        System.out.println("\n" + 6);

        List<Trial> faildTrials = trials.stream()
                .filter(t -> !t.isPassed())
                .map(TrialsFactory::getTrialFromFactory)
                .collect(Collectors.toList());

        System.out.println("All trials are failed: " + trials.stream().noneMatch(Trial::isPassed));

        faildTrials.forEach(t -> {
            t.setMark1(0);
            t.setMark2(0);
            if (t instanceof ExtraTrial) {
                ((ExtraTrial) t).setMark3(0);
            }
        });

        faildTrials.forEach(System.out::println);

        // 7
        System.out.println("\n" + 7);

        int[] sums = trials.stream()
                .mapToInt(x -> x.getMark1() + x.getMark2())
                .toArray();

        Arrays.stream(sums)
                .mapToObj(Integer::toString)
                .reduce((x, y) -> x + ", " + y)
                .ifPresent(System.out::println);
    }
}
