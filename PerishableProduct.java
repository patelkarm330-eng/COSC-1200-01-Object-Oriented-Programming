/*
 Name: Karm Patel
 Program name: PerishableProduct
 Date: 28 March 2026
 Description: PerishableProduct extends Product and adds an expiry date.
 */


import java.time.LocalDate;

    public class PerishableProduct extends Product {

        private LocalDate expiryDate;

        // Default constructor sets expiry date to today

        public PerishableProduct() {
            super();
            this.expiryDate = LocalDate.now();
        }

        // Full constructor for creating a perishable product
        public PerishableProduct(String sku, String name, double unitCost, double salePrice,
                                 int quantityOnHand, int quantityNeeded, String specialInstructions,
                                 LocalDate expiryDate) {

            super(sku, name, unitCost, salePrice, quantityOnHand, quantityNeeded, specialInstructions);
            this.expiryDate = expiryDate;
        }

        // Returns the expiry date
        public LocalDate getExpiryDate() { return expiryDate; }
        // Sets the expiry date
        public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

        // Returns product details including expiry date
        @Override
        public String toString() {
            return super.toString() + "\nExpiry Date: " + expiryDate;
        }
    }


