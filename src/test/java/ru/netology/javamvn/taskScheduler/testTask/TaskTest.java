package ru.netology.javamvn.taskScheduler.testTask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void simpleTaskMatchesTitle() {
        SimpleTask task = new SimpleTask(1, "Купить продукты");

        boolean expected = true;
        boolean actual = task.matches("продукты");

        Assertions.assertEquals(expected, actual);

        expected = false;
        actual = task.matches("одежда");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void epicMatchesSubtask() {
        String[] subtasks = {
                "Купить молоко",
                "Приготовить ужин",
                "Постирать одежду"
        };
        Epic epic = new Epic(2, subtasks);

        boolean expected = true;
        boolean actual = epic.matches("молоко");

        Assertions.assertEquals(expected, actual);

        expected = true;
        actual = epic.matches("ужин");

        Assertions.assertEquals(expected, actual);

        expected = false;
        actual = epic.matches("книги");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void meetingMatchesTopic() {
        Meeting meeting = new Meeting(
                3,
                "Обсуждение бюджета проекта",
                "Финансовый отдел",
                "15:00, пятница"
        );

        boolean expected = true;
        boolean actual = meeting.matches("бюджет");

        Assertions.assertEquals(expected, actual);

        expected = true;
        actual = meeting.matches("Финансовый");

        Assertions.assertEquals(expected, actual);

        expected = false;
        actual = meeting.matches("отпуск");

        Assertions.assertEquals(expected, actual);
    }
}
