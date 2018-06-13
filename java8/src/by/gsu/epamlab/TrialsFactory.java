package by.gsu.epamlab;

public class TrialsFactory {

    public static Trial getTrialFromFactory(Trial t) {
        Trial trial = null;

        if (t != null) {
            if (t.getClass() == ExtraTrial.class) {
                trial = new ExtraTrial(t.getName(), t.getMark1(), t.getMark2(), ((ExtraTrial) t).getMark3());
            } else if (t.getClass() == LightTrial.class) {
                trial = new LightTrial(t.getName(), t.getMark1(), t.getMark2());
            } else if (t.getClass() == StrongTrial.class) {
                trial = new StrongTrial(t.getName(), t.getMark1(), t.getMark2());
            } else {
                trial = new Trial(t.getName(), t.getMark1(), t.getMark2());
            }
        }
        return trial;
    }
}
