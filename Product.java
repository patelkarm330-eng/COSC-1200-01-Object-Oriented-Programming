/*
 Name: Karm Patel
 Program name: Product
 Date: 28 March 2026
 Description: Making product add to program.
 */
public class Product {

         // 8+ digits
        private String sku;
        // not blank
        private String name;
        // >= 0
        private double unitCost;
        // >= 0
        private double salePrice;
        // >= 0
        private int quantityOnHand;
        // >= 0
        private int quantityNeeded;
        // no validation
        private String specialInstructions; // no validation

        // Default constructor
        public Product() {
            this("00000000", "Unknown", 0.0, 0.0, 0, 0, "");
        }

        // Parameterized constructor
        public Product(String sku, String name, double unitCost, double salePrice,
                       int quantityOnHand, int quantityNeeded, String specialInstructions) {

            setSku(sku);
            setName(name);
            setUnitCost(unitCost);
            setSalePrice(salePrice);
            setQuantityOnHand(quantityOnHand);
            setQuantityNeeded(quantityNeeded);
            this.specialInstructions = specialInstructions;
        }

        // Getters and setters
        public final String getSku() { return sku; }
        public final void setSku(String sku) {
            if (sku == null || !sku.matches("\\d{8,}")) {
                throw new IllegalArgumentException("SKU must be at least 8 digits.");
            }
            this.sku = sku;
        }

        // Returns the product name
        public final String getName() { return name; }
        public final void setName(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank.");
            }
            this.name = name;
        }

        // Returns the unit cost
        public final double getUnitCost() { return unitCost; }
        // Sets the unit cost
        public final void setUnitCost(double unitCost) {
            if (unitCost < 0) {
                throw new IllegalArgumentException("Unit cost must be >= 0.");
            }
            this.unitCost = unitCost;
        }

        // Returns the sale price
        public final double getSalePrice() { return salePrice; }
        // Sets the sale price
        public final void setSalePrice(double salePrice) {
            if (salePrice < 0) {
                throw new IllegalArgumentException("Sale price must be >= 0.");
            }
            this.salePrice = salePrice;
        }

       // Returns the quantity on hand
        public final int getQuantityOnHand() { return quantityOnHand; }
        // Sets the quantity on hand
        public final void setQuantityOnHand(int quantityOnHand) {
            if (quantityOnHand < 0) {
                throw new IllegalArgumentException("Quantity on hand must be >= 0.");
            }
            this.quantityOnHand = quantityOnHand;
        }

        // Returns the quantity needed
        public final int getQuantityNeeded() { return quantityNeeded; }
        // Returns the quantity needed
        public final void setQuantityNeeded(int quantityNeeded) {
            if (quantityNeeded < 0) {
                throw new IllegalArgumentException("Quantity needed must be >= 0.");
            }
            this.quantityNeeded = quantityNeeded;
        }

        // Returns special instructions
        public final String getSpecialInstructions() { return specialInstructions; }
        // Sets special instructions
        public final void setSpecialInstructions(String specialInstructions) {
            this.specialInstructions = specialInstructions;
        }

        // Returns a formatted string of product details
        @Override
        public String toString() {
            return "SKU: " + sku +
                    "\nProduct Name: " + name +
                    "\nUnit Cost: $" + unitCost +
                    "\nSale Price: $" + salePrice +
                    "\nQuantity on hand: " + quantityOnHand +
                    "\nQuantity needed: " + quantityNeeded +
                    "\nSpecial Instructions: " + specialInstructions;
        }
    }


