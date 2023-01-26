package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    private final Repeatability YEARLY_REPEATABILITY = Repeatability.YEARLY_TASK;
    public YearlyTask(String title, Type type, String description) {
        super(title, type, description);

    }

    public Repeatability getMONTHLY_REPEATABILITY() {
        return YEARLY_REPEATABILITY;
    }

    @Override
    public String toString() {
        return super.toString() +
                "repeatability=" + YEARLY_REPEATABILITY;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        if (localDate.isEqual(getDateTime().toLocalDate().plusYears(1)) || localDate.isEqual(localDate.now())){
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
        LocalDate localDate = getDateTime().toLocalDate().plusYears(1);
        System.out.println(localDate);
    }
}
