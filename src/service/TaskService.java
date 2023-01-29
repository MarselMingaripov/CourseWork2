package service;

import exeptions.TaskNotFoundException;
import task.OneTimeTask;
import task.Task;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Set<Task> removedTask = new HashSet<>();

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task remove(int id) throws TaskNotFoundException {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Задача не найдена!");
        }
        removedTask.add(taskMap.get(id));
        return taskMap.remove(id);
    }

    public List<Task> getAllByDate(LocalDate localDate) throws TaskNotFoundException {
        List<Task> taskList = new ArrayList<>();
        for (Task task : taskMap.values()) {
            if (task.appearsIn(localDate)) {
                taskList.add(task);
            }
        }
        if (taskList.isEmpty()) {
            throw new TaskNotFoundException("Задачи по дате не найдены!");
        }
        return taskList;
    }

    public Map<LocalDate, ArrayList<Task>> getAllGroupedByDate() throws TaskNotFoundException {
        Map<LocalDate, ArrayList<Task>> localDateArrayListMap = new HashMap<>();
        if (!taskMap.isEmpty()) {
            for (Task value : taskMap.values()) {
                localDateArrayListMap.computeIfAbsent(value.getDateTime().toLocalDate(), k -> new ArrayList<>()).
                        add(value);
            }
        } else {
            throw new TaskNotFoundException("Задачи не найдены!");
        }
        return localDateArrayListMap;
    }

    public void printAllTasks() throws TaskNotFoundException {
        if (!taskMap.isEmpty()) {
            for (Task task : taskMap.values()) {
                System.out.println(task.toString());
            }
        } else {
            throw new TaskNotFoundException("Нет созданных задач!");
        }
    }

    public void updateTask(int id, Task task) throws TaskNotFoundException {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Задача не найдена");
        }
        taskMap.replace(id, task);
        taskMap.get(id).setId(id);
    }

    public void findById(int id) throws TaskNotFoundException {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Задача не найдена");
        }
    }

    public void printRemovedTasks() throws TaskNotFoundException {
        if (!removedTask.isEmpty()) {
            for (Task task : removedTask) {
                System.out.println(task.toString());
            }
        } else {
            throw new TaskNotFoundException("Архив пуст!");
        }
    }
}
