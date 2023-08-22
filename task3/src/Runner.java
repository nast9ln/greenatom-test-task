public class Runner {
    public static void main(String[] args) {
        SinglyLinkedList<Employee> employees = new SinglyLinkedList<>();
        employees.add(new Employee(1, "Ivanov", 10));
        employees.add(new Employee(2, "Petrov", 12));
        employees.add(new Employee(3, "Sidorov", 14));
        employees.add(new Employee(4, "Stuk", 4));
        employees.print();
        employees.reverse();
        employees.print();

    }
}
