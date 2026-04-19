# Notes App (Android + Firebase)

## Overview

This is a simple Notes App built using Android (Java) and Firebase Realtime Database. The application allows users to create, view, and delete notes in real time.

---

## Features

* Add notes with title and content
* Store notes in Firebase Realtime Database
* View all saved notes
* Delete notes instantly
* Real-time data updates

---

## Technologies Used

* Java (Android Development)
* Android Studio
* Firebase Realtime Database
* XML (UI Design)

---

## Project Structure

```
NotesApp/
│
├── java/com.kamalclasses.notesapp/
│   └── MainActivity.java
│
├── res/layout/
│   └── activity_main.xml
│
├── res/drawable/
│   └── gradient_bg.xml
│
├── AndroidManifest.xml
│
└── Gradle Scripts
```

---

## Setup Instructions

### 1. Clone the Repository

```
git clone https://github.com/your-username/NotesApp.git
```

---

### 2. Open in Android Studio

* Open Android Studio
* Click "Open"
* Select the project folder

---

### 3. Configure Firebase

1. Go to Firebase Console
2. Create a new project
3. Add an Android app
4. Download `google-services.json`
5. Place it inside the `app/` folder

---

### 4. Enable Realtime Database

* Open Firebase Console
* Go to Realtime Database
* Create database
* Start in test mode

---

### 5. Add Internet Permission

Ensure the following line is added in `AndroidManifest.xml`:

```
<uses-permission android:name="android.permission.INTERNET"/>
```

---

### 6. Run the App

* Connect a device or emulator
* Click Run in Android Studio

---

## How It Works

* User enters title and content
* Clicking "Save Note" stores data in Firebase
* Clicking "View Notes" fetches all notes
* Notes are displayed dynamically
* Each note includes a delete option

---

## Firebase Data Structure

```
notes
  ├── unique_id_1
  │     ├── title: "Sample Title"
  │     └── content: "Sample Content"
  ├── unique_id_2
        ├── title: "Another Note"
        └── content: "Details here"
```
Screenshots:
<img width="283" height="505" alt="image" src="https://github.com/user-attachments/assets/ff62425c-8b72-4f76-9977-6f1096ee65ab" />
<img width="1301" height="636" alt="image" src="https://github.com/user-attachments/assets/46ad6713-ad3a-4f01-a4a3-37706068dd12" />

---

## Future Improvements

* Add edit/update functionality
* Improve UI design
* Use RecyclerView instead of LinearLayout
* Add user authentication
* Add search functionality

---

## Author

Soham Savadi
