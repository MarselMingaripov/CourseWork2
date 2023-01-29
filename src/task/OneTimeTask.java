package task;

import java.time.LocalDate;

public class OneTimeTask extends Task{
    public OneTimeTask(String title, Type type, String description) {
        super(title, type, description);

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.equals(getDateTime().toLocalDate());
    }
}
