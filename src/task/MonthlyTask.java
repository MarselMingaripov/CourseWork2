package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    private final Repeatability MONTHLY_REPEATABILITY = Repeatability.MONTHLY_TASK;
    public MonthlyTask(String title, Type type, String description) {
        super(title, type, description);

    }


    @Override
    public String toString() {
        return super.toString() +
                "repeatability=" + MONTHLY_REPEATABILITY;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        if (localDate.isAfter(getDateTime().toLocalDate()) || localDate.isEqual(getDateTime().toLocalDate()) && localDate.getDayOfMonth() == getDateTime().getDayOfMonth()){
            return true;
        } else {
            return false;
        }
    }
}
