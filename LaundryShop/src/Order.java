import java.time.LocalDate;

/** 洗衣訂單類別，包含客戶姓名、衣服清單、取件日期、付款狀態等*/
public class Order {
    private static final int ORDER_SLA = 1;
    private static int SERIAL_NUMBER = 1;
    private int id;
    private String customerName;
    private Cloth[] clothesToClean;
    private boolean isPaid;

    private boolean isFullyWashed;
    private LocalDate pickupDate;

    public Order(String customerName, Cloth[] clothesToClean) {
        id = SERIAL_NUMBER++;
        pickupDate = java.time.LocalDate.now().plusDays(ORDER_SLA);
        this.customerName = customerName;
        this.clothesToClean = clothesToClean;
        isPaid = false;
        isFullyWashed = false;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setFullyWashed(boolean fullyWashed) {
        isFullyWashed = fullyWashed;
    }

    public LocalDate getPickupDate() {
        try {
            return pickupDate;
        } catch (Exception e) {
            System.out.println("訂單取件日期未定。");
            return null;
        }
    }

    public Cloth[] getClothesToClean() {
        return clothesToClean;
    }

    /** 計算訂單總費用 */
    public int calculateBill() {
        int totalAmount = 0;
        for (int i = 0; i < clothesToClean.length; i++) {
            Cloth cloth = clothesToClean[i];
            if (cloth.isCleaned()) {
                // 只有洗過的衣服需要付款
                Cloth.ShirtType shirtType = cloth.getType();
                totalAmount += (baseCostOfClotheType(shirtType) + (
                        costMultiplierOfClotheType(shirtType) * numericValueOfClotheSize(cloth.getSize())
                ));
            }
        }
        System.out.println("計算訂單當付金額");
        return totalAmount;
    }

    /** 按衣服類型回傳對應遞增的附加費用 */
    private int costMultiplierOfClotheType(Cloth.ShirtType shirtType) {
        int multiplier = 0;
        switch (shirtType) {
            case TShirt, PoloShirt -> multiplier = 2;
            case Jacket -> multiplier = 5;
        }
        return multiplier;
    }

    /** 按衣服類型回傳對應基礎單價 */
    private int baseCostOfClotheType(Cloth.ShirtType shirtType) {
        int baseCost = 0;
        switch (shirtType) {
            case TShirt -> baseCost = 20;
            case Jacket -> baseCost = 50;
            case PoloShirt -> baseCost = 10;
        }
        return baseCost;
    }

    /** 按衣服尺寸回傳對應值 */
    private int numericValueOfClotheSize(Cloth.ShirtSize shirtSize) {
        int numericValueOfSize = 0;
        switch (shirtSize) {
            case S -> numericValueOfSize = 1;
            case M -> numericValueOfSize = 2;
            case L -> numericValueOfSize = 3;
            case XL -> numericValueOfSize = 4;
            case XXL -> numericValueOfSize = 5;
        }
        return numericValueOfSize;
    }
}
