# GLS Package Tracking System – Part 2 🚚📦

This exercise extends the GLS Package Tracking System with more advanced entity relationships and functionality. You will introduce **locations** and **shipments** to represent how packages move between different places.

---

## 📖 Problem Description
When a package is sent from one location to another, it is considered a **shipment**:

- A **shipment** is the movement of a package between two locations.
- Each shipment belongs to **exactly one package**, **one source location**, and **one destination location**.
- A package can have **multiple shipments**.

**Example:**  
A package is sent from **Copenhagen** to **Aarhus**. The journey involves **four shipments** and **five locations** in total.

---

## 📝 Requirements

### 1. New Entity: `Location`
Represents a physical location where a package can be sent from or to.

**Attributes:**
- `id` (auto-generated primary key)
- `latitude` (`Double`)
- `longitude` (`Double`)
- `address` (`String`)

---

### 2. New Entity: `Shipment`
Represents the movement of a package between two locations.

**Attributes:**
- `id` (auto-generated primary key)
- `package` (Many-to-One relationship with `Package`)
- `sourceLocation` (Many-to-One relationship with `Location`)
- `destinationLocation` (Many-to-One relationship with `Location`)
- `shipmentDateTime` (`DateTime`)

---

### 3. Relationships
- A **Package** can have multiple **Shipments** (One-to-Many).
- Each **Shipment**:
    - belongs to one **Package**
    - has one **source Location**
    - has one **destination Location**

---

## ⚙️ Implementation Steps

1. **Design the class diagram**
    - Visualize entities and relationships before implementation.
    - Optional: make it bidirectional if needed for navigation both ways.

2. **Extend Entities**
    - Add `Location` and `Shipment` entities with JPA annotations.

3. **Update DAO Layer**
    - Create new DAO classes for `Location` and `Shipment`.
    - Implement CRUD operations:
        - Start with **read** operations (`findById`, `findAll`).
        - Then move on to **create**, **update**, and **delete**.

4. **Testing**
    - Write **JUnit integration tests** to verify the new features and relationships.

---

## ✅ Expected Outcome
By completing Part 2, you will:
- Gain experience designing and managing **complex entity relationships** with JPA.
- Understand how to implement **CRUD operations** for new entities.
- Learn to write **integration tests** that ensure system correctness.

