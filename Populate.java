import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Populate {

    public static void prepopulate(Admin admin, ArrayList<Course> courses) {

        // *************** Pre-populated Student & Course, Index, Class *************** //
        // 15 Students //
        admin.addStudent("Andy Lau", "andy001", "andy001", 21, "Male", "Singaporean", "U1920001A", "andy001@mystars.com");
        admin.addStudent("Bryson Teo", "bryson002", "bryson002", 21, "Male", "Malaysian", "U1920002A", "brys002@mystars.com");
        admin.addStudent("Cody Hong", "cody003", "cody003", 21, "Male", "Malaysian", "U1920003A", "cody003@mystars.com");
        admin.addStudent("Daron Lim", "daron004", "daron004", 21, "Male", "American", "U1920004A", "daron004@mystars.com");
        admin.addStudent("Emily Kim", "emily005", "emily005", 21, "Female", "Korean", "U1920005A", "emily005@mystars.com");
        admin.addStudent("Fatima Tan", "fatima006", "fatima006", 21, "Female", "Singaporean", "U1920006A", "fatima006@mystars.com");
        admin.addStudent("Gavin Chong", "gavin007", "gavin007", 21, "Male", "Singaporean", "U1920007A", "gavin007@mystars.com");
        admin.addStudent("Hayley Tan", "hayley008", "hayley008", 21, "Female", "American", "U1920008A", "hayley008@mystars.com");
        admin.addStudent("Isaac Newton", "isaac009", "isaac009", 21, "Male", "British", "U1920009A", "isaac009@mystars.com");
        admin.addStudent("Jay Chou", "jay010", "jay010", 21, "Male", "Taiwanese", "U1920010A", "jay010@mystars.com");
        admin.addStudent("Kingston Wong", "kingston011", "kingston011", 21, "Male", "Singaporean", "U1920011A", "kingston011@mystars.com");
        admin.addStudent("Lucas Tan", "lucas012", "lucas012", 21, "Male", "Singaporean", "U1920012A", "lucas012@mystars.com");
        admin.addStudent("Mandy Wong", "mandy013", "mandy013", 21, "Female", "Singaporean", "U1920013A", "mandy013@mystars.com");
        admin.addStudent("Nathan Lim", "nathan014", "nathan014", 21, "Male", "Singaporean", "U1920014A", "nathan014@mystars.com");
        admin.addStudent("Ogei Chong", "ogei015", "ogei015", 21, "Female", "Japanese", "U1920015A", "ogei015@mystars.com");

        admin.addCourse("SCSE", "CZ2001", "Algorithms", 3);
        admin.addCourse("SCSE", "CZ2002", "Object-oriented Design & Programming", 3);
        admin.addCourse("SSS", "HE9091", "Principles of Economics", 3);
        admin.addCourse("NBS", "BU8201", "Business Finance", 3);

        for(Course c : courses){
            if(c.getCourseCode().equals("CZ2001")){
                c.addIndex(new Index(c, "10124", 10));
                c.addIndex(new Index(c, "10125", 10));
            }

            if(c.getCourseCode().equals("CZ2002")){
                c.addIndex(new Index(c, "10126", 10));
                c.addIndex(new Index(c, "10127", 10));
            }

            if(c.getCourseCode().equals("HE9091")){
                c.addIndex(new Index(c, "00551", 10));
                c.addIndex(new Index(c, "00552", 10));
            }

            if(c.getCourseCode().equals("BU8021")){
                c.addIndex(new Index(c, "00402", 10));
                c.addIndex(new Index(c, "00403", 10));
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");

        for(Course c: courses){

            if(c.getCourseCode().equals("CZ2001")){
                for(Index i: c.getIndexes()){
                    try{
                    if(i.getIndexNo().equals("10124")){
                        i.addClass(new Class("10124_LEC1", "LEC", sdf.parse("8:30"), sdf.parse("9:30"), "LT10", "CS2", "BOTH", "Friday"));
                        i.addClass(new Class("10124_LEC2", "LEC", sdf.parse("10:30"), sdf.parse("11:30"), "LT11", "CS2", "BOTH", "Monday"));
                        i.addClass(new Class("10124_TUT", "TUT", sdf.parse("16:30"), sdf.parse("17:30"), "TR+9", "SSR1", "BOTH", "Thursday"));
                        i.addClass(new Class("10124_LAB", "LAB", sdf.parse("10:30"), sdf.parse("12:30"), "HWLAB1", "SSR1", "EVEN", "Wednesday"));
                    }
                    
                    if(i.getIndexNo().equals("10125")){
                        i.addClass(new Class("10125_LEC1", "LEC", sdf.parse("8:30"), sdf.parse("9:30"), "LT10", "CS2", "BOTH", "Friday"));
                        i.addClass(new Class("10125_LEC2", "LEC", sdf.parse("10:30"), sdf.parse("11:30"), "LT11", "CS2", "BOTH", "Monday"));
                        i.addClass(new Class("10125_TUT", "TUT", sdf.parse("13:30"), sdf.parse("14:30"), "TR+15", "SSR5", "BOTH", "Wednesday"));
                        i.addClass(new Class("10125_LAB", "LAB", sdf.parse("8:30"), sdf.parse("10:30"), "HWLAB1", "SSR5", "ODD", "Wednesday"));
                    }
                }catch (ParseException e){
                    e.printStackTrace();
                }
                }
            }

            if(c.getCourseCode().equals("CZ2002")){
                for(Index i: c.getIndexes()){
                    try{
                    if(i.getIndexNo().equals("10126")){
                        i.addClass(new Class("10126_LEC1", "LEC", sdf.parse("8:30"), sdf.parse("9:30"), "LT11", "CS2", "BOTH", "Thursday"));
                        i.addClass(new Class("10126_LEC2", "LEC", sdf.parse("14:30"), sdf.parse("15:30"), "LT11", "CS2", "BOTH", "Tuesday"));
                        i.addClass(new Class("10126_TUT", "TUT", sdf.parse("9:30"), sdf.parse("10:30"), "TR+17", "FEP1", "BOTH", "Wednesday"));
                        i.addClass(new Class("10126_LAB", "LAB", sdf.parse("14:30"), sdf.parse("16:30"), "SPL", "FEP1", "ODD", "Monday"));
                    }

                    if(i.getIndexNo().equals("10127")){
                        i.addClass(new Class("10127_LEC1", "LEC", sdf.parse("8:30"), sdf.parse("9:30"), "LT10", "CS2", "BOTH", "Thursday"));
                        i.addClass(new Class("10127_LEC2", "LEC", sdf.parse("14:30"), sdf.parse("15:30"), "LT11", "CS2", "BOTH", "Tuesday"));
                        i.addClass(new Class("10127_TUT", "TUT", sdf.parse("13:30"), sdf.parse("14:30"), "TR+9", "FSP3", "BOTH", "Wednesday"));
                        i.addClass(new Class("10127_LAB", "LAB", sdf.parse("14:30"), sdf.parse("16:30"), "SPL", "FSP3", "EVEN", "Monday"));
                    }
                }catch(ParseException e){
                    e.printStackTrace();
                }
                }
            }
            try{
            if(c.getCourseCode().equals("HE9091")){
                for(Index i: c.getIndexes()){
                    if(i.getIndexNo().equals("00551")){
                        i.addClass(new Class("00551_LEC", "LEC", sdf.parse("16:30"), sdf.parse("18:30"), "ONLINE", "NBS", "BOTH", "Friday"));
                        i.addClass(new Class("00551_TUT", "TUT", sdf.parse("9:30"), sdf.parse("10:30"), "LHS-TR+48", "NBS1", "BOTH", "Tuesday"));
                    }

                    if(i.getIndexNo().equals("00552")){
                        i.addClass(new Class("00552_LEC", "LEC", sdf.parse("16:30"), sdf.parse("18:30"), "ONLINE", "NBS", "BOTH", "Friday"));
                        i.addClass(new Class("00552_TUT", "TUT", sdf.parse("10:30"), sdf.parse("11:30"), "LHS-TR+48", "NBS2", "BOTH", "Tuesday"));
                    }
                }
            }

            if(c.getCourseCode().equals("BU8021")){
                for(Index i: c.getIndexes()){
                    if(i.getIndexNo().equals("00402")){
                        if(i.getIndexNo().equals("00402")){
                            i.addClass(new Class("00402_LEC", "LEC", sdf.parse("18:30"), sdf.parse("20:30"), "ONLINE", "1", "BOTH", "Wednesday"));
                            i.addClass(new Class("00402_TUT", "TUT", sdf.parse("10:30"), sdf.parse("11:30"), "S4-SR10", "1", "BOTH", "Tuesday"));
                        }
                    }

                    if(i.getIndexNo().equals("00403")){
                        i.addClass(new Class("00403_LEC", "LEC", sdf.parse("18:30"), sdf.parse("20:30"), "ONLINE", "1", "BOTH", "Wednesday"));
                            i.addClass(new Class("00403_TUT", "TUT", sdf.parse("11:30"), sdf.parse("12:30"), "S4-SR10", "2", "BOTH", "Tuesday"));
                    }
                }
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
    }
    
}
