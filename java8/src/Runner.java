import by.gsu.epamlab.ExtraTrial;
import by.gsu.epamlab.LightTrial;
import by.gsu.epamlab.StrongTrial;
import by.gsu.epamlab.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {

    public static void main(String[] args) {

        List<Trial> trials = new ArrayList<>(Arrays.asList(
                new Trial("Trial1", 45, 93),
                new Trial("Trial2", 51, 35),
                new Trial("Trial3", 55, 46),

                new LightTrial("LightTrial1", 76, 75),
                new LightTrial("LightTrial2", 65, 87),

                new StrongTrial("StrongTrial1", 85, 89),
                new StrongTrial("StrongTrial2", 89, 75),

                new ExtraTrial("ExtraTrial1", 46, 69, 78),
                new ExtraTrial("ExtraTrial2", 75, 77, 79)
        ));

        System.out.println(2);

        trials.forEach(System.out::println);


        System.out.println("\n" + 3);

        trials.stream()
                .filter(Trial::isPassed)
                .forEach(System.out::println);

        System.out.println("\n" + 4);

        trials.stream()
                .sorted((x, y) -> (x.getMark1() + x.getMark2()) - (y.getMark1() + y.getMark2()))
                .forEach(System.out::println);

        System.out.println("\n" + 5);

        trials.stream()
                .map((x) -> x.getMark2() + x.getMark1())
                .forEach(System.out::println);

        System.out.println("\n" + 6);

        List<Trial> faildTrials = trials.stream()
                .filter(t -> !t.isPassed())
                .map(Runner::clone)
                .collect(Collectors.toList());

        System.out.println("All trials are failed: "+trials.stream().noneMatch(Trial::isPassed));

        faildTrials.forEach(t -> {
            t.setMark1(0);
            t.setMark2(0);
            if (t.getClass() == ExtraTrial.class) {
                ((ExtraTrial) t).setMark3(0);
            }
        });

        faildTrials.forEach(System.out::println);

        System.out.println("\n" + 7);

        Integer[] sums = (Integer[]) trials.stream()
                .map(x -> x.getMark1() + x.getMark2())
                .toArray();

        Arrays.stream(sums)
                .map(l -> Integer.toString(l))
                .reduce((x, y) -> x + ", " + y)
                .ifPresent(System.out::println);
    }


    private static Trial clone(Trial t) {
        Trial trial = null;
        if (t.getClass() == Trial.class) {
            trial = new Trial(t.getName(), t.getMark1(), t.getMark2());
        } else if (t.getClass() == LightTrial.class) {
            trial = new LightTrial(t.getName(), t.getMark1(), t.getMark2());
        } else if (t.getClass() == StrongTrial.class) {
            trial = new StrongTrial(t.getName(), t.getMark1(), t.getMark2());
        } else if (t.getClass() == ExtraTrial.class) {
            trial = new ExtraTrial(t.getName(), t.getMark1(), t.getMark2(), ((ExtraTrial) t).getMark3());
        }
        return trial;
    }
}
