
public class Student {
	String name;
	int id;
	int grades[];
	int totalLetters[];
	double average;
	public Student(String newname, int newid)
	{
		name = newname;
		id = newid;
		grades = new int[5];
		totalLetters = new int[14];
	}
	public double calcAvg()
	{
		double sum = 0.0;
		for (int i=0; i<5; i++)
		{
			sum += grades[i];
		}
		return sum/5;
	}
	public void setGrade(int grade, int position) {
		grades[position] = grade;
	}
	public void calcTotalLetters()
	{
		for (int i=0; i<13; i++){
			totalLetters[i] = 0;
		}
		for (int i=0; i<5; i++){
			if (grades[i] == 13) {
				totalLetters[13] += 1;
			}
			else if (grades[i] == 12) {
				totalLetters[12] += 1;
			}
			else if (grades[i] == 11) {
				totalLetters[11] += 1;
			}
			else if (grades[i] == 10) {
				totalLetters[10] += 1;
			}
			else if (grades[i] == 9) {
				totalLetters[9] += 1;
			}
			else if (grades[i] == 8) {
				totalLetters[8] += 1;
			}
			else if (grades[i] == 7) {
				totalLetters[7] += 1;
			}
			else if (grades[i] == 6) {
				totalLetters[6] += 1;
			}
			else if (grades[i] == 5) {
				totalLetters[5] += 1;
			}
			else if (grades[i] == 4) {
				totalLetters[4] += 1;;
			}
			else if (grades[i] == 3) {
				totalLetters[3] += 1;
			}
			else if (grades[i] == 2) {
				totalLetters[2] += 1;
			}
			else if (grades[i] == 1) {
				totalLetters[1] += 1;
			}
			else {
				totalLetters[0] += 1;
			}
		}
	}
	public String getLetter(int grade) {
		if (grade == 13) {
			return "A+";
		}
		else if (grade == 12) {
			return "A";
		}
		else if (grade == 11) {
			return "A-";
		}
		else if (grade == 10) {
			return "B+";
		}
		else if (grade == 9) {
			return "B";
		}
		else if (grade == 8) {
			return "B-";
		}
		else if (grade == 7) {
			return "C+";
		}
		else if (grade == 6) {
			return "C";
		}
		else if (grade == 5) {
			return "C-";
		}
		else if (grade == 4) {
			return "D+";
		}
		else if (grade == 3) {
			return "D";
		}
		else if (grade == 2) {
			return "D-";
		}
		else if (grade == 1) {
			return "F";
		}
		else {
			return "F-";
		}
	}
}
