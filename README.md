# Android Project with REST Server Integration

## Description
This project integrates a REST server with an Android app. It also includes instructions for setting up a Node.js server with MongoDB and Yarn. Follow the steps below to set up and run both the Android project and the REST server.

## Prerequisites

- Android Studio (install following the [official guide](https://developer.android.com/studio))
- Node.js, MongoDB, Yarn, and NPM
- REST server running with the correct configuration

## Android Setup Instructions

1. **Install Android Studio**
   - Follow the installation guide provided in the [official documentation](https://developer.android.com/studio).

2. **Open the Project in Android Studio**
   - Clone or download the project and open it in Android Studio.

3. **Configure the REST Server**
   - Start the REST server.
   - Set the `server_address` string in the project to match the address of your running REST server.

4. **Sync Gradle**
   - In Android Studio, click on "Sync Project with Gradle Files" to sync the project dependencies.

5. **Build the Project**
   - Click on "Build" in the Android Studio menu to compile the project.

6. **Run the Project**
   - Connect your Android device or use an emulator.
   - Run the project from Android Studio to test it on your device.

## Node.js and Server Setup Instructions

1. **Install Node.js**
   - Install Node.js by following the [official installation guide](https://nodejs.org/en/).
   - Alternatively, you can use NVM (Node Version Manager) to install Node.js.

2. **Install MongoDB**
   - Install MongoDB by following the [official installation guide](https://www.mongodb.com/docs/manual/installation/).

3. **Install Yarn (optional)**
   - Follow the [official Yarn installation guide](https://yarnpkg.com/getting-started/install) if you prefer to use Yarn.
   - If you don't want to use Yarn, you can skip this step and use NPM instead.

4. **Initialize Dependencies**
   - Open the project directory in the terminal.
   - Run the following command to install the necessary dependencies:
     ```bash
     $ yarn install
     ```

5. **Run the Server**
   - Start the server with the following command:
     ```bash
     $ yarn start
     ```

6. **(Optional)** Fill Visitation Data
   - After filling in some data, you may run the following command to automatically fill the visitation data:
     ```bash
     $ yarn fill
     ```

## Running Both Android and REST Server

- Ensure that the REST server is running.
- Follow the Android setup steps to run the mobile application on your device.
- Once the mobile app is running, it will communicate with the REST server based on the provided address.

## Troubleshooting

- Make sure that the REST server is running and the `server_address` is correctly configured in the Android project.
- If you encounter issues during the Node.js or MongoDB setup, check the official documentation for troubleshooting steps.
- For any errors related to Yarn or NPM, consult their respective documentation or logs.

## License
This project is licensed under the MIT License.
