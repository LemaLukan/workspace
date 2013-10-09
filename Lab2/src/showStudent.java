import javax.swing.JOptionPane;


public class showStudent {
	public static void main(String[] args) {
		Student newStudent;
		String name;
		int id;
		int grades[] = new int[5];
		name = JOptionPane.showInputDialog( "Enter Student name:" );
		id = Integer.parseInt(JOptionPane.showInputDialog( "Enter id number:" ));
		
		for (int i =0; i<5; i++) {
			grades[i] = Integer.parseInt(JOptionPane.showInputDialog( "Enter course grade point " + (i+1) + ":" ));
			
		}
		newStudent = new Student(name,id);
		newStudent.setGrade(grades[0], 0);
		newStudent.setGrade(grades[1], 1);
		newStudent.setGrade(grades[2], 2);
		newStudent.setGrade(grades[3], 3);
		newStudent.setGrade(grades[4], 4);
		newStudent.calcTotalLetters();
        JOptionPane.showMessageDialog( null, "The student name and id is " + newStudent.name + " " + newStudent.id + "\nThe GPA is " + newStudent.calcAvg() +
                "\nfirst course recieved a GP of " + newStudent.grades[0] + " which is in letter grades an " + newStudent.getLetter(newStudent.grades[0])+
                "\nsecond course recieved a GP of " + newStudent.grades[1] + " which is in letter grades an " + newStudent.getLetter(newStudent.grades[1])+
                "\nthird course recieved a GP of " + newStudent.grades[2] + " which is in letter grades an " + newStudent.getLetter(newStudent.grades[2])+
                "\nfourth course recieved a GP of " + newStudent.grades[3] + " which is in letter grades an " + newStudent.getLetter(newStudent.grades[3])+
                "\nfifth course recieved a GP of " + newStudent.grades[4] + " which is in letter grades an " + newStudent.getLetter(newStudent.grades[4]) + 
                "\nThe student received " + newStudent.totalLetters[13] + " " + newStudent.getLetter(13) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[12] + " " + newStudent.getLetter(12) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[11] + " " + newStudent.getLetter(11) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[10] + " " + newStudent.getLetter(10) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[9] + " " + newStudent.getLetter(9) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[8] + " " + newStudent.getLetter(8) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[7] + " " + newStudent.getLetter(7) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[6] + " " + newStudent.getLetter(6) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[5] + " " + newStudent.getLetter(5) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[4] + " " + newStudent.getLetter(4) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[3] + " " + newStudent.getLetter(3) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[2] + " " + newStudent.getLetter(2) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[1] + " " + newStudent.getLetter(1) +"s"+ 
                "\nThe student received " + newStudent.totalLetters[0] + " " + newStudent.getLetter(0) +"s",
                "Results", JOptionPane.PLAIN_MESSAGE );
	}
}
