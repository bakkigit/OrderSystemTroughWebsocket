package model;

public class Person{
    private String computerNavn;
    private String Bestilling;
    private double total;


    public Person(String computerNavn, String bestilling, double total) {
        this.computerNavn = computerNavn;
        Bestilling = bestilling;
        this.total = total;
    }

    public String getComputerNavn() {
        return computerNavn;
    }

    public String getBestilling() {
        return Bestilling;
    }

    public double getTotal() {
        return total;
    }

    public void setComputerNavn(String computerNavn) {
        this.computerNavn = computerNavn;
    }

    public void setBestilling(String bestilling) {
        Bestilling = bestilling;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
