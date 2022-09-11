package com.example.bli.mcalc;

public class MortgageModel{
    private double principle;
    private int amortization;
    private double interest;
    public MortgageModel(String p, String a, String i){
        this.principle=Double.parseDouble(p);
        this.amortization=Integer.parseInt(a);
        this.interest=Double.parseDouble(i);
    }
public String computePayment(){
        double r = (this.interest/100)/12;
        int n = this.amortization*12;

        double fin = (r*this.principle)/(1-Math.pow((1+r), 0-n));

        String result = String.format("$%.2f", fin);
        return result;
    }
}