# Manufacturing Order Management Application

## Overview

This application is a **Manufacturing Order Management** system designed to streamline and monitor the manufacturing workflow.  
It allows management of manufacturing orders, machine and employee assignments, and monitoring of machine maintenance and availability.

---

## Technology Stack

- **Frontend:** Next.js, Tailwind CSS, shadcn/ui, react-icons
- **Backend:** Spring Boot (Java) RESTful API
- **Database:** PostgreSQL
- **No Authentication:** The app does not implement authentication or user login.

---

## Backend Architecture

The backend is implemented using **Spring Boot**, exposing RESTful endpoints for managing core manufacturing entities.  
Entities are modeled with Java classes and enums, with CRUD operations handled via standard Spring MVC controllers and JPA repositories.  
Data is persisted in a **PostgreSQL** database.

### Core Entities & Features

---

### 1. Employee Management

- **Entity:** `Employee`
- **Attributes:**
    - `id` (Long, primary key)
    - `name` (String)
    - `position` (`EmployeePosition` enum)
    - `machine` (Many-to-One association with Machine)
    - `createdAt`, `updatedAt` (timestamps, e.g., managed via `@CreatedDate` / `@LastModifiedDate`)

- **Features:**
    - Create, retrieve, update, delete employees
    - Associate employees with machines

- **API Endpoints:**
    - `GET /api/employees/` - Retrieve all employees
    - `POST /api/employees/` - Create new employee
    - `PUT /api/employees/{id}` - Update employee by ID
    - `DELETE /api/employees/{id}` - Delete employee by ID

---

### 2. Machine Management

- **Entity:** `Machine`
- **Attributes:**
    - `id` (Long, primary key)
    - `name` (String)
    - `status` (`MachineStatus` enum)
    - `lastMaintenanceDate` (Date)

- **Features:**
    - Full lifecycle management of machines
    - Track machine status and maintenance history
    - Associate machines with employees and manufacturing orders

- **API Endpoints:**
    - `GET /api/machines/` - Retrieve all machines
    - `POST /api/machines/` - Create new machine
    - `PUT /api/machines/{id}` - Update machine by ID
    - `DELETE /api/machines/{id}` - Delete machine by ID

---

### 3. Product Management

- **Entity:** `Product`
- **Attributes:**
    - `id` (Long, primary key)
    - `name` (String)
    - `type` (`ProductType` enum)
    - `stock` (Integer)
    - `supplier` (String)

- **Features:**
    - Manage products lifecycle
    - Associate products with manufacturing orders

- **API Endpoints:**
    - `GET /api/products/` - Retrieve all products
    - `POST /api/products/` - Create new product
    - `PUT /api/products/{id}` - Update product by ID
    - `DELETE /api/products/{id}` - Delete product by ID

---

### 4. Manufacturing Order Management

- **Entity:** `ManufacturingOrder`
- **Attributes:**
    - `id` (Long, primary key)
    - `project` (String)
    - `product` (Many-to-One association with Product)
    - `machine` (Many-to-One association with Machine)
    - `status` (`ManufacturingOrderStatus` enum)
    - `quantity` (Integer)
    - `date` (Date)

- **Features:**
    - Full lifecycle management of manufacturing orders
    - Update order status independently
    - Track assigned product and machine

- **API Endpoints:**
    - `GET /api/manufacturing-orders/` - Retrieve all orders
    - `GET /api/manufacturing-orders/{id}` - Retrieve order by ID
    - `POST /api/manufacturing-orders/` - Create new order
    - `PUT /api/manufacturing-orders/{id}` - Update order by ID
    - `PUT /api/manufacturing-orders/{id}/status` - Update order status only
    - `DELETE /api/manufacturing-orders/{id}` - Delete order by ID

---