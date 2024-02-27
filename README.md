# GitHub Users Explorer App

## Overview

The GitHub Users Explorer App is an Android app created as the final submission for the "Belajar Fundamental Aplikasi Android" Dicoding course. The GitHub Users Explorer App is an Android application built with Kotlin that leverages the GitHub API to provide users with the ability to explore GitHub profiles. It includes features such as searching for GitHub users, viewing detailed profiles including followers and following lists, saving favorite users for quick access, and supporting dark mode for enhanced user experience.

**Please note:** This repository is meant for reference only. Dicoding has a strict plagiarism policy, and all submissions are checked for originality. Use this code to learn and inspire your projects, not to submit as your own work.

## Features

- **Explore GitHub Users**: Browse through all GitHub users in a user-friendly interface.
- **Search Functionality**: Quickly find users with the search feature.
- **User Details**: View detailed information about users, including their followers, following, and repositories.
- **Favorite Users**: Save users to a favorites list for easy access.
- **Dark Mode**: Toggle between light and dark modes for user preference.

## Screenshots

### Light Mode
| Home | Search | User Details |
| --- | --- | --- |
| ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20home.png) | ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20search.png) | ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20detail%201.png) |

| Favorites | Settings | 
| --- | --- |
| ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20fav.png) | ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20setting.png) |

### Dark Mode
| Home | User Details | Settings
| --- | --- | --- |
| ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20dark.png) | ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20detail%20dark.png) | ![](https://github.com/davidjohanhp/github-users/blob/main/assets/Fundamental%20Aplikasi%20Android%20-%20setting%20dark.png)

## Getting Started

This section provides instructions on how to set up the project locally.

### Prerequisites

- Android Studio
- Kotlin
- An active Internet connection to fetch data from GitHub's API

### Installation

1. Clone this repository
2. Open the project in Android Studio.
3. Obtain a GitHub Personal Access Token:
- Go to your GitHub Settings.
- Select Developer Settings > Personal access tokens > Generate new token.
- Note: Ensure you select the scopes necessary for accessing user data.
4. Add your GitHub token to the `ApiService.kt` file:
- Navigate to `app/src/main/java/com/example/proyekgithubuser/ApiService.kt`.
- Find the placeholder for the GitHub token and replace it with your actual token. This token will be used to authenticate requests to the GitHub API.
5. Sync the project with Gradle files to resolve dependencies.
6. Run the application on an emulator or physical device.

## Usage

- Launch the app and browse or search for GitHub users.
- Tap on a user to view their detailed profile.
- Use the heart icon to add/remove users from your favorites.
- Access your saved favorites through the favorites tab.
- Switch between light and dark modes via the settings.

## Contributing

Contributions are welcome to help improve the GitHub Users Explorer App.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
