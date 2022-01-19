package objects.ch07_objects_decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

// 급여관리시스템
public class Main {

    static final List<String> names = Arrays.asList("문상후", "홍길동", "가나다");
    static final List<Double> pays = Arrays.asList(500d, 400d, 300d);

    // 직원의 급여를 계산하자.
    public static void main(String[] args) throws IOException {
        String oper = args[0];
        String name = args[1];

        switch (oper) {
            case "pay" :
                calculatePay(name);
                break;
            case "basePays" :
                sumOfBasePays();
                break;
        }
    }

    private static void sumOfBasePays() {
        double result = 0;
        for(double pay : pays) {
            result += pay;
        }
        System.out.printf("직원 총 급여 : %.0f", result);

    }

    private static void calculatePay(String name) throws IOException {
        double taxRate = getTaxRate();
        double pay = calculatePayFor(name, taxRate);
        describeResult(name, pay);
    }

    private static double getTaxRate() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("세율을 입력하세요 : ");
        String taxRate = br.readLine();

        return Double.parseDouble(taxRate);
    }

    private static double calculatePayFor(String name, double taxRate) {
        double basePay = pays.get(names.indexOf(name));

        return basePay - (basePay * taxRate);
    }

    private static void describeResult(String name, double pay) {
        System.out.printf("이름 : %s, 급여 : %.0f", name, pay);
    }

}
