package objects.ch07_objects_decomposition.classType;

import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    String name;
    double basePay;

    abstract double calculatePay(double taxRate);
    abstract double monthlyBasePay();
}

class SalariedEmployee extends Employee {

    public SalariedEmployee(String name, double basePay) {
        this.name = name;
        this.basePay = basePay;
    }

    @Override
    double calculatePay(double taxRate) {
        return basePay - (basePay * taxRate);
    }

    @Override
    double monthlyBasePay() {
        return basePay;
    }
}

class HourlyEmployee extends Employee {
    int timeCard;

    public HourlyEmployee(String name, double basePay, int timeCard) {
        this.name = name;
        this.basePay = basePay;
        this.timeCard = timeCard;
    }

    @Override
    double calculatePay(double taxRate) {
        return (basePay * timeCard) - (basePay * timeCard) * taxRate;
    }

    @Override
    double monthlyBasePay() {
        return 0;
    }
}

public class AbstractMain {

    static List<Employee> employees = new ArrayList(){{
        add(new SalariedEmployee("직원A", 400d));
        add(new SalariedEmployee("직원B", 300d));
        add(new SalariedEmployee("직원C", 250d));
        add(new HourlyEmployee("아르바이트D", 1, 120));
        add(new HourlyEmployee("아르바이트E", 1, 120));
        add(new HourlyEmployee("아르바이트F", 1, 120));
    }};

    public static void main(String[] args) {
        sumOfBasePay();
    }

    private static void sumOfBasePay() {
        int result = 0;

        for(Employee employee : employees) {
            result += employee.monthlyBasePay();
        }

        System.out.printf("총 급여 : " + result);
    }

}
