package Tester;

import static utils.StudentCollectionUtils.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

import com.app.core.Student;
import com.app.core.Subject;

public class Test1 {

	public static void main(String[] args) {

		// 4. Write a tester to print average of gpa of students for the specified
		// subject
		// eg : i/p : subject

		List<Student> populateList = populateList();

		Map<String, Student> populateMap = populateMap(populateList);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Subject..");
		Subject sub = Subject.valueOf(sc.next().toUpperCase());

		OptionalDouble average = populateMap.values().stream().filter(m -> m.getSubject() == sub)
				.mapToDouble(m -> m.getGpa()).average();
		if (average.isPresent()) {
			System.out.println(average.getAsDouble());
		} else {
			System.out.println("Empty");
		}
		System.out.println("------------------------------");

		//
		// 5 Print name of specified subject topper 
		System.out.println("");
		Optional<Student> max = populateMap.values().stream().filter(m -> m.getSubject() == sub)
				.max((s1, s2) -> ((Double) s1.getGpa()).compareTo(s2.getGpa()));
		if (max.isPresent()) {
			System.out.println(max.get().getName());
		} else {
			System.out.println("Empty");
		}
		System.out.println("------------------------------");

		// 6 Print no of failures for the specified subject chosen from user.
		// (gpa < 7 : failed case)
		long count = populateMap.values().stream().filter(m -> m.getSubject() == sub).filter(m -> m.getGpa() < 7)
				.count();

		System.out.println(count);

		System.out.println("------------------------------");

		// 7. Display student names of all the students , from a specified city.

		System.out.println("Enter City..");
		String city = sc.next();

		populateMap.values().stream().filter(m -> m.getAddress().getCity().equalsIgnoreCase(city))
				.forEach(m -> System.out.println(m.getName()));
		sc.close();

	}

}
