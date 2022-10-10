package chain_of_responsibility;

abstract class PurchasePower {
    protected final double base = 500;
    protected PurchasePower successor;

    public void setSuccessor(PurchasePower successor) { //에러 나왔을 때, 다음 책임자 임명
        this.successor = successor;
    }

    abstract public void processRequest(PurchaseRequest request);
}
