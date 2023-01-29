package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    private final Repeatability YEARLY_REPEATABILITY = Repeatability.YEARLY_TASK;
    public YearlyTask(String title, Type type, String description) {
        super(title, type, description);

    }

    @Override
    public String toString() {
        return super.toString() +
                "repeatability=" + YEARLY_REPEATABILITY;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        if (localDate.isAfter(getDateTime().toLocalDate()) || localDate.isEqual(getDateTime().toLocalDate()) && localDate.getDayOfYear() == getDateTime().getDayOfYear()){
            return true;
        } else {
            return false;
        }
    }
}
