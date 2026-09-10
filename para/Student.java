package para;

public class Student {

    public String nickName;
    public int lazyness; 
    public int IQ;
    public int exams;
    public int[] marks = new int[exams];

    public Student() {
       
    }

    public Student(Student Prototype) {
        nickName = Prototype.nickName;
        lazyness = Prototype.lazyness;
        IQ = Prototype.IQ;
        exams = Prototype.exams;
        marks = new int[exams];
        for (int i = 0; i < exams; i++) {
            marks[i] = Prototype.marks[i];
        }
    }

    void printInfo() {
        System.out.println("Student Info:");
        System.out.println("Nickname: " + nickName);
        System.out.println("Lazyness: " + lazyness);
        System.out.println("IQ: " + IQ);
        System.out.println("Exams: " + exams);
        System.out.print("Marks: ");
        for (int i = 0; i < exams; i++) {
            System.out.print(marks[i] + " ");
        }
        System.out.println();
    }


public static void main(String[] args) {
        Student s1 = new Student();
        s1.nickName = "Alex";
        s1.lazyness = 3;
        s1.IQ = 120;
        s1.exams = 4;
        s1.marks = new int[]{90, 85, 78, 92};

        System.out.println("Original Student:");
        s1.printInfo();

        Student s2 = new Student(s1);
        System.out.println("\nCopied Student:");
        s2.printInfo();
    }
}