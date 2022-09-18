import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner (System.in);
        // 創建洗衣系統和訂單系統
        LaundrySystem laundrySystem = LaundrySystem.getInstance();
        OrderSystem orderSystem = OrderSystem.getInstance();

        while (true) {
            if (sc.nextLine().equals("exit")) {
                break;
            }
            // 呈現用戶菜單
        }

    }
}