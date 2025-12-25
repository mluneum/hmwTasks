package ru.netology.javamvn.taskScheduler.testTask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTasksByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить хлеб");
        String[] subtasks = {"Написать отчёт", "Проверить тесты", "Обновить документацию"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(3, "Планирование релиза", "Проект X", "Завтра в 10:00");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        // Тест поиска для SimpleTask
        Task[] result1 = todos.search("хлеб");
        int expectedCount1 = 1;
        int actualCount1 = result1.length;

        Assertions.assertEquals(expectedCount1, actualCount1);
        Assertions.assertEquals(simpleTask, result1[0]);

        // Тест поиска для Epic
        Task[] result2 = todos.search("отчёт");
        int expectedCount2 = 1;
        int actualCount2 = result2.length;

        Assertions.assertEquals(expectedCount2, actualCount2);
        Assertions.assertEquals(epic, result2[0]);

        // Тест поиска для Meeting
        Task[] result3 = todos.search("релиз");
        int expectedCount3 = 1;
        int actualCount3 = result3.length;

        Assertions.assertEquals(expectedCount3, actualCount3);
        Assertions.assertEquals(meeting, result3[0]);

        // Тест отсутствия результатов
        Task[] result4 = todos.search("кофе");
        int expectedCount4 = 0;
        int actualCount4 = result4.length;

        Assertions.assertEquals(expectedCount4, actualCount4);
    }

    @Test
    public void shouldFindMultipleTasks() {
        SimpleTask task1 = new SimpleTask(1, "Купить яблоки");
        SimpleTask task2 = new SimpleTask(2, "Купить бананы");
        Epic epic = new Epic(3, new String[]{"Написать код", "Протестировать функционал"});
        Meeting meeting = new Meeting(4, "Обсуждение яблок", "Проект Фрукты", "Сегодня");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("яблок");
        int expectedCount = 2;
        int actualCount = result.length;

        Assertions.assertEquals(expectedCount, actualCount);

        boolean hasTask1 = result[0] == task1 || result[1] == task1;
        Assertions.assertTrue(hasTask1);

        boolean hasMeeting = result[0] == meeting || result[1] == meeting;
        Assertions.assertTrue(hasMeeting);

        boolean hasTask2 = result[0] == task2 || result[1] == task2;
        Assertions.assertFalse(hasTask2);
    }
}
