# InterTech POS System — Demo Script

---

## Introduction *(~30 seconds)*

> "Good [morning/afternoon]. I'd like to present **InterTech POS** — a Point of Sale system designed for an electronics and gadgets store. It's built using **Java Swing** for the desktop UI, **MySQL** as the database, and **Maven** for dependency management. Let me walk you through how it works."

---

## 1. Login Screen *(~30 seconds)*

> "The system starts with a login screen. It supports two roles — **Admin** and **Cashier** — each with different access levels. Authentication is secured using **BCrypt password hashing**."

- Type username: `admin` / password: `admin123`
- Click **LOG IN**

> "Admins have full access to all modules, while cashiers can only access the POS screen."

---

## 2. Dashboard *(~45 seconds)*

> "After logging in, the admin is taken to the **Dashboard**. At a glance, we can see four key metrics for today:"

Point to each card:
- **Today's Sales** — total revenue for the day
- **Transactions** — number of completed sales
- **Low Stock Alerts** — products running low
- **Total Products** — items in the system

> "Below that is the **Recent Transactions** table showing the latest sales with the cashier name, total, and payment method. There's also a **live clock** in the top right."

---

## 3. POS Screen *(~1 minute)*

> "The core of the system is the **POS Screen**, accessed from the top navigation."

- Click **POS**

> "It's split into three sections. On the left, we have the **product catalog** — cashiers can search by name or barcode in real time."

- Type "Samsung" in the search box

> "In the center is the **Current Order** — the shopping cart. Let me add a product."

- Select a product → click **Add to Cart**
- Add one more product

> "On the right is the **Order Summary**, which automatically calculates the subtotal, **12% VAT**, and the total. The cashier selects a payment method, enters the amount tendered, and the change is computed instantly."

- Select payment method → enter amount → click **Checkout**

> "The sale is recorded, stock is automatically deducted, and the cart is cleared for the next customer."

---

## 4. Inventory Management *(~45 seconds)*

> "Admins can manage products through the **Inventory** screen."

- Click **Inventory** in the navbar

> "All products are listed here with their category, price, cost, stock level, and status. Notice the **color coding** — red rows mean out of stock, orange means low stock."

- Type in the filter box to search a product

> "We can **Add**, **Edit**, or **Delete** products. The search filter works in real time, and the edit and delete functions correctly target the selected item even after filtering."

- Click **Edit** on a product → change the stock → save

---

## 5. Reports *(~45 seconds)*

> "The **Reports** screen gives the admin a sales overview."

- Click **Reports**

> "We can filter by **Today**, **Weekly**, **Monthly**, or view **All** transactions. The summary cards at the top show total sales, number of transactions, and the average sale value."

- Click **Today**, then **Weekly**, then **Monthly**

> "Each record shows the cashier, total amount, tax, discount, payment method, and timestamp."

---

## 6. User Management *(~30 seconds)*

> "Finally, the **User Management** screen lets the admin control system access."

- Click **Users**

> "We can **add new users**, assign them as Admin or Cashier, **toggle their active status** — which disables login without deleting their history — and **reset passwords**. The role is shown as a badge on each row."

---

## Closing *(~20 seconds)*

> "To summarize, InterTech POS covers the complete workflow of a retail electronics store — from login and sales processing, to inventory tracking, reporting, and user administration. The system is built with clean role-based access control and a modern dark-themed UI. Thank you."

---

**Total estimated time: ~5 minutes**
