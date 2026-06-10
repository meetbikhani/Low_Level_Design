package Entities;

public class coin {

    public enum coinType {
        QUARTER(25),
        DIME(10),
        NICKEL(5),
        PENNY(1);

        private final int value;

        coinType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private coinType type;

    public coin(coinType type) {
        this.type = type;
    }

    public int getValue() {
        return type.getValue();
    }
}