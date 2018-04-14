/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Database;

import E_Com_Models.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aghazey
 */
public class DatabaseWrapper implements IDatabaseWrapper {

    public static Connection mysql_connection;

    public Connection Create_database_connection(String database_Name, String user_name, String password) {
        String url = "jdbc:mysql://localhost:3306/" + database_Name;
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        Connection connection;
        try {
            connection = (Connection) DriverManager.getConnection(url, user_name, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return connection;
    }

    public User Check_login(String user_name, String password) {
        User user = null;
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String check_login = "{call SP_check_login(?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement callableStatement;
            callableStatement = mysql_connection.prepareCall(check_login);
            callableStatement.setString(1, user_name);
            callableStatement.setString(2, password);
            callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
            callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
            callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
            callableStatement.registerOutParameter(6, java.sql.Types.INTEGER);
            callableStatement.registerOutParameter(7, java.sql.Types.FLOAT);
            callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(11, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(12, java.sql.Types.INTEGER);
            callableStatement.executeUpdate();
            int valid = callableStatement.getInt(3);
            if (valid < 0) {
                return null;
            }
            int role = callableStatement.getInt(4);
            if (role == 1) {
                Student student = new Student();
                student.email = user_name;
                student.password = "";
                student.id = callableStatement.getInt(5);
                student.level = callableStatement.getInt(6);
                student.gpa = callableStatement.getFloat(7);
                student.major = callableStatement.getString(8);
                student.minor = callableStatement.getString(9);
                student.group_name = callableStatement.getString(10);
                student.user_name = callableStatement.getString(11);
                student.user_id = callableStatement.getInt(12);
                user = student;
            } else {
                Staff staff = new Staff();
                staff.email = user_name;
                staff.password = "";
                staff.id = callableStatement.getInt(5);
                staff.user_name = callableStatement.getString(11);
                staff.user_id = callableStatement.getInt(12);
                user = staff;
            }
            user.role = role;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int Insert_course(String course_name, String course_code) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "INSERT INTO course (course_name,course_code) VALUES (?,?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, course_name);
            preparedStmt.setString(2, course_code);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    public int Insert_group(String group_name) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "INSERT INTO groups (group_name) VALUES (?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, group_name);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    public int Insert_hall(String hall_name, int hall_capacity) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "INSERT INTO hall (hall_name,hall_capacity) VALUES (?,?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, hall_name);
            preparedStmt.setInt(2, hall_capacity);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    public int Insert_department(String department_name) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "INSERT INTO department (department_name) VALUES (?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, department_name);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    public int Insert_news(String title, String photo_url, String content) {
        //INSERT INTO news (news_title,news_photo_url, news_content) VALUES ('New Semester timetable updated ','', 'ANY thing  ');
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "INSERT INTO news (news_title,news_photo_url, news_content) VALUES (?,?,?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, title);
            preparedStmt.setString(2, photo_url);
            preparedStmt.setString(3, content);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    public int Insert_announecment(String title, String content, int level) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "INSERT INTO announecment (announcement_title,announcement_content, announcement_level) VALUES (?,?,?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, title);
            preparedStmt.setString(2, content);
            preparedStmt.setInt(3, level);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }

        return 1;

    }

    public int Insert_student(String student_name, String email, String student_password, float gpa, int major_id, int minor_id, int group_id, int level) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String check_login = "{call SP_Insert_Student(?,?,?,?,?,?,?,?)}";
            CallableStatement callableStatement;
            callableStatement = mysql_connection.prepareCall(check_login);
            callableStatement.setString(1, student_name);
            callableStatement.setString(2, student_password);
            callableStatement.setString(3, email);
            callableStatement.setFloat(4, gpa);
            callableStatement.setInt(5, major_id);
            callableStatement.setInt(6, minor_id);
            callableStatement.setInt(7, group_id);
            callableStatement.setInt(8, level);
            callableStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }

    public int Insert_staff(String staff_name, String staff_password, String email, int department_id, int role, String position) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String check_login = "{call SP_Insert_staff(?,?,?,?,?,?)}";
            CallableStatement callableStatement;
            callableStatement = mysql_connection.prepareCall(check_login);
            callableStatement.setString(1, staff_name);
            callableStatement.setString(2, staff_password);
            callableStatement.setString(3, email);
            callableStatement.setString(4, position);
            callableStatement.setInt(5, role);
            callableStatement.setInt(6, department_id);

            callableStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }

    public List<Student_Courses> Get_student_courses(int student_id) {
        List<Student_Courses> scourses = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select * from (select student_courses.student_id,student_courses.p_grade,student_courses.f_grade,(student_courses.p_grade + student_courses.f_grade) as full_grade, course.course_name,course.course_code from student_courses  left join course on course.course_id = student_courses.course_id) as table_courses where student_id = " + student_id;
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                scourses.add(new Student_Courses(rs.getString("course_name"), rs.getString("course_code"),
                        rs.getFloat("p_grade"), rs.getFloat("full_grade"), rs.getFloat("f_grade")));
            }
        } catch (Exception e) {

        }
        return scourses;
    }

    public List<AvailableSlot> Get_AvailableSlotses(String day) {
        List<AvailableSlot> slots = new ArrayList<>();

        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select hall.hall_id,time_table_slot.id,hall.hall_name,hall.hall_capacity, time_table_slot.start_hr,time_table_slot.end_hr from time_table_slot right join hall on time_table_slot.hall_id = hall.hall_id where is_free = 1 And time_table_slot.day ='" + day + "'";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                slots.add(new AvailableSlot(day, rs.getInt("hall_id"), rs.getInt("id"),
                        rs.getString("start_hr"), rs.getString("end_hr"),
                        rs.getString("hall_name"), rs.getInt("hall_capacity")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return slots;
    }

    public List<Hall> Get_Faculty_Halls() {
        List<Hall> halls = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select * from hall";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                halls.add(new Hall(rs.getInt("hall_id"), rs.getInt("hall_capacity"), rs.getString("hall_name")));
            }
        } catch (Exception e) {

        }
        return halls;
    }

    public List<Group> Get_student_groups() {
        List<Group> groups = new ArrayList<Group>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select * from groups";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                groups.add(new Group(rs.getInt("group_id"), rs.getString("group_name")));
            }
        } catch (Exception e) {

        }
        return groups;
    }

    public List<Department> Get_student_departments() {
        List<Department> departments = new ArrayList<Department>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select * from department";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                departments.add(new Department(rs.getInt("department_id"), rs.getString("department_name")));
            }
        } catch (Exception e) {

        }
        return departments;
    }

    public int Insert_timeslot(int hall_id, int slot_number, boolean isfree, String day, String start_hr, String end_hr) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "INSERT INTO time_table_slot (hall_id,day, start_hr,end_hr,is_free,slot_number) VALUES (?,?,?,?,?,?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setInt(1, hall_id);
            preparedStmt.setString(2, day);
            preparedStmt.setString(3, start_hr);
            preparedStmt.setString(4, end_hr);
            preparedStmt.setBoolean(5, isfree);
            preparedStmt.setInt(6, slot_number);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int Send_contact_us(String email, String title, String message) {
        int result = -1;
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String check_login = "{call SP_Contact_us(?,?,?,?)}";
            CallableStatement callableStatement;
            callableStatement = mysql_connection.prepareCall(check_login);
            callableStatement.setString(1, "Admin@fci.edu.eg");
            callableStatement.setString(2, title);
            String body = "from: " + email + "\n" + message;
            callableStatement.setString(3, body);
            callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);

            callableStatement.executeUpdate();
            result = callableStatement.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Announcement> Get_Announcement() {
        List<Announcement> announcements = new ArrayList<Announcement>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select * from announcement";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                announcements.add(new Announcement(rs.getInt("announcement_id"), rs.getInt("announcement_level"), rs.getString("announcement_title"), rs.getString("announcement_content")));
            }
        } catch (Exception e) {

        }
        return announcements;

    }

    public List<News> Get_News() {
        List<News> news = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select * from news";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                news.add(new News(rs.getInt("news_id"), rs.getString("news_title"), rs.getString("c"), rs.getString("news_content")));
            }
        } catch (Exception e) {

        }
        return news;

    }

    public List<StaffMail> GetStaff_Mails() {
        List<StaffMail> mails = new ArrayList<>();

        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select user.user_name, user.user_email,staff.staff_id from staff left join user on staff.user_id = user.user_id";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                mails.add(new StaffMail(rs.getInt("staff_id"), rs.getString("user_name"), rs.getString("user_email")));
            }
        } catch (Exception e) {

        }

        return mails;

    }

    public int Insert_Reservation(String purpose, int user_id, int slot_id, String date, int level, int group_id, int department_id) {
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "insert into reservation (reservation_purpose,reservation_state,user_id,slot_id, reservation_date,level,group_id,department_id) values (?,?,?,?,STR_TO_DATE( ?, '%m/%d/%Y'),?,?,?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, purpose);
            preparedStmt.setInt(2, 1);
            preparedStmt.setInt(3, user_id);
            preparedStmt.setInt(4, slot_id);
            preparedStmt.setString(5, date);
            preparedStmt.setInt(6, level);
            preparedStmt.setInt(7, group_id);
            preparedStmt.setInt(8, department_id);
            preparedStmt.execute();

        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public List<Top50Record> GetTop50(int level, int department_id) {
        List<Top50Record> top50 = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select user.user_name, student.student_gpa from student left join user on user.user_id = student.user_id where student.student_level = " + level + " and student.student_major= " + department_id + " order by  student_gpa desc limit 50;";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                top50.add(new Top50Record(rs.getString("user_name"), rs.getFloat("student_gpa")));
            }
        } catch (Exception e) {

        }

        return top50;
    }

    public void Add_office_hours(String day, String start, String end, String place, int staff_id) {

        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "insert into office_hours (day,start,end,place, staff_id) values (?,?,?,?,?)";
            Statement st;
            PreparedStatement preparedStmt = mysql_connection.prepareStatement(query);
            preparedStmt.setString(1, day);
            preparedStmt.setString(2, start);
            preparedStmt.setString(3, end);
            preparedStmt.setString(4, place);
            preparedStmt.setInt(5, staff_id);

            preparedStmt.execute();

        } catch (Exception e) {

        }

    }

    public GP_Team Get_GP_teams_staff(int staff_id) {
        GP_Team teams = new GP_Team();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select team.team_id, user.user_name, course.course_name, team.project_description,team.team_name  from team left join student_teams on team.team_id = student_teams.team_id left join  course on course.course_id = team.course_id left join student on student.student_id = student_teams.student_id left join user on user.user_id = student.user_id  where team.staff_id = " + staff_id + " and course.course_id =7";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                int team_id = rs.getInt("team_id");
                TeamRecord teamMember = new TeamRecord(rs.getString("team_name"), rs.getString("project_description"), rs.getString("user_name"), rs.getString("course_name"));
                if (teams.project_members.get(team_id) == null) {
                    List<TeamRecord> members = new ArrayList<>();
                    members.add(teamMember);
                    teams.project_members.put(team_id, members);
                } else {
                    teams.project_members.get(team_id).add(teamMember);
                }
            }
        } catch (Exception e) {

        }
        return teams;
    }

    public List<TeamRecord> GetStudent_team(int student_id) {
        List<TeamRecord> team = new ArrayList<>();

        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String query = "{call SP_GetStudentGPTeam(?,?)}";
            CallableStatement callableStatement;
            callableStatement = mysql_connection.prepareCall(query);
            callableStatement.setInt(1, student_id);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);

            callableStatement.execute();
            ResultSet rs = callableStatement.getResultSet();
            String StaffName = callableStatement.getString(2);
            while (rs.next()) {
                TeamRecord tmp = new TeamRecord(rs.getString("team_name"), rs.getString("project_description"), rs.getString("user_name"), rs.getString("course_name"));
                tmp.StaffName = StaffName;
                team.add(tmp);

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return team;
    }

    public List<OfficeHour> GetStaff_OfficeHours(int staff_id) {
        List<OfficeHour> office_hours = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select * from office_hours where staff_id = " + staff_id;
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                office_hours.add(new OfficeHour(rs.getString("day"), rs.getString("start"), rs.getString("end"), rs.getString("place")));
            }
        } catch (Exception e) {

        }

        return office_hours;
    }

    public List<TimeTableCell> GetStudentTimeTable(int student_id) {
        List<TimeTableCell> timetablecells = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select hall_name, department_name, slot_number, day,user_name as staff_name, course_name,group_name,time_table.student_level from student_courses right join time_table on student_courses.course_id = time_table.course_id  left join staff on staff.staff_id = time_table.staff_id left join user on user.user_id = staff.user_id left join groups on groups.group_id = time_table.group_id left join course on course.course_id = time_table.course_id  left join time_table_slot on time_table_slot.id = time_table.slot_id  left join hall on hall.hall_id =  time_table_slot.hall_id left join department_courses on department_courses.course_id = time_table.course_id left join department on department.department_id =  department_courses.department_id where student_id = " + student_id;
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                timetablecells.add(new TimeTableCell(rs.getString("day"), rs.getInt("slot_number"), rs.getString("staff_name"), rs.getString("course_name"), rs.getString("hall_name"), rs.getString("department_name"), rs.getString("group_name"), rs.getInt("student_level")));
            }
        } catch (Exception e) {
        }

        return timetablecells;
    }

    public List<TimeTableCell> GetStaffTimeTable(int staff_id) {
        List<TimeTableCell> timetablecells = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select hall_name, department_name, slot_number, day,user_name as staff_name, course_name,group_name,time_table.student_level from student_courses right join time_table on student_courses.course_id = time_table.course_id  left join staff on staff.staff_id = time_table.staff_id left join user on user.user_id = staff.user_id left join groups on groups.group_id = time_table.group_id left join course on course.course_id = time_table.course_id  left join time_table_slot on time_table_slot.id = time_table.slot_id  left join hall on hall.hall_id =  time_table_slot.hall_id left join department_courses on department_courses.course_id = time_table.course_id left join department on department.department_id =  department_courses.department_id where time_table.staff_id = " + staff_id;
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                timetablecells.add(new TimeTableCell(rs.getString("day"), rs.getInt("slot_number"), rs.getString("staff_name"), rs.getString("course_name"), rs.getString("hall_name"), rs.getString("department_name"), rs.getString("group_name"), rs.getInt("student_level")));
            }
        } catch (Exception e) {
        }

        return timetablecells;
    }

    public List<TimeTableCell> GetGroupTimeTable(int department_id, int level, int group_id) {
        List<TimeTableCell> timetablecells = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select hall_name, department_name, slot_number, day,user_name as staff_name, course_name,group_name,time_table.student_level from student_courses right join time_table on student_courses.course_id = time_table.course_id  left join staff on staff.staff_id = time_table.staff_id left join user on user.user_id = staff.user_id left join groups on groups.group_id = time_table.group_id left join course on course.course_id = time_table.course_id  left join time_table_slot on time_table_slot.id = time_table.slot_id  left join hall on hall.hall_id =  time_table_slot.hall_id left join department_courses on department_courses.course_id = time_table.course_id left join department on department.department_id =  department_courses.department_id where  time_table.group_id = "+group_id+" and time_table.student_level = "+level+" and department_courses.department_id = "+department_id;
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                timetablecells.add(new TimeTableCell(rs.getString("day"), rs.getInt("slot_number"), rs.getString("staff_name"), rs.getString("course_name"), rs.getString("hall_name"), rs.getString("department_name"), rs.getString("group_name"), rs.getInt("student_level")));
            }
        } catch (Exception e) {
        }

        return timetablecells;
    }
    
    public int Send_Message(int user_id,String email, String title, String message) {
        int result = -1;
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String query = "{call SP_send_email(?,?,?,?,?)}";
            CallableStatement callableStatement;
            callableStatement = mysql_connection.prepareCall(query);
             callableStatement.setInt(1,user_id);
            callableStatement.setString(2, email);
            callableStatement.setString(3, title);
            callableStatement.setString(4, message);
            callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);

            callableStatement.executeUpdate();
            result = callableStatement.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public List<Message> GetInbox(int user_id) {
        List<Message> messages = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = " select message_state,message.message_id, message_title, message_body, user_email as 'from' from inbox left join message on inbox.message_id = message.message_id left join outbox on outbox.message_id = inbox.message_id left join user on outbox.user_id = user.user_id where inbox.user_id = " + user_id;
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                messages.add(new Message(rs.getInt("message_state"), rs.getInt("message_id"), rs.getString("message_title"), rs.getString("message_body"),"",rs.getString("from")));
            }
        } catch (Exception e) {
        }

        return messages;
    }
     
    public List<Message> GetOutbox(int user_id) {
        List<Message> messages = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = " select message.message_id, message_title, message_body, user_email as 'to' from outbox left join message on outbox.message_id = message.message_id left join inbox on inbox.message_id = outbox.message_id left join user on inbox.user_id = user.user_id where outbox.user_id = " + user_id;
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {
                messages.add(new Message(0, rs.getInt("message_id"), rs.getString("message_title"), rs.getString("message_body"),rs.getString("to"),""));
            }
        } catch (Exception e) {
        }

        return messages;
    }
    
    public List<Reservation> GetAllReservation(){
    
     List<Reservation> reservations = new ArrayList<>();
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }

            String query = "select reservation_id,slot_id,slot_number, hall_name, reservation_purpose, DATE_FORMAT(reservation_date, '%d/ %m /%Y') AS res_date ,reservation_date,level,reservation.group_id, group_name,reservation.user_id,user_name,user_email, reservation.department_id, department_name,day, start_hr,end_hr,user_role from reservation left join department on department.department_id = reservation.department_id left join time_table_slot on time_table_slot.id = reservation.slot_id left join groups on groups.group_id = reservation.group_id left join user on user.user_id = reservation.user_id left join hall on hall.hall_id = time_table_slot.hall_id where reservation_state = 1 having reservation_date > now()";
            Statement st;
            st = (Statement) mysql_connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            while (rs.next()) {   
                Reservation reservation = new Reservation();
                reservation.group = new Group(rs.getInt("group_id"), rs.getString("group_name"));
                reservation.department = new Department(rs.getInt("department_id"), rs.getString("department_name"));
                reservation.reservation_id =rs.getInt("reservation_id");
                reservation.slot = new TimeTableSlot(rs.getInt("slot_id"), 0,rs.getString("day"), rs.getString("start_hr"), rs.getString("end_hr"), true, rs.getInt("slot_number"));
                reservation.slot.hall_name = rs.getString("hall_name");
                reservation.level = rs.getInt("level");
                reservation.user_id = rs.getInt("user_id");
                reservation.email = rs.getString("user_email");
                reservation.name = rs.getString("user_name");
                reservation.purpose =rs.getString("reservation_purpose");
                reservation.date=rs.getString("res_date");
                reservations.add(reservation);
            }
        } catch (Exception e) {
        }

        return reservations;
    
    }
    
    public void Delete_Reservation(int reservation_id){
        try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String deleteSQL = "DELETE FROM reservation WHERE reservation_id=?";
            PreparedStatement preparedStatement = mysql_connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, reservation_id);
            // execute delete SQL stetement
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void Refuse_reservation(int reservation_id,int user_id, String email){
        Send_Message(user_id, email, "Your Reservation has been canceled", "Dear, \n we apologize we the slot you choose is reserved to other members \n thanks");
        Delete_Reservation(reservation_id);
        
    }
    
    public void Accept_Reservation(int reservation_id,int user_id, String email, int group_id, int department_id, int level, String message){
     try {
            if (mysql_connection == null) {
                mysql_connection = Create_database_connection("e-com_database", "admin", "root");
            }
            String query = "{call SP_Accept_Reservation(?,?,?,?,?,?,?)}";
            CallableStatement callableStatement;
            callableStatement = mysql_connection.prepareCall(query);
            callableStatement.setInt(1,reservation_id);
            callableStatement.setInt(2,user_id);
            callableStatement.setString(3, email);
            callableStatement.setInt(4,group_id);
            callableStatement.setInt(5,department_id);
            callableStatement.setInt(6,level);
            callableStatement.setString(7, message);
            callableStatement.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
