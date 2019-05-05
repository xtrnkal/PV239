package cz.muni.pv239;

import java.util.ArrayList;
import java.util.HashMap;

import cz.muni.pv239.enums.BuildingType;
import cz.muni.pv239.enums.Month;

public class DataManager {
    private HashMap<String, Statistics> statistics = new HashMap<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private Statistics current;

    public DataManager() {
        loadData();
    }

    private void loadData() {
        tasks.add(new Task("Study"));
        tasks.add(new Task("Work"));
        tasks.add(new Task("Coding"));
        tasks.add(new Task("Workout"));

        current = new Statistics(2019, Month.APRIL);
        current.addValue("Study", new Integer(50));
        current.addValue("Workout", new Integer(120));

        current.addBuilding(BuildingType.SMALL);
        current.addBuilding(BuildingType.SMALL);
        current.addBuilding(BuildingType.MEDIUM);
        current.addBuilding(BuildingType.HUGE);

        statistics.put("current.getMonth() + current.getYear()", current);
    }

    public void addTask(String name) {
        Task tmp = new Task(name);
        if (tasks.contains(tmp) == true) {
            tasks.set(tasks.indexOf(name), tmp);
        } else {
            tasks.add(tmp);
        }
        saveTasks();
    }

    public void editTask(String nameBefore, String nameAfter) {
        Task before = new Task(nameBefore);
        Task after = new Task(nameAfter);

        if (tasks.contains(before) == true && tasks.contains(after) == false) {
            tasks.set(tasks.indexOf(before), after);
        } else if (tasks.contains(after) == false) {
            tasks.add(after);
        }
        saveTasks();
    }

    public void editStatistics(String name, Integer value) {
        current.addValue(name, value);


        /*
        if (value < 10) {
            current.addBuilding(BuildingType.SMALL);
        } else if (value < 30) {
            current.addBuilding(BuildingType.MEDIUM);
        } else if (value < 60) {
            current.addBuilding(BuildingType.LARGE);
        } else {
            current.addBuilding(BuildingType.HUGE);
        }*/

        saveStatistics();
    }


    public void saveTasks() {

    }

    public void saveStatistics() {

    }

    public HashMap<String, Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(HashMap<String, Statistics> statistics) {
        this.statistics = statistics;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
