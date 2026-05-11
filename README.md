# OceanX Agency - Mini Grocery Delivery App Assignment

![App Demo](https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHRtcGs5cTV0MHB6NjFqaWw0M203ZWgweXFocjV5b2Z1aW9vdnc0cyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Sm9AfJRiZofjlrkAAl/giphy.gif)

## Overview
This is a premium, Blinkit-style mini grocery delivery application built as part of the OceanX Android Developer Internship assignment. The app features a high-end UI design, reactive data management with Room DB, and a seamless shopping experience from login to order confirmation.

## Features Implemented
The application is structured across 5 core screens, ensuring a complete user journey:

*   **Secure Login**: Interactive OTP verification screen (Hardcoded OTP: `1234`).
*   **Dynamic Home Screen**: Features premium 3D category icons, a horizontal Spotlight scroller for deals, and a robust search/filter system for the product catalog.
*   **Reactive Cart**: Real-time quantity management powered by **Room Local Database**. Includes dynamic bill summaries (subtotal, delivery fee, and grand total).
*   **Seamless Checkout**: Pre-filled address cards and payment method selection with validation before placing an order.
*   **Order Success**: A celebratory confirmation screen with order tracking options and navigation back to the home screen.
*   **User Profile**: A personalized profile section for **Aman Yadav** featuring order history, saved addresses, and account management.

## Tech Stack
*   **Language**: Kotlin
*   **UI Framework**: Android XML (ConstraintLayout, Material Components)
*   **Architecture**: MVVM (Model-View-ViewModel) with ViewBinding
*   **Database**: Room Persistence Library (Local SQLite)
*   **Asynchronous Flow**: Coroutines & StateFlow for reactive UI updates
*   **Design System**: Custom Teal/Surface-Gray design system with high-resolution AI-generated assets

## How to Run
Follow these steps to get the project running on your local machine:

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/amancatto/OceanX--Grocery-App-Demo.git
    ```
2.  **Open in Android Studio**:
    *   Launch Android Studio.
    *   Select `File > Open` and navigate to the cloned directory.
3.  **Build the Project**:
    *   Wait for the Gradle sync to complete.
    *   Ensure you have the latest Android SDK and Build Tools installed.
4.  **Run the App**:
    *   Connect a physical device or launch an emulator.
    *   Click the **Run** button (green play icon) in the toolbar.

---
Developed for the **OceanX Agency Android Internship Assignment** by Aman Yadav.
