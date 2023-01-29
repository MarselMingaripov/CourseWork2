import exeptions.IncorrectArgumentException;
import exeptions.IncorrectMenuArgumentException;
import exeptions.TaskNotFoundException;
import service.TaskService;
import task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private static final String DATE_FORMATTER = "yyyy/MM/dd";

    private final TaskService taskService = new TaskService();

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printMenu() {
        System.out.println("Выберите значение, введите, нажмите Enter");
        System.out.println("1: Создать задачу");
        System.out.println("2: Показать все задачи");
        System.out.println("3: Показать задачи по датам");
        System.out.println("4: Смотреть архив удаленных задач");
        System.out.println("5: Сгруппировать по датам");
        System.out.println("6: Выход из программы");
    }

    public void printMessageMenu() {
        System.out.println("Выберите значение, введите, нажмите Enter");
        System.out.println("1: Удалить");
        System.out.println("2: Изменить");
        System.out.println("3: Выход в меню");
    }

    public void start() {
        if (this.scanner != null) {
            int key = 0;
            do {
                printMenu();
                String str = this.scanner.nextLine();
                if (!isDigit(str)){
                    try {
                        throw new IncorrectMenuArgumentException("Введите нормальное число!");
                    } catch (IncorrectMenuArgumentException e) {
                        e.printStackTrace();
                        start();
                    }
                } else {
                    key = Integer.parseInt(str);
                }
                switch (key) {
                    case 1: {
                        try {
                            taskService.add(printCreateNewTask());
                        } catch (IncorrectArgumentException e) {
                            e.printStackTrace();
                            break;
                        }
                        break;
                    }
                    case 2: {
                        try {
                            taskService.printAllTasks();
                        } catch (TaskNotFoundException e) {
                            e.printStackTrace();
                            break;
                        }
                        int key1 = 0;
                        do {
                            printMessageMenu();
                            String str1 = this.scanner.nextLine();
                            if (!isDigit(str1)){
                                try {
                                    throw new IncorrectMenuArgumentException("Введите нормальное число!");
                                } catch (IncorrectMenuArgumentException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                key1 = Integer.parseInt(str1);
                            }
                            switch (key1) {
                                case 1 -> {
                                    System.out.println("Введите id задачи для удаления");
                                    removeTaskPartOfMenu();
                                    break;
                                }
                                case 2 -> {
                                    System.out.println("Введите id задачи для изменения");
                                    updateTaskPartOfMenu();
                                    break;
                                }
                                case 3 -> {
                                    System.out.println("Выход в меню...");
                                    break;
                                }
                                default -> {
                                    System.out.println("Введите правильный номер меню\n");
                                }
                            }
                        }
                        while (key1 != 3);
                        break;
                    }
                    case 3: {
                            try {
                                taskService.getAllByDate(enterDate());
                            } catch (TaskNotFoundException | IncorrectArgumentException e) {
                                e.printStackTrace();
                                break;
                            }

                        int key1 = 0;
                        do {
                            printMessageMenu();
                            String str2 = this.scanner.nextLine();
                            if (!isDigit(str2)){
                                try {
                                    throw new IncorrectMenuArgumentException("Введите нормальное число!");
                                } catch (IncorrectMenuArgumentException e) {
                                    e.printStackTrace();
                                    break;
                                }
                            } else {
                                key1 = Integer.parseInt(str2);
                            }
                            switch (key1) {
                                case 1: {
                                    System.out.println("Введите id задачи");
                                    removeTaskPartOfMenu();
                                }
                                case 2: {
                                    System.out.println("Введите id задачи");
                                    updateTaskPartOfMenu();
                                }
                                case 3: {
                                    System.out.println("Выход в меню...");
                                    System.out.println();
                                    break;
                                }
                                default: {
                                    System.out.println("Введите правильный номер меню\n");
                                }
                            }
                        }
                        while (key1 != 3);
                        break;

                    }
                    case 4: {
                        try {
                            taskService.printRemovedTasks();
                        } catch (TaskNotFoundException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    case 5: {
                        try {
                            System.out.println(taskService.getAllGroupedByDate());
                        } catch (TaskNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    case 6: {
                        System.out.println("Завершение программы");
                        break;
                    }
                    default:
                        System.out.println("Введите правильный номер меню\n");
                }
            } while (key != 6);
        }
    }

    private void updateTaskPartOfMenu() {
        String s = this.scanner.nextLine();
        int id = 0;
        if (isDigit(s)){
            id = Integer.parseInt(s);
        } else {
            try {
                throw new IncorrectArgumentException("Введите нормальный ид!");
            } catch (IncorrectArgumentException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            taskService.findById(id);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            taskService.updateTask(id, printCreateNewTask());
            System.out.println("Задача обновлена!");
            return;
        } catch (TaskNotFoundException | IncorrectArgumentException e) {
            e.printStackTrace();
        }
        return;
    }

    private void removeTaskPartOfMenu() {
        String s = this.scanner.nextLine();
        int id = 0;
        if (isDigit(s)){
            id = Integer.parseInt(s);
        } else {
            try {
                throw new IncorrectArgumentException("Введите нормальный ид!");
            } catch (IncorrectArgumentException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            taskService.remove(id);
            System.out.println("Задача удалена");
            return;
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
        return;
    }

    private Task printCreateNewTask() throws IncorrectArgumentException {
        System.out.print("Введите имя задачи: ");
        String name = null;
        name = this.scanner.nextLine();
        if (name.isBlank() || isDigit(name) || name.isEmpty()) {
            throw new IncorrectArgumentException("Некорректные данные");
        }
        System.out.println("Выберите тип задачи" + '\n' + "1: личная" + '\n' + "2: рабочая");
        String var = this.scanner.nextLine();
        int typeIndex = 0;
        if (isDigit(var)) {
            typeIndex = Integer.parseInt(var);
        } else {
            throw new IncorrectArgumentException("Некорректные данные");
        }
        if (typeIndex > 2 || typeIndex < 1) {
            throw new IncorrectArgumentException("Некорректные данные");
        }
        Type type = Type.WORK;
        if (typeIndex == 1) {
            type = Type.PERSONAL;
        }
        System.out.print("Введите описание задачи: ");
        String description = null;
        description = this.scanner.nextLine();
        System.out.println("Выберите повторяемость задачи" + '\n' + "1: ежедневная" + '\n' + "2: еженедельная" + '\n' +
                "3: ежемесячная" + '\n' + "4: ежегодная" + '\n' + "5: одноразовая");
        String var1 = this.scanner.nextLine();
        int repeat = 0;
        if (isDigit(var1)) {
            repeat = Integer.parseInt(var1);
        } else {
            throw new IncorrectArgumentException("Некорректные данные");
        }
        if (repeat < 1 || repeat > 5){
            throw new IncorrectArgumentException("Некорректные данные");
        }
        Task task = null;
        switch (repeat) {
            case 1:
                task = new DailyTask(name, type, description);
                break;
            case 2:
                task = new WeeklyTask(name, type, description);
                break;
            case 3:
                task = new MonthlyTask(name, type, description);
                break;
            case 4:
                task = new YearlyTask(name, type, description);
                break;
            case 5:
                task = new OneTimeTask(name, type, description);
                break;
        }
        System.out.println("Задача сохранена");
        return task;
    }

    private LocalDate enterDate() throws IncorrectArgumentException {
        System.out.println("Введите дату в формате ГГГГ/ММ/ДД");
        //this.scanner.nextLine();
        String date = this.scanner.nextLine();
        String[] array = date.split("/");
        if (array.length != 3){
            throw new IncorrectArgumentException("Введите корректную дату!");
        }
        if (isDigit(array[0])){
            int checkVar = Integer.parseInt(array[0]);
            if (checkVar < 1000 || checkVar >9999){
                throw new IncorrectArgumentException("Введите корректный год!");
            }
        } else {
            throw new IncorrectArgumentException("Год должен быть числом!");
        }

        if (isDigit(array[1])){
            int checkVar = Integer.parseInt(array[1]);
            if (checkVar < 0 || checkVar > 13){
                throw new IncorrectArgumentException("Введите корректный месяц!");
            }
        } else {
            throw new IncorrectArgumentException("Месц должен быть числом!");
        }

        if (isDigit(array[2])){
            int checkVar = Integer.parseInt(array[2]);
            if (checkVar < 0 || checkVar > 32){
                throw new IncorrectArgumentException("Введите корректный день!");
            }
        } else {
            throw new IncorrectArgumentException("День должен быть числом!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        LocalDate parse = LocalDate.parse(date, formatter);
        return parse;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
