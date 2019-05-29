package cz.muni.pv239;

import android.content.Context;
import android.renderscript.ScriptIntrinsicYuvToRGB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.FileHandler;

import cz.muni.pv239.enums.BuildingType;
import cz.muni.pv239.enums.Month;

public class DataManager {
    private static final String TASKS_FILE = "tasks.txt";
    private static final String STATISTICS_FILE = "statistics.txt";

    private HashMap<String, Statistics> statistics = new HashMap<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private String current;
    private int currentYear;
    private Month currentMonth;

    public DataManager(Context context) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("UK"));

        currentMonth = (Month.values())[c.get(Calendar.MONTH)];
        currentYear = c.get(Calendar.YEAR);
        current = currentMonth.toString() + currentYear;

        File file1 = new File(context.getFilesDir() + File.separator + TASKS_FILE);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file2 = new File(context.getFilesDir() + File.separator + STATISTICS_FILE);
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (statistics.isEmpty()) {
            loadData(context);
        } else {
            System.out.println("------------- STATS not empty --------------" + statisticsToString());
        }

        if (statistics.get(current) == null) {
            Statistics tmpCurrent = new Statistics(currentYear, currentMonth);
            statistics.put(current, tmpCurrent);
        }
    }

    private void loadData(Context context) {
        System.out.println(statistics.toString());
        loadTasks(context);
        loadStatistics(context);
    }

    public String getPreviousName (String name) {
        Month m = statistics.get(name).getMonth();
        int y = statistics.get(name).getYear();

        Month targetM;
        int targetY;

        if (m.ordinal() == 0) {
            targetM = Month.DECEMBER;
            targetY = y - 1;
        } else {
            targetM = (Month.values())[(m.ordinal() - 1)];
            targetY = y;
        }
        if (statistics.get(targetM.toString() + targetY) == null) {
            return "";
        }
        return targetM.toString() + targetY;
    }

    public String getNextName (String name) {
        Month m = statistics.get(name).getMonth();
        int y = statistics.get(name).getYear();

        Month targetM;
        int targetY;

        if (m.ordinal() == 11) {
            targetM = Month.JANUARY;
            targetY = y + 1;
        } else {
            targetM = (Month.values())[(m.ordinal() + 1)];
            targetY = y;
        }

        if (statistics.get(targetM.toString() + targetY) == null) {
            return "";
        }

        return targetM.toString() + targetY;
    }

    public void addTask(String name, Context context) {
        Task tmp = new Task(name);
        if (tasks.contains(tmp) == true) {
            tasks.set(tasks.indexOf(name), tmp);
        } else {
            tasks.add(tmp);
        }
        saveTasks(context);
    }

    public void editTask(String nameBefore, String nameAfter, Context context) {
        Task before = new Task(nameBefore);
        Task after = new Task(nameAfter);

        if (tasks.contains(before) == true && tasks.contains(after) == false) {
            tasks.set(tasks.indexOf(before), after);
        } else if (tasks.contains(after) == false) {
            tasks.add(after);
        }
        saveTasks(context);
    }

    public void deleteTask(String name, Context context) {
        Task task = new Task(name);
        tasks.remove(tasks.indexOf(task));
        saveTasks(context);
    }

    public void editStatistics(String name, Integer value, Context context) {

        if (statistics.get(current) != null) {
            statistics.get(current).addValue(name, value);
            saveStatistics(context);
        }
    }


    public void saveTasks(Context context) {
        try {
            FileOutputStream out = context.openFileOutput(TASKS_FILE, Context.MODE_PRIVATE);
            byte[] separator = (";").getBytes();
            for (Task t : tasks) {
                byte[] strToBytes = t.getName().getBytes();
                out.write(strToBytes);
                out.write(separator);
            }
            out.close();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }


    private void loadTasks(Context context) {
        String loadedTasks = new String();
        try {
            FileInputStream in = context.openFileInput(TASKS_FILE);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            loadedTasks = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        parseLoadedTasks(loadedTasks);
    }

    private void loadStatistics(Context context) {
        String loadedStats = new String();
        try {
            FileInputStream in = context.openFileInput(STATISTICS_FILE);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            loadedStats = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!loadedStats.isEmpty()) {
            parseLoadedStatistics(loadedStats);
        }
    }

    private void parseLoadedStatistics(String loadedStats) {
        String[] list = loadedStats.split(";");

        int position = 0;
        Statistics stats = new Statistics(0, Month.JANUARY);
        for (String l : list) {
            switch(position) {
                case 0:
                    //{APRIL2019=YEAR=2019
                    int i = l.indexOf("YEAR");
                    l = l.substring(i);
                    l = l.replace("YEAR=", "");
                    stats.setYear(Integer.parseInt(l));
                    ++position;
                    break;
                case 1:
                    l = l.replace("MONTH=", "");
                    stats.setMonth(Month.valueOf(l));
                    ++position;
                    break;
                case 2:
                    l = l.replace("VALUES=", "");
                    l = l.replace("{", "");
                    l = l.replace("}", "");
                    String[] parts = l.split(", ");
                    for (String p : parts) {
                        String[] nameVal = p.split("=");
                        stats.addTaskValue(nameVal[0], Integer.parseInt(nameVal[1]));
                    }
                    ++position;
                    break;
                case 3:
                    l = l.replace("CITY=", "");
                    l = l.replace("{", "");
                    l = l.replace("}", "");
                    String[] cityParts = l.split(", ");
                    for (String p : cityParts) {
                        String[] nameVal = p.split("=");
                        stats.addMoreBuildings(BuildingType.valueOf(nameVal[0]),  Integer.parseInt(nameVal[1]));
                    }

                    statistics.put((stats.getMonth().toString() + stats.getYear()), stats);
                    stats = new Statistics(0, Month.JANUARY);
                    position = 0;
                    break;
            }
        }
    }

    private void parseLoadedTasks(String loadedTasks) {
        String[] list = loadedTasks.split(";");
        for (String l : list) {
            if (!l.isEmpty()) {
                Task tmp = new Task(l);
                tasks.add(tmp);
            }
        }
    }

    public void saveStatistics(Context context) {
        try {
            FileOutputStream out = context.openFileOutput(STATISTICS_FILE, Context.MODE_PRIVATE);

            String tmp = statisticsToString();
            byte[] strToBytes = tmp.getBytes();
            out.write(strToBytes);
            out.close();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Statistics> getStatistics() {
        return statistics;
    }

    public Statistics getCurrentStatistics() {
        return statistics.get(current);
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

    public void deteleAllData(Context context){
        try {
            FileOutputStream tasks = context.openFileOutput(TASKS_FILE, context.MODE_PRIVATE);
            tasks.close();

            FileOutputStream statistics = context.openFileOutput(STATISTICS_FILE, context.MODE_PRIVATE);
            statistics.close();

            this.statistics.clear();
            this.tasks.clear();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    private String statisticsToString() {
        String result = "";
        Iterator it = statistics.values().iterator();

        while (it.hasNext()) {
            String stat = it.next().toString();
            result = result + "{" + stat + "};";
        }

        return result;
    }
}
