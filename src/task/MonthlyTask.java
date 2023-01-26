package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    private final Repeatability MONTHLY_REPEATABILITY = Repeatability.MONTHLY_TASK;
    public MonthlyTask(String title, Type type, String description) {
        super(title, type, description);

    }

    public Repeatability getMONTHLY_REPEATABILITY() {
        return MONTHLY_REPEATABILITY;
    }


    @Override
    public String toString() {
        return super.toString() +
                "repeatability=" + MONTHLY_REPEATABILITY;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        if (localDate.isEqual(getDateTime().toLocalDate().plusMonths(1)) || localDate.isEqual(localDate.now())){
            if (localDate.isEqual(localDate.now())) {
                setDateTime(LocalDateTime.now());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printDateOfRepeat(){
        LocalDate localDate = getDateTime().toLocalDate().plusMonths(1);
        System.out.println(localDate);
    }
}
