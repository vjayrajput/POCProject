package app.boilerplate.utils;

public enum ShopStatus {
    CLOSE {
        @Override
        public String toString() {
            return "0";
        }
    },
    OPEN {
        @Override
        public String toString() {
            return "1";
        }
    },
    PRE_OPEN {
        @Override
        public String toString() {
            return "2";
        }
    }
}
