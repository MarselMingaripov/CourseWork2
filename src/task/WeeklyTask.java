package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    private final Repeatability WEEKLY_REPEATABILITY = Repeatability.WEEKLY_TASK;
    public WeeklyTask(String title, Type type, String description) {
        super(title, type, description);
    }

    public Repeatability getRepeatability() {
        return WEEKLY_REPEATABILITY;
    }

    @Override
    public void printDateOfRepeat(){
        LocalDate localDate = getDateTime().toLocalDate().plusDays(7);
        System.out.println(localDate);
    }

    @Override
    public String toString() {
        return super.toString() +
                "repeatability=" + WEEKLY_REPEATABILITY;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        if (localDate.isEqual(getDateTime().toLocalDate().plusDays(7)) || localDate.isEqual(localDate.now())){
            if (localDate.isEqual(localDate.now())) {
                setDateTime(LocalDateTime.now());
            }
            return true;
        } else {
            return false;
        }
    }
}
