/*
 * package ebs.entity; import java.util.Scanner;
 * 
 * public class BillCalc {
 * 
 * public static double Home_bill(int unit) { int units=unit; double cost = 0;
 * 
 * if (units <= 100) { // First 100 units are free cost = 0; } else if (units <=
 * 200) { // Cost for units 101 to 200 is $2.25 per unit cost = (units - 100) *
 * 2.25; } else if (units <= 400) { // Cost for units 201 to 400 is $4.50 per
 * unit cost = 100 * 2.25 + (units - 200) * 4.50; }else if (units <= 500) { //
 * Cost for units 401 to 500 is $6 per unit cost = 100 * 2.25 + 200 * 4.50 +
 * (units - 400) * 6; } else if (units > 500 && units<= 600) { // Cost for units
 * greater than 500 is $6 per unit cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 +
 * (units - 500) *8; }else if (units > 600 && units <= 800) { // Cost for units
 * greater than 600 is $8 per unit cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 +
 * 100 * 8 + (units - 600) * 9; }else if (units > 800 && units <= 1000) { //
 * Cost for units greater than 800 is $10 per unit cost = 100 * 4.50 + 200 *
 * 4.50 + 100 * 6 + 100 * 8 + 200 * 9 + (units - 800) * 10; }else if (units >
 * 1000) { // Cost for units greater than 1000 is $11 per unit cost= 100 * 4.50
 * + 200 * 4.50 + 100 * 6 + 100 * 8 + 200 * 9 + 200 * 10 + (units - 1000) * 11;
 * }else { System.out.println("Please Visit Branch"); }
 * 
 * return cost; }
 * 
 * public static void main(String[] args) { // TODO Auto-generated method stub
 * System.out.println(Home_bill(1001)); }
 * 
 * }
 */

package ebs.entity;

import java.util.function.Function;

public class BillCalc {

    public static Function<Integer, Double> HomeBillLambda = units -> {
        double cost = 0;

        if (units <= 100) {
            cost = 0;
        } else if (units <= 200) {
            cost = (units - 100) * 2.25;
        } else if (units <= 400) {
            cost = 100 * 2.25 + (units - 200) * 4.50;
        } else if (units <= 500) {
            cost = 100 * 2.25 + 200 * 4.50 + (units - 400) * 6;
        } else if (units <= 600) {
            cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + (units - 500) * 8;
        } else if (units <= 800) {
            cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + 100 * 8 + (units - 600) * 9;
        } else if (units <= 1000) {
            cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + 100 * 8 + 200 * 9 + (units - 800) * 10;
        } else {
            cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + 100 * 8 + 200 * 9 + 200 * 10 + (units - 1000) * 11;
        }

        return cost;
    };

    public static void main(String[] args) {
        System.out.println(HomeBillLambda.apply(1001));
    }

	
}
