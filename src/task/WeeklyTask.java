package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    private final Repeatability WEEKLY_REPEATABILITY = Repeatability.WEEKLY_TASK;
    public WeeklyTask(String title, Type type, String description) {
        super(title, type, description);
    }


    @Override
    public String toString() {
        return super.toString() +
                "repeatability=" + WEEKLY_REPEATABILITY;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        if (localDate.isAfter(getDateTime().toLocalDate()) || localDate.isEqual(getDateTime().toLocalDate()) && localDate.getDayOfWeek() == getDateTime().getDayOfWeek()){
            return true;
        } else {
            return false;
        }
    }
}
