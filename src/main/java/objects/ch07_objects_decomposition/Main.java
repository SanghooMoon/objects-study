package objects.ch07_objects_decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// 급여관리시스템
public class Main {

    static final HashMap<String, Integer> employeeInfos = new HashMap() {
        {
            put("문상후", 500);
            put("홍길동", 400);
            put("가나다", 300);
        }
    };

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
        int result = 0;
        for(int pay : employeeInfos.values()) {
            result += pay;
        }
        System.out.printf("직원 총 급여 : %d", result);

    }

    private static void calculatePay(String name) throws IOException {
        int taxRate = getTaxRate();
        int pay = calculatePayFor(name, taxRate);
        describeResult(name, pay);
    }

    private static int getTaxRate() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("세율을 입력하세요 : ");
        String taxRate = br.readLine();

        return Integer.parseInt(taxRate);
    }

    private static int calculatePayFor(String name, int taxRate) {
        int basePay = employeeInfos.get(name);

        return basePay - (basePay * taxRate);
    }

    private static void describeResult(String name, int pay) {
        System.out.printf("이름 : %s, 급여 : %d", name, pay);
    }

}
