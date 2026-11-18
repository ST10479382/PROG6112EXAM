package com.mycompany.question1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class test {

    private Product product;

    @BeforeEach
    public void setUp() {
        // Sample data from the question paper
        String[] productNames = {
            "Cozy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };

        int[][] salesData = {
            {25, 18, 20},  // Cozy Nights
            {15, 10, 12},  // Restonic
            {18, 15, 20}   // Sealy
        };

        product = new Product(productNames, salesData);
    }

    // ========== Unit Test 1 ==========
    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        int expected = 153;
        int actual = product.GetTotalProductsSold();

        assertEquals(expected, actual, 
            "GetTotalProductsSold should return 153");
    }

    // ========== Unit Test 2 ==========
    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        double expected = 17.0;
        double actual = product.GetAverageProductSold();

        assertEquals(expected, actual, 0.001, 
            "GetAverageProductSold should return 17.0");
    }
}