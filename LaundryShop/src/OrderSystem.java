/**
 * 訂單系統：
 * 用Singleton設計模式，限制程式內指向同一個訂單系統
 * 新建訂單
 * 取出訂單
 * 輸出指定客戶所需支付金額
 * 輸出目前所有訂單已付金額
 */
public class OrderSystem {
    private static final OrderSystem INSTANCE = new OrderSystem();
    // FIFO Queue
    private class Node{
        private Order data;
        private Node next;
        public Node(Order input) {
            this.data = input;
            this.next = null;
        }
    }

    private Node root;
    private Node tail;

    private OrderSystem(){}
    public static OrderSystem getInstance() {
        return INSTANCE;
    }

    public void addOrder(Order order) {
        if (root == null) {
            // 如果root為空
            root = new Node(order);
        } else {
            tail.next = new Node(order);
        }
        tail = root;
        System.out.println("新增訂單");
    }

    /**
     * 取出訂單（要從資料結構裡面取出）
     * @return
     */
    public Order retrieveOrder() {
        if (root == null) {
            return null;
        }
        Node retrievedOrder = root;
        root = root.next;
        System.out.println("取出訂單");
        return retrievedOrder.data;
    }

    public int getBillOfCustomer(String customer) {
        int customerBill = 0;
        Node temp = root;
        while (temp != null) {
            if (temp.data.getCustomerName().equals(customer)) {
                customerBill = temp.data.calculateBill();
            } else {
                temp = temp.next;
            }
        }
        System.out.println("完成客戶訂單金額計算");
        return customerBill;
    }

    public int getTotalPaidAmount() {
        int totalPaidAmount = 0;
        Node temp = root;
        while (temp != null) {
            if (temp.data.isPaid()) {
                // 如果該訂單已付款
                totalPaidAmount += temp.data.calculateBill();
            }
        }
        System.out.println("完成統計已付款總額");
        return totalPaidAmount;
    }


}
