package by.htp.Group.model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;;

public class Group {

	private LinkedList<Student> students;
	private int studentCounter;

	public void setStudents(LinkedList<Student> students) {
		this.students = students;
	}

	public LinkedList<Student> getStudents() {
		return this.students;
	}

	// этот метод возвращает массив из возрастов всех студентов
	public LinkedList<Integer> getStudentsAge() {
		LinkedList<Integer> age = new LinkedList<>();
		for (Student stud : this.getStudents()) {
			age.add(stud.getAge());
		}
		return age;
	}

	public void setStudentAge(LinkedList<Integer> age) {
		ListIterator<Integer> litr = age.listIterator();
		ListIterator<Student> stLitr = this.getStudents().listIterator();
		while (litr.hasNext()) {
			stLitr.next().setAge(litr.next());
		}
	}

	// эта функция проверяет достаточно ли студентов в группе (больше или меньше
	// 5ти)
	// чтобы начать обучение
	public boolean groupStatus(Student[] students) {
		boolean status = false;
		for (int i = 0; i < 5; i++) {
			if (students[i] == null) {
				status = false;
			} else {
				status = true;
			}
		}
		if (status == false) {
			System.out.println("В группе меньше 5-ти студентов, добавьте студентов");
		} else {
			System.out.println("В группе 5 или больше студентов можно начинать обучение");
		}
		return status;
	}

	
	public void addStudent(Student student) {
		students.add(student);
	}

	// эта функция возвращает средний возраст студентов в группе
	public double getMedium() {
		double medium = 0;
		double ageSum = 0;
		for (Student stud : this.getStudents()) {
			ageSum += stud.getAge();
		}
		medium = ageSum / this.getStudents().size();
		return medium;
	}

	// эта функция возвращает количество студентов, поступивших в заданном году
	// заданный год вводится с консоли
	public int getStudentsNumberByStartDate() {
		int counter = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите год");
		int year = sc.nextInt();
		for (Student student : students) {
			if (student.getStartYear() == year) {
				counter++;
			}
		}
		System.out.println("Количество студентов, поступивших в " + year + " году = " + counter);
		return counter;
	}

	// эта функция создает массив равный по длине массиву студентов в котором
	// содержится количество студентов поступивших в тот же год, что и данный
	// (i-ый) студент, то есть количество студентов, поступивших вместе с ним
	public ArrayList<Integer> getYearCount() {
		Student[] students = this.getStudents().toArray(new Student[this.getStudents().size()]);
		Integer[] yearCount = new Integer[this.getStudents().size()];
		for (int i = 0; i < students.length; i++) {
			for (int j = 0; j < students.length; j++) {
				if (students[i].getStartYear() == students[j].getStartYear()) {
					yearCount[i]++;
				}
			}
		}
		return new ArrayList<Integer>(Arrays.asList(yearCount));
	}

	// эта функция возвращает максимальное количество студентов,
	// поступивших в один год
	public int getNumberStudentsMaxYear() {
		int number = 0;
		for (Integer x : this.getYearCount()) {
			if (number <= x) {
				number = x;
			}
		}
		return number;
	}

	// эта функция возвращает год, в который поступило максимальное количество
	// студентов
	public int getYearMaxStudents() {
		int year = 0;
		int counter = 0;
		int[] studCount = this.getYearCount();
		int num = this.getNumberStudentsMaxYear();
		for (int i = 0; i < this.getStudents().length; i++) {
			if (num == studCount[i]) {
				year = this.getStudents()[i].getStartYear();
			}
		}
		return year;
	}

	// сортировка списка студентов по возрасту в взрастающем порядке
	public void bubbleSortAsc() {
		for (int i = this.getStudents().length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (this.getStudents()[j].getAge() > this.getStudents()[j + 1].getAge()) {
					Student temp = this.getStudents()[j + 1];
					this.getStudents()[j + 1] = this.getStudents()[j];
					this.getStudents()[j] = temp;
				}
			}
		}
	}

	//TODO (сортировка плохо работает с массивом объектов-наследников, см задачу Biker) сортировка вставками в взрастающем порядке
	public void insertionSort() {
		int j, tempInt;
		Student temp;
		for (int i = 0; i < this.getStudents().length - 1; i++) {
			if (this.getStudentsAge()[i] > getStudentsAge()[i + 1]) {
				temp = this.getStudents()[i + 1];
				tempInt = this.getStudentsAge()[i + 1];
				this.getStudents()[i + 1] = this.getStudents()[i];
				j = i;
				while (j > 0 && tempInt < this.getStudentsAge()[j - 1]) {
					this.getStudents()[j] = this.getStudents()[j - 1];
					j--;
				}
			}
		}
	}

	// сортировка выбором в возрастающем порядке
	public void selectionSort() {
		int min, imin;
		Student temp;
		//int[] tempAge = new int[this.students.length];
		for (int i = 0; i < this.students.length; i++) {
			imin = i;
			min = this.getStudents()[i].getAge();
			for (int j = i + 1; j < this.students.length; j++) {
				if (this.students[j].getAge() < min) { // min or this.getStudentsAge()[imin]
					min = this.students[j].getAge();
					imin = j;
				}
			}
			// Swap the values
			if (i != imin && this.students[i] != null) {
				temp = this.getStudents()[i]; // [imin]
				this.students[i] = this.students[imin]; //
				this.students[imin] = temp;
			}
		}
	}
	
	// быстрая сортировка
	public void quickSort(int low, int high) {
//		int low = 0;
//		int high = students.length - 1;
        if (this.getStudentsAge().length == 0)
            return;//завершить выполнение если длина массива равна 0
        if (low >= high)
            return;//завершить выполнение если уже нечего делить
 
        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = this.getStudentsAge()[middle];
 
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (this.getStudentsAge()[i] < opora) {
                i++;
            }
            while (this.getStudentsAge()[j] > opora) {
                j--;
            }
            if (i <= j) {//меняем местами
                Student temp = this.getStudents()[i];
                this.getStudents()[i] = this.getStudents()[j];
                this.getStudents()[j] = temp;
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            this.quickSort(low, j);
        if (high > i)
            this.quickSort(i, high);
    }
}
