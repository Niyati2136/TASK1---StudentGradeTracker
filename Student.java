/**
 * Student class represents a student with name and grades
 */
public class Student {
    private String name;
    private double[] grades;
    private int gradeCount;
    
    public Student(String name, int maxGrades) {
        this.name = name;
        this.grades = new double[maxGrades];
        this.gradeCount = 0;
    }
    
    public void addGrade(double grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount++] = grade;
        } else {
            System.out.println("Maximum grades reached for " + name);
        }
    }
    
    public double calculateAverage() {
        if (gradeCount == 0) return 0.0;
        double sum = 0;
        for (int i = 0; i < gradeCount; i++) {
            sum += grades[i];
        }
        return sum / gradeCount;
    }
    
    public double getHighestGrade() {
        if (gradeCount == 0) return 0.0;
        double highest = grades[0];
        for (int i = 1; i < gradeCount; i++) {
            if (grades[i] > highest) {
                highest = grades[i];
            }
        }
        return highest;
    }
    
    public double getLowestGrade() {
        if (gradeCount == 0) return 0.0;
        double lowest = grades[0];
        for (int i = 1; i < gradeCount; i++) {
            if (grades[i] < lowest) {
                lowest = grades[i];
            }
        }
        return lowest;
    }
    
    public String getName() {
        return name;
    }
    
    public int getGradeCount() {
        return gradeCount;
    }
    
    public double getGrade(int index) {
        if (index >= 0 && index < gradeCount) {
            return grades[index];
        }
        return 0.0;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(name).append("\n");
        sb.append("Grades: ");
        for (int i = 0; i < gradeCount; i++) {
            sb.append(grades[i]);
            if (i < gradeCount - 1) sb.append(", ");
        }
        sb.append("\n");
        sb.append(String.format("Average: %.2f\n", calculateAverage()));
        sb.append(String.format("Highest: %.2f\n", getHighestGrade()));
        sb.append(String.format("Lowest: %.2f\n", getLowestGrade()));
        return sb.toString();
    }
}