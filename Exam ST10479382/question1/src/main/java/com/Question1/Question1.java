package com.mycompany.question1;

import java.util.Scanner;

// ==================== INTERFACE ====================
interface IProduct {
    int GetTotalProductsSold();
    double GetAverageProductSold();
    String GetMostPopularProduct();
}

// ==================== PRODUCT CLASS ====================
class Product implements IProduct {

    private String[] productNames;   // 1D Array
    private int[][] salesData;       // 2D Array

    public Product(String[] productNames, int[][] salesData) {
        this.productNames = productNames;
        this.salesData = salesData;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < salesData.length; i++) {
            for (int j = 0; j < salesData[i].length; j++) {
                total += salesData[i][j];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        return (double) GetTotalProductsSold() / (salesData.length * salesData[0].length);
    }

    @Override
    public String GetMostPopularProduct() {
        int highest = -1;
        int index = 0;

        for (int i = 0; i < salesData.length; i++) {
            int sum = 0;
            for (int j = 0; j < salesData[i].length; j++) {
                sum += salesData[i][j];
            }
            if (sum > highest) {
                highest = sum;
                index = i;
            }
        }
        return productNames[index] + "(" + highest + ")";
    }
}

// ==================== PRINT PRODUCT CLASS ====================
class PrintProduct {

    private IProduct product;

    public PrintProduct(IProduct product) {
        this.product = product;
    }

    public void printTotalProductSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void printAverageProductSales() {
        System.out.println("AVERAGE PRODUCT SALES: " + (int) product.GetAverageProductSold());
    }

    public void printMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    public void printReport() {
        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("****************************************");
        printTotalProductSales();
        printAverageProductSales();
        printMostPopularProduct();
    }
}

// ==================== MAIN CLASS ====================
public class Question1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1D Array
        String[] productNames = {
            "Cozy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };

        // 2D Array
        int[][] salesData = new int[3][3];

        // Input
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter number of sales for month " + (j + 1)
                        + " for " + productNames[i] + ": ");
                salesData[i][j] = sc.nextInt();
            }
        }

        // Create objects
        Product product = new Product(productNames, salesData);
        PrintProduct printer = new PrintProduct(product);

        // Print report
        printer.printReport();

        // ========== SIMPLE UNIT TEST (Q.1.4) ==========
        System.out.println("\n----- UNIT TESTS -----");
        testTotalSales(product);
        testAverageSales(product);

        sc.close();
    }

    // Unit Test 1
    public static void testTotalSales(Product product) {
        int expected = 153;
        int actual = product.GetTotalProductsSold();
        if (actual == expected) {
            System.out.println("GetTotalProductSales_WithValidData_ReturnsCorrectValue → PASSED");
        } else {
            System.out.println("GetTotalProductSales_WithValidData_ReturnsCorrectValue → FAILED");
        }
    }

    // Unit Test 2
    public static void testAverageSales(Product product) {
        double expected = 17.0;
        double actual = product.GetAverageProductSold();
        if (Math.abs(actual - expected) < 0.001) {
            System.out.println("GetAverageSales_WithValidData_ReturnsCorrectValue → PASSED");
        } else {
            System.out.println("GetAverageSales_WithValidData_ReturnsCorrectValue → FAILED");
        }
    }
}