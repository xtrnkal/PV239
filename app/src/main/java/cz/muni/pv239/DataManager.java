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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.FileHandler;

import cz.muni.pv239.enums.BuildingType;
import cz.muni.pv239.enums.Month;

public class DataManager {
    private static final String TASKS_FILE = "tasks.txt";
    private static final String STATISTICS_FILE = "statistics.txt";

    private HashMap<String, Statistics> statistics = new HashMap<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private Statistics current;

    public DataManager(Context context) {
        loadData(context);
    }

    private void loadData(Context context) {
       /* tasks.add(new Task("Study"));
        tasks.add(new Task("Work"));
        tasks.add(new Task("Coding"));
        tasks.add(new Task("Workout"));
*/
        current = new Statistics(2019, Month.MARCH);
        current.addValue("Study", new Integer(50));
        current.addValue("Workout", new Integer(120));

        current.addBuilding(BuildingType.SMALL);
        current.addBuilding(BuildingType.SMALL);
        current.addBuilding(BuildingType.MEDIUM);
        current.addBuilding(BuildingType.HUGE);

        statistics.put((current.getMonth().toString() + current.getYear()), current);

        Statistics jine = new Statistics(2019, Month.APRIL);
        jine.addValue("Jedna", new Integer(50));
        jine.addValue("Dva", new Integer(120));

        jine.addBuilding(BuildingType.SMALL);
        jine.addBuilding(BuildingType.SMALL);
        jine.addBuilding(BuildingType.MEDIUM);
        jine.addBuilding(BuildingType.HUGE);
        statistics.put((jine.getMonth().toString() + jine.getYear()), jine);

        String a = statisticsToString();
        loadTasks(context);
        loadStatistics(context);

        System.out.println("---------- ukladani po nacteni ------------");
        saveStatistics(context);
        System.out.println("---------- po ukladani po nacteni ------------");

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

    public void editStatistics(String name, Integer value, Context context) {
        current.addValue(name, value);

        saveStatistics(context);
    }


    public void saveTasks(Context context) {
        try {
            FileOutputStream out = context.openFileOutput(TASKS_FILE, Context.MODE_PRIVATE);
            /*new FileOutputStream(TASKS_FILE);
            FileOutputStream out = new FileOutputStream(new File()  getFilesDir(), "abc.txt"));
*/
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
            System.out.println("------------------------------- LOADING  TASKS--------------------------------------");
            FileInputStream in = context.openFileInput(TASKS_FILE);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            loadedTasks = new String(buffer);
            System.out.println(loadedTasks);



        } catch (IOException e) {
            e.printStackTrace();
        }

        parseLoadedTasks(loadedTasks);

        for (Task t : tasks) {
            System.out.println(t.getName());
        }
    }

    private void loadStatistics(Context context) {
        String loadedStats = new String();
        try {
            System.out.println("------------------------------- LOADING STAT--------------------------------------");
            FileInputStream in = context.openFileInput(STATISTICS_FILE);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            loadedStats = new String(buffer);
            System.out.println("Tohle jsem nacetla ze souboru:" + loadedStats);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!loadedStats.isEmpty()) {
            parseLoadedStatistics(loadedStats);
        }
        for (Task t : tasks) {
            System.out.println(t.getName());
        }
    }

    private void parseLoadedStatistics(String loadedStats) {

        //loadedStats = "{APRIL2019=YEAR=2019;MONTH=APRIL;VALUES={Study=50, Workout=120};CITY={HUGE=2, LARGE=1, MEDIUM=1, SMALL=2}}";
        System.out.println("TOHLE se bude parsovat:" + loadedStats);
        String[] list = loadedStats.split(";");

        int position = 0;
        Statistics stats = new Statistics(0, Month.JANUARY);
        for (String l : list) {
            System.out.println("-------- PARSING STATS ----------------------------------------------");
            System.out.println(l);


            switch(position) {
                case 0:
                    //{APRIL2019=YEAR=2019
                    int i = l.indexOf("YEAR");
                    l = l.substring(i);
                    l = l.replace("YEAR=", "");
                    System.out.println(l);
                    stats.setYear(Integer.parseInt(l));


                    ++position;
                    break;
                case 1:
                    System.out.println(l);
                    l = l.replace("MONTH=", "");
                    stats.setMonth(Month.valueOf(l));

                    //statistics = new Statistics(year, month);
                    ++position;
                    break;
                case 2:

                    l = l.replace("VALUES=", "");
                    l = l.replace("{", "");
                    l = l.replace("}", "");
                    String[] parts = l.split(", ");
                    System.out.println(l);
                    for (String p : parts) {
                        System.out.println(p);
                        String[] nameVal = p.split("=");
                        stats.addValue(nameVal[0], Integer.parseInt(nameVal[1]));
                    }
                    ++position;
                    break;
                case 3:
                    System.out.println(l);

                    l = l.replace("CITY=", "");
                    l = l.replace("{", "");
                    l = l.replace("}", "");
                    String[] cityParts = l.split(", ");
                    System.out.println(l);
                    for (String p : cityParts) {
                        System.out.println(p);
                        String[] nameVal = p.split("=");

                        stats.addMoreBuildings(BuildingType.valueOf(nameVal[0]),  Integer.parseInt(nameVal[1]));
                    }

                    statistics.put((stats.getMonth().toString() + stats.getYear()), stats);
                    stats = new Statistics(0, Month.JANUARY);
                    position = 0;
                    break;

            }
            //Statistics tmp = new Statistics();
            //Task tmp = new Task(l);
            //tasks.add(tmp);


        }
        //statistics.put((stats.getMonth().toString() + stats.getYear()), stats);

        //System.out.println(statistics);
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
            System.out.println("------------------------------- SAVE STAT--------------------------------------");
            FileOutputStream out = context.openFileOutput(STATISTICS_FILE, Context.MODE_PRIVATE);
            /*new FileOutputStream(TASKS_FILE);
            FileOutputStream out = new FileOutputStream(new File()  getFilesDir(), "abc.txt"));
*/
            //byte[] separator = (";").getBytes();
            String tmp = statisticsToString();
            System.out.print("TOHLE se bude ukladat:" + tmp);
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
            System.out.println("------------------------------- DELETE DATA--------------------------------------");

            FileOutputStream tasks = context.openFileOutput(TASKS_FILE, context.MODE_PRIVATE);
            tasks.close();

            FileOutputStream statistics = context.openFileOutput(STATISTICS_FILE, context.MODE_PRIVATE);
            statistics.close();

            this.statistics.clear();
            this.tasks.clear();
            this.current.deleteAllValues();
            this.current.clearCity();

            //Files.newBufferedWriter(TASKS_FILE , StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("----------------DELETED ---------------------");
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    private String statisticsToString() {
        String result = "";

        Iterator it = statistics.values().iterator();

        while (it.hasNext()) {
            //it.next(); // statistics
            //System.out.println(it.next());

            String stat = it.next().toString();
            result = result + "{" + stat + "};";

        }

        System.out.println("Tohle jsou statistiky v textu:" + result);
        return result;
    }
}
