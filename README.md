# VolvoCar Assignment (Weather App) version 1.0

## Technologies
The project is developed in the Kotlin language and also uses libraries such as Jetpack Compose, Hilt, Coroutines, Flows, and Retrofit.
The JUnit and Mockk test libraries were used for unit testing.
The Compose testing was used for UI testing.

## Architecture
Multi-module using Clean Architecture alongside other components and MVVM architectural design patterns are used for project architecture.

## Features
The application consists of two screens: List of cities by displaying the their weathers and the detail screen for every city.

##### In the following images you can see the screenshots of screens:

![Screen1](https://user-images.githubusercontent.com/95828426/198157343-de50bf63-0ed3-4afd-9c95-3c9072c6a796.jpg) ![Screen2](https://user-images.githubusercontent.com/95828426/198157403-4057188c-acc7-4370-9adc-0e5c7b995c1f.jpg)

> And you can download the apk file form [here](https://drive.google.com/file/d/10QRx-FXIoACKQzGnlA-wMEEJduvNu5Ac/view?usp=sharing).

## FYI 
The commit by message "Replace the Stateflow with mutableList for fixing the problem of recomposing the list" is for fixing a bug that I found in the app. There is a structure between stateflows and compose screens for awaring of changes. But unexpectedly, by changing the state of stateflow the home screen list could not observe the changes. So I had to add and cahnge something to fix the bug. But I belive that the first solution is the mosre correct one and I will find the problem and fix it in the future. 
