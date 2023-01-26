package task;

import java.time.LocalDate;

public class DailyTask extends Task{
    private final Repeatability DAILY_REPEATABILITY = Repeatability.DAILY_TASK;
    public DailyTask(String title, Type type, String description) {
        super(title, type, description);

    }

    public Repeatability getMONTHLY_REPEATABILITY() {
        return DAILY_REPEATABILITY;
    }

    @Override
    public String toString() {
        return super.toString() +
                "repeatability=" + DAILY_REPEATABILITY;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDateTime().toLocalDate()) || localDate.isEqual(getDateTime().toLocalDate());
    }

    @Override
    public void printDateOfRepeat(){
        LocalDate localDate = getDateTime().toLocalDate().plusDays(1);
        System.out.println(localDate);
    }
}
