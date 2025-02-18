package lab_1;

public class lab_1 {
    public static void main(String[] args) {
        MarketModel model = new MarketModel();
        System.out.print("-------------- \033[1;31mMARKET MODEL\033[0m --------------\n");
        if (!model.inputValues()) {
            return;
        }
        model.searchBalanceQuantity();
        model.printValues();
    }
}

