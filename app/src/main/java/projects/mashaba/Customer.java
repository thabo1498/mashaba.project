package projects.mashaba;

public class Customer {
    String names,phoneNumber,itemPurchased;
    Double totalAmountOwed,depositPercentage,depositAmount,currentAmountOwed;

    //default constructor
    public Customer() {
        this("name surname","000 000 0000","itemname",0.0,0.0,0.0,0.0);
    }
    //parameterised constructor
    public Customer(String names, String phoneNumber, String itemPurchased, Double totalAmountOwed, Double depositPercentage, Double depositAmount, Double currentAmountOwed) {
        this.names = names;
        this.phoneNumber = phoneNumber;
        this.itemPurchased = itemPurchased;
        this.totalAmountOwed = totalAmountOwed;
        this.depositPercentage = depositPercentage;
        this.depositAmount = depositAmount;
        this.currentAmountOwed = currentAmountOwed;
    }

    public String getNames() {
        return names;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getItemPurchased() {
        return itemPurchased;
    }

    public Double getTotalAmountOwed() {
        return totalAmountOwed;
    }

    public Double getDepositPercentage() {
        return depositPercentage;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public Double getCurrentAmountOwed() {
        return currentAmountOwed;
    }

    public void setNames(String names) {
        if (names.contains(" ")){
            int spacePos = names.indexOf(" ");
            if (names.substring(0,spacePos).length()>3 && names.substring(spacePos + 1).length()>3){
                this.names = names;
            }else{
                throw new IllegalArgumentException("Both name and Surname must be more than 3 characters");
            }
        }else{
            throw new IllegalArgumentException("names must be separated by a space");
        }

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setItemPurchased(String itemPurchased) {
        this.itemPurchased = itemPurchased;
    }

    public void setTotalAmountOwed(Double totalAmountOwed) {
        this.totalAmountOwed = totalAmountOwed;
    }

    public void setDepositPercentage(Double depositPercentage) {
        this.depositPercentage = depositPercentage;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setCurrentAmountOwed(Double currentAmountOwed) {
        this.currentAmountOwed = currentAmountOwed;
    }

    @Override
    public String toString() {
        return
                "Names: " + names + "\n" +
                "Contacts: " + phoneNumber + "\n" +
                "Goods purchased: " + itemPurchased + "\n" +
                "Total amount: R" + totalAmountOwed + "\n"+
                "Deposit percentage: " + depositPercentage +"%" +"\n"+
                "Amount deposited: R" + depositAmount +"\n"+
                "Outstanding Balance: R" + currentAmountOwed;
    }
}
