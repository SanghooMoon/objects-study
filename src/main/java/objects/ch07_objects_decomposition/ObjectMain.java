package objects.ch07_objects_decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Employee {
    String name;
    double basePay;
    boolean hourly;
    int timeCard;

    public Employee(String name, double basePay, boolean hourly, int timeCard) {
        this.name = name;
        this.basePay = basePay;
        this.hourly = hourly;
        this.timeCard = timeCard;
    }

    public double calculatePay(double taxRate){
        if(hourly) {
            return calculateHourlyPay(taxRate);
        }
        return calculateSalariedPay(taxRate);
    }

    private double calculateSalariedPay(double taxRate) {
        return basePay - (basePay * taxRate);
    }

    private double calculateHourlyPay(double taxRate) {
        return (basePay * timeCard) - (basePay * timeCard) * taxRate;
    }

    public double monthlyBasePay() {
        if(hourly) return 0;
        return basePay;
    }

}

public class ObjectMain {

    static List<Employee> employees = new ArrayList(){{
        add(new Employee("직원A", 400d, false, 0));
        add(new Employee("직원B", 300d, false, 0));
        add(new Employee("직원C", 250d, false, 0));
        add(new Employee("아르바이트D", 1d, true, 1));
        add(new Employee("아르바이트E", 1d, true, 1));
        add(new Employee("아르바이트F", 1d, true, 1));
    }};

    public static void main(String[] args) throws IOException {
        calculatePay("직원A");
        sumOfBasePays();
    }

    public static void calculatePay(String name) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("세율을 입력하세요 : ");
        String taxRate = br.readLine();
        double pay = 0.0d;

        for(Employee employee : employees) {
            if(employee.name.equals(name)) {
                pay = employee.calculatePay(Double.parseDouble(taxRate));
            }
        }

        describeResult(name, pay);
    }

    private static void describeResult(String name, double pay) {
        System.out.printf("이름 : %s, 급여 : %.0f\n", name, pay);
    }


    public static void sumOfBasePays() {
        double result = 0;

        for(Employee employee : employees) {
            result += employee.monthlyBasePay();
        }

        System.out.printf("직원 총 급여 : %.0f", result);
    }

}
