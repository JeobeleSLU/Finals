import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class InfoReader {
    private ArrayList<Course> course = new ArrayList<>();
    File file = new File("UserCourses.ser");


    public InfoReader() {
        try {
            course = readCourses("TemplateCourse");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Course> getCourse() {
        return course;
    }



    //uses the course reader
    private ArrayList readCourses(String fileName) throws IOException {
        ArrayList courses;
        if(!file.exists()){
            courses = readTemplate(fileName);
            file.createNewFile();
        }else
            courses = readUserCourse();
        return courses;


    }
    void delUserCourses(){
        file.delete();
        System.out.println("successfully deleted!");
    }

    private ArrayList readUserCourse() {
        ArrayList userCourses = new ArrayList();
        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            userCourses = (ArrayList) objectIn.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println("Error reading file: " + file.getName());
            e.printStackTrace();
        }
        return userCourses;
    }

    private ArrayList<Course> readTemplate(String template) throws IOException {

        ArrayList<Course> templateCourses = new ArrayList<>();
        try  {
            File file = new File(template);
            Scanner scanner = new Scanner(file);
            String line;
           while (scanner.hasNextLine()) {
               String courseNo = "";
               String name= "";
               int year = 0;
               int sem = 0;
               float units = 0;
               float grade =0;
               line = scanner.nextLine();
               String[] parts = line.split(",");
               System.out.println(parts[1]);
                if (parts.length == 6) {
                    courseNo = parts[0];
                    name = parts[1];
                    year = Integer.parseInt(parts[2]);
                    sem = Integer.parseInt(parts[3]);
                    units = Float.parseFloat(parts[4]);
                    grade = Float.parseFloat(parts[5]);
                    templateCourses.add(
                            new Course(courseNo, name, year, sem, units, grade));
                }
            }
        }catch (IOException e){
            course = templateCourses;

            return templateCourses;
        }
        course = templateCourses;
        return templateCourses;
    }
}
