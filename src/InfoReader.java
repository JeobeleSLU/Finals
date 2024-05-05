import java.io.*;
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

//    private ArrayList<Course> readTemplate(String fileName){
//        ArrayList templateArray = new ArrayList<>();
//        try (FileInputStream fileIn = new FileInputStream(fileName);
//             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
//            templateArray = (ArrayList) objectIn.readObject();
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found: " + fileName);
//        } catch (IOException | ClassNotFoundException | ClassCastException e) {
//            System.err.println("Error reading file: " + fileName);
//            e.printStackTrace();
//        }
//        return templateArray;
//    }
    private ArrayList<Course> readTemplate(String template) throws IOException {

        ArrayList<Course> templateCourses = new ArrayList<>();
        try  {
            File file = new File(template);
            Scanner scanner = new Scanner(file);
            String line;
           while (scanner.hasNextLine()) {
               String name= "";
               int year = 0;
               int sem = 0;
               float units = 0;
               float grade =0;
               line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    name = parts[0];
                    year = Integer.parseInt(parts[1]);
                    sem = Integer.parseInt(parts[2]);
                    units = Float.parseFloat(parts[3]);
                    grade = Float.parseFloat(parts[4]);
                    templateCourses.add(
                            new Course(name, year, sem, units, grade));
                }
            }
        }catch (IOException e){
            System.out.println(templateCourses);
            course = templateCourses;

            return templateCourses;
        }
        templateCourses.forEach(c-> System.out.println(c.getName()));
        //System.out.println(templateCourses);
        course = templateCourses;
        return templateCourses;
    }
}
