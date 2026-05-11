# OceanX Agency - Mini Grocery Delivery App Assignment

<div align="center">

### 🚀 Premium Blinkit-Style Grocery Experience
**Built with precision for the OceanX Android Developer Internship**

---

**[Key Highlights]**
✨ **Pixel-Perfect UI** | 🛡️ **MVVM Architecture** | 📦 **Room Persistence** | ⚡ **StateFlow Updates** | 🎨 **AI-Generated 3D Assets**

---

![App Demo](https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHRtcGs5cTV0MHB6NjFqaWw0M203ZWgweXFocjV5b2Z1aW9vdnc0cyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Sm9AfJRiZofjlrkAAl/giphy.gif)

</div>

## 🛠 Detailed Implementation Table

| Feature / Screen | Implementation Details | Architecture & Components |
| :--- | :--- | :--- |
| **🔐 Secure Login** | Fake OTP verification system (`1234`) with interactive OTP boxes and input validation. | `LoginActivity`, `ViewBinding`, `Intent Flow` |
| **🏠 Home Dashboard** | Dynamic scroller for 3D Category icons, Horizontal Spotlight deals, and a 2-column product grid. | `HomeViewModel`, `ListAdapter` (DiffUtil), `GridLayoutManager` |
| **🔍 Search & Filter** | Real-time product search with category-based filtering logic. | `StateFlow` reactive filtering, `MutableStateFlow` |
| **🛒 Cart System** | Full CRUD operations for cart items. Persistent storage ensures data survives app restarts. | **Room DB**, `CartDao`, `CartRepository`, `StateFlow` |
| **💳 Checkout Flow** | Modern address card system, payment method selection, and order total validation. | `CheckoutActivity`, `CartViewModel` integration |
| **✅ Order Success** | Celebration screen with unique order IDs and estimated delivery time badges. | `OrderSuccessActivity`, Custom XML Animations |
| **👤 User Profile** | Personalized profile for **Aman Yadav** with account management and version tracking. | `ProfileActivity`, Custom Bottom Navigation |

## 🏗 Technical Architecture

This project follows the **Clean Architecture** principles and the **MVVM (Model-View-ViewModel)** pattern to ensure scalability and maintainability.

- **UI Layer**: XML-based layouts using `ConstraintLayout` for flat, high-performance view hierarchies.
- **ViewModel Layer**: Manages UI state and business logic, exposing data via `StateFlow`.
- **Repository Layer**: The single source of truth, mediating between the local Room DB and the ViewModels.
- **Data Layer (Room)**: Handles SQLite persistence for the cart, ensuring a robust offline-first experience.

## 📱 Tech Stack

- **Language**: Kotlin (100%)
- **UI**: Android XML, Material Design 3
- **Database**: Room Persistence Library
- **Concurrency**: Kotlin Coroutines
- **State Management**: StateFlow & LiveData
- **Dependency Injection**: Manual Repository Injection (via ViewModelFactory)
- **Image Handling**: Dynamic Resource Loading (Identifier-based)

## 🚀 How to Run

1. **Clone**: `git clone https://github.com/amancatto/OceanX--Grocery-App-Demo.git`
2. **Open**: Launch Android Studio and open the project.
3. **Build**: Let Gradle sync and build the project.
4. **Run**: Deploy to an Emulator or Physical Device (API 26+ recommended).

---
<div align="center">
Developed with ❤️ by <b>Aman Yadav</b> for OceanX Agency
</div>
