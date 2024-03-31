package ProblemSets;

public class ProblemSet3B {
    public static void main(String[] args) {
    }

}

// YieldCalculation interface
interface YieldCalculation {
    double yieldToMaturity (Bond bond);
}

// Bond Class
class Bond {
    private double sellingPrice;
    private double faceValue;
    private double interestPayment;
    private double duration;
    private YieldCalculation yieldCalculation;

    // default constructor
    private Bond(double sellingPrice, double faceValue, double interestPayment, double duration)
            throws IllegalArgumentException{
        if (sellingPrice <= 0 || faceValue <= 0 || duration <= 0 || interestPayment < 0){
            throw new IllegalArgumentException("Requirement (c): Invalid parameters for bond");
        }

        this.sellingPrice = sellingPrice;
        this.faceValue = faceValue;
        this.interestPayment = interestPayment;
        this.duration = duration;
    }

    // TODO (b) : getter methods for instance variables
    public double getSellingPrice(){
        return sellingPrice;
    }
    public double getFaceValue(){
        return faceValue;
    }
    public double getInterestPayment(){
        return interestPayment;
    }
    public double getDuration(){
        return duration;
    }

    // TODO (d) : setter method for instance variable yieldCalculation for objects that implement
    //            YieldCalculation interface
    public void setYieldCalculation(YieldCalculation yieldCalculation){
        this.yieldCalculation = yieldCalculation;
    }

    // TODO (a) : BondBuilder
    public static class BondBuilder{
        private double sellingPrice = 1000.00;
        private double faceValue = 1000.00;
        private double interestPayment = 10.0;
        private double duration = 1.0;

        public BondBuilder setSellingPrice(double sellingPrice){
            this.sellingPrice = sellingPrice;
            return this;
        }

        public BondBuilder setFaceValue(double faceValue){
            this.faceValue = faceValue;
            return this;
        }

        public BondBuilder setInterestPayment(double interestPayment){
            this.interestPayment = interestPayment;
            return this;
        }

        public BondBuilder setDuration(double duration){
            this.duration = duration;
            return this;
        }

        public Bond createBond(){
            return new Bond(sellingPrice, faceValue, interestPayment, duration);
        }
    }

    // TODO (e) : calculateYTM()
    public double calculateYTM(){
        if (yieldCalculation == null) {
            return 0.01;
        }
        return yieldCalculation.yieldToMaturity(this);
    }
}

// TODO (f) : class ZeroCouponYield & WithCouponYield
class ZeroCouponYield implements YieldCalculation{

    @Override
    public double yieldToMaturity(Bond bond) {
        double faceValue = bond.getFaceValue();
        double sellingPrice = bond.getSellingPrice();
        double duration = bond.getDuration();

        return (Math.pow(faceValue / sellingPrice, 1 / duration) - 1);
    }
}

class WithCouponYield implements YieldCalculation{
    @Override
    public double yieldToMaturity(Bond bond) {
        double interestPayment = bond.getInterestPayment();
        double faceValue = bond.getFaceValue();
        double duration = bond.getDuration();
        double sellingPrice = bond.getSellingPrice();

        return calculateYTM(interestPayment, faceValue, duration, sellingPrice);
    }

    private double presentValue(double interestPayment, double faceValue, double ytm, double duration){
        double pv = 0.0;
        for (int t = 1; t <= duration; t++) {
            pv += interestPayment / Math.pow(1 + ytm, t);
        }
        pv += faceValue / Math.pow(1 + ytm, duration);
        return pv;
    }

    private double calculateYTM(double interestPayment, double faceValue, double duration, double sellingPrice){
        double ytm = 0.0;
        double lowerBound = 0.0;
        double upperBound = 1.0;

        while (Math.abs(sellingPrice - presentValue(interestPayment, faceValue, ytm, duration)) > 0.0001) {
            ytm = (lowerBound + upperBound) / 2.0;
            if (presentValue(interestPayment, faceValue, ytm, duration) < sellingPrice) {
                upperBound = ytm;
            }
            else {
                lowerBound = ytm;
            }
        }
        return ytm;
    }
}

class TestBond {
    public static void main(String[] args) {
        Bond.BondBuilder bondBuilder = new Bond.BondBuilder();
        Bond bond1 = bondBuilder.createBond();
        bond1.setYieldCalculation(new WithCouponYield());
        System.out.println("YTM for bond1: " + bond1.calculateYTM());

        Bond bond2 = bondBuilder.setDuration(1).setFaceValue(1000).setSellingPrice(900).setInterestPayment(0).createBond();
        bond2.setYieldCalculation(new ZeroCouponYield());
        System.out.println("YTM for bond2: " + bond2.calculateYTM());

    }
}