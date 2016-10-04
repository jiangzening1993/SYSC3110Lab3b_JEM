
/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prof {
	private String name;
	private Date midtermDate;
	private ArrayList<Student> students;
	private TeachingAssistant ta;
	private List<CourseListener> courseListeners;

	public Prof(String aName) {
		this.name = aName;
		this.students = new ArrayList<Student>();
		courseListeners = new ArrayList<CourseListener>();
	}

	public Date getMidterm() {
		return this.midtermDate;
	}

	public String getName() {
		return this.name;
	}
	
	public void setMidterm(Date date) {
		this.midtermDate = date;
		CourseEvent e = new CourseEvent(this);
		for (CourseListener cl : courseListeners) {
			cl.midtermAnnounced(e);
		}

	}
	public void postponeMidterm(Date date) {
		this.midtermDate = date;
		CourseEvent e = new CourseEvent(this);
		for (CourseListener cl : courseListeners) {
			cl.midtermPostponed(e);
		}
	}

	public synchronized void addCourseListener(CourseListener cl) {
		courseListeners.add(cl);
	}

	public synchronized void removeCourseListener(CourseListener cl) {
		courseListeners.remove(cl);
	}

	public static void main(String[] args) {

		Prof p = new Prof("Babak");
		Student s = new Student("Homer");
		Student s2 = new Student("Bart");
		TeachingAssistant ta = new TeachingAssistant("Michael");

		p.addCourseListener(s);
		p.addCourseListener(s2);
		p.addCourseListener(ta);

		Date midterm = new Date();
		p.setMidterm(midterm);

		p.postponeMidterm(new Date(midterm.getTime() + 1000000000));
	}

}