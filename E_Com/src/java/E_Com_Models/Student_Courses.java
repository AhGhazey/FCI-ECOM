/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Models;

/**
 *
 * @author aghazey
 */
public class Student_Courses {
    public String course_name;
    public String course_code;
    public float p_grade;
    public float f_grade;
    public float full_grade;
    public Student_Courses(String course_name, String course_code, float p_grade, float full_grade, float f_grade) {
        this.course_name = course_name;
        this.course_code = course_code;
        this.p_grade = p_grade;
        this.full_grade = full_grade;
        this.f_grade = f_grade;
    }

    public Student_Courses() {
    }
    
}
