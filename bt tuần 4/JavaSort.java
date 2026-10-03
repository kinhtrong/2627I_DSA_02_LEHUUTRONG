import java.util.*;

class Student {
    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }
}

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student x, Student y) {
        int result = Double.compare(y.getCgpa(), x.getCgpa());

        if (result != 0) {
            return result;
        }

        result = x.getName().compareTo(y.getName());

        if (result != 0) {
            return result;
        }

        return Integer.compare(x.getId(), y.getId());
    }
}

public class JavaSort{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        ArrayList<Student> studentList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = in.nextInt();
            String name = in.next();
            double cgpa = in.nextDouble();

            studentList.add(new Student(id, name, cgpa));
        }

        Collections.sort(studentList, new StudentComparator());

        for (Student student : studentList) {
            System.out.println(student.getName());
        }
    }
}

