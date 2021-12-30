package controller;

import app.entities.Lesson;
import app.entities.User;

import java.util.Comparator;
import java.util.List;

public class Controller {
    private static User currentUser;

    private static List<Lesson> sortedLessons = null;
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Controller.currentUser = currentUser;
    }

    public static List<Lesson> getSortedLessons() {
        return sortedLessons;
    }

    public static void setSortedLessons(List<Lesson> sortedLessons) {
        Controller.sortedLessons = sortedLessons;
    }

    public static void sortLessons(List<Lesson> lessons, String order){
        String checkOrder = order.replace("-", "");
        if (checkOrder.equalsIgnoreCase("count_of_students")){
            Comparator<Lesson> lessonsCountOFStudents;
            if (order.startsWith("-")) lessonsCountOFStudents = Comparator.comparingInt(Lesson::getCountOfStudents);
            else lessonsCountOFStudents = (o1, o2) -> (o2.getCountOfStudents()) - o1.getCountOfStudents();

            lessons.sort(lessonsCountOFStudents);
            sortedLessons = lessons;
        }
        else if (checkOrder.equalsIgnoreCase("lesson_name")){
            Comparator<Lesson> lessonsName;
            if (order.startsWith("-")) lessonsName = (o1, o2) -> o2.getLessonName().compareTo(o1.getLessonName());
            else lessonsName = Comparator.comparing(Lesson::getLessonName);

            lessons.sort(lessonsName);
            sortedLessons = lessons;
        }
        else if (checkOrder.equalsIgnoreCase("status")){
            Comparator<Lesson> lessonsStatus;
            lessonsStatus = Comparator.comparingInt(Lesson::getStatus);
            lessons.sort(lessonsStatus);
            sortedLessons = lessons;
        }
    }
}
