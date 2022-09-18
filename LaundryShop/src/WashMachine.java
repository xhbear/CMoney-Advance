/**
 * 洗衣機類別：
 * 用Singleton設計模式，限制程式內指向同一台洗衣機
 * 洗衣機負荷限制在10件衣服
 */
public class WashMachine {
    private static final int MAX_CAPACITY = 10;
    private static final WashMachine INSTANCE = new WashMachine();
    private Cloth[] clothesToWash = new Cloth[MAX_CAPACITY];
    private int currentLoad = 0;

    private WashMachine(){}
    public static WashMachine getInstance() {
        return INSTANCE;
    }

    public boolean addClothesToWash(Cloth cloth) {
        if (currentLoad < MAX_CAPACITY) {
            clothesToWash[currentLoad++] = cloth;
            cloth.setCleaned(true);
            System.out.println("成功放入衣服");
            return true;
        }
        System.out.println("洗衣機負荷滿載，衣服未能放入清洗");
        return false;
    }

    public Cloth retrieveClothe() {
        if (currentLoad == 0) {
            System.out.println("洗衣機裡面目前沒有衣服");
            return null;
        }
        Cloth lastCloth = clothesToWash[currentLoad-1];
        clothesToWash[currentLoad-1] = null;
        currentLoad--;
        System.out.println("成功取出衣服");
        return lastCloth;
    }

    public void clearWashPileLoad() {
        for (int i = 0; i < currentLoad; i++) {
            clothesToWash[i] = null;
        }
        System.out.println("清空洗衣機");
    }
}
