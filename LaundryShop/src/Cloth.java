public class Cloth {

    private ShirtType type;
    private ShirtColor color;
    private ShirtSize size;
    private boolean isCleaned;

    public ShirtType getType() {
        return type;
    }

    public ShirtSize getSize() {
        return size;
    }

    public boolean isCleaned() {
        return isCleaned;
    }

    public void setCleaned(boolean cleaned) {
        isCleaned = cleaned;
    }

    /** 衣服類型 */
    public enum ShirtType {TShirt, Jacket, PoloShirt}
    /** 衣服顏色 */
    public enum ShirtColor {Red, Green, Grey, Blue, Yellow}
    /** 衣服尺寸 */
    public enum ShirtSize {S, M, L, XL, XXL}
}

