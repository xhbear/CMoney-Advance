import java.util.ArrayList;
import java.util.HashMap;

/**
 * 用Singleton設計模式，限制程式內指向同一個洗衣系統
 * 在這裡實現以訂單洗衣功能
 * 維護以客戶為單位的未完成訂單hash表
 */
public class LaundrySystem {
    private static final LaundrySystem INSTANCE = new LaundrySystem();
    WashMachine washMachine = WashMachine.getInstance();
    HashMap<String, ArrayList<Order>> orderWithUnfinishedJob = new HashMap<>();

    private LaundrySystem(){}
    public static LaundrySystem getInstance() {
        return INSTANCE;
    }

    /**
     * 傳入訂單驅動洗衣機依序洗衣服
     * 更新客戶訂單狀態
     * @param order 洗衣訂單
     */
    public void washByOrder(Order order) {
        Cloth[] clothesInOrder = order.getClothesToClean();
        int laundrySize = clothesInOrder.length;
        for (int i = 0; i < laundrySize; i++) {
            Cloth cloth = clothesInOrder[i];
            if (!cloth.isCleaned()) {
                // 衣服還沒洗
                if (!washMachine.addClothesToWash(cloth)) {
                    // 洗衣機放不下了
                    System.out.println("Wash loading halted due to max capacity!");
                    String customer = order.getCustomerName();
                    // 紀錄用戶及對應未完成的訂單
                    if (orderWithUnfinishedJob.containsKey(customer)) {
                        orderWithUnfinishedJob.get(customer).add(order);
                    } else {
                        ArrayList<Order> orderList = new ArrayList<>();
                        orderList.add(order);
                        orderWithUnfinishedJob.put(customer, orderList);
                    }
                    break;
                }
            }
            if (i == laundrySize-1) {
                // 如果訂單內容的衣服已全部洗完，更新對應flag
                if (cloth.isCleaned()) {
                    order.setFullyWashed(true);
                }
            }
        }
    }
}
