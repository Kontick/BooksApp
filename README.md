# BooksApp

BooksApp is an Android application built with Kotlin and Jetpack Compose as an interview assignment from **Viesure** company. It demonstrates a modular architecture with a strong focus on code quality and security. The app consists of two main screens: a list of books and detailed information about each book.


## Features

- **Modular Architecture**: The app is divided into three modules:
  - **app**: Main application module.
  - **common**: Common utilities and shared code.
  - **features**:
    - **list**: Module for displaying the list of books.
    - **detail**: Module for displaying detailed information about each book.
  
- **Clean Architecture**: Each module and the common module are structured into `data`, `domain`, and `presentation` layers.

- **Security**: Local database is secured using SQLcipher. A random 32-byte passphrase is generated and stored in an encrypted file on the device (implemented in `PassphraseGenerator.kt` and `BooksDatabasePassphrase.kt`).

- **Data Synchronization**: Implements retry mechanism (3 retries with 2 seconds backoff delay) for handling data synchronization failures like 500 internal server errors or network coverage issues.

- **Date Parsing**: Supports various date formats including "dd/MM/yyyy", "yyyy", and "yyyy BC". Dates are parsed and displayed differently based on the year:
  - For "dd/MM/yyyy" format:
    - Years 2000 or higher are displayed as "Wed, Jul 8, '20".
    - Years below 2000 are displayed as "Wed, Jul 8, 1924".
  This functionality is implemented in `CloudToCacheBookMapper.kt` and `BooksAppDateParser.kt`.


## Screenshots:

<img src="https://github.com/KontVIP/BooksApp/assets/76660306/8ce19c95-9892-4487-ada5-6c38d4ab1b65" alt="List Screen" width="400"/>  
<img src="https://github.com/KontVIP/BooksApp/assets/76660306/f2d8a701-1b47-45e6-bc4f-1de77c0f0ee3" alt="Detail Screen" width="400"/>


## Testing

Testing in BooksApp includes *unit*, *UI*, and *instrumented* tests throughout the application. A notable aspect is the "Delay test", located in `FetchBooksUseCaseTest.kt`, which evaluates the interval (in seconds) between retries when sending requests to the server.


## Stack:

- Kotlin;
- Jetpack Compose;
- Hilt;
- Retrofit;
- Room;
- Coroutines;
- Flow;
- Glide;
- JUnit;
- SQLcipher.

**Architecture:**
- Clean Architecture with modular structure.

**Notes:**
- No public fields throughout the project except in one file (`CacheBook.kt` entity for Room database) due to necessary code generation.
