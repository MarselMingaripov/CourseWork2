package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task implements RepeatIn{
    public static int idGenerator = 1;

    private int id;
    private String title;
    private Type type;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title, Type type, String description) {
        this.id = idGenerator;
        this.title = title;
        this.type = type;
        this.dateTime = LocalDateTime.now();
        this.description = description;
        idGenerator++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime.now();
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && type == task.type && Objects.equals(dateTime, task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, dateTime, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", dateTime=" + dateTime.format(DateTimeFormatter.ofPattern("YYYY/MM/DD HH:mm:ss")) +
                ", description='" + description + '\'' +
                '}';
    }


    public abstract boolean appearsIn(LocalDate localDate);
    public abstract void printDateOfRepeat();
}
