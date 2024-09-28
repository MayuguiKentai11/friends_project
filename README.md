# Friends Projet !

FriendSpy is an Android application that uses Retrofit to fetch random user data from an API and display it in a RecyclerView. Users can load a specified number of random users and add them to a friend list.


## Features

- Fetch random user data from the [Random User API](https://randomuser.me/)
- Display user data in a RecyclerView
- Add users to a local friend list
- Uses Retrofit for network requests
- Gson for JSON parsing

## Screenshots

- Gson for JSON parsing

## Screenshots

![Main_Activity](https://i.imgur.com/VmIIH5f.png)
<br>
**Main Activity**

<br>

![People List Activity](https://i.imgur.com/piAYmrL.png)
<br>
**People Activity**
<br>
## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/MayuguiKentai11/FriendSpy.git
    ```
2. Open the project in Android Studio.

3. Build the project to download dependencies and sync the project.

## Usage

1. Run the application on an Android device or emulator.

2. Navigate to the People Activity to load random users.
   
3. Enter the number of users to load and click the "Load" button.

4. Add users to the friend list by clicking on them.

## Code Overview

### Main Components

- **Activities**
  - `PeopleActivity`: Fetches and displays random users.
  - `FavoriteActivity`: Displays the list of friends added.
  - `MainActivity`: Displays the Home view, in this case the main point entry.

- **Adapters**
  - `PersonAdapter`: Binds user data to the RecyclerView.

- **Network**
  - `RandomUserApiService`: Defines the API endpoints.
  - `ApiResponse`: Models the API response.
    
- **Database**
  - `AppDatabase`: Manages local database operations.

### Layouts (Visual Components)

- `activity_people.xml`: Layout for the People Activity.
- `activity_favorite.xml`: Layout for the Favorite Activity.
- `activity_main.xml`: Layout for the Main Activity 
- `prototype_user.xml`: Layout for the prototype user, in this case the card for each received person from request API.

## Dependencies

- Retrofit
- Gson
- Room
- ConstraintLayout
- RecyclerView
- ConstraintLayout
- RecyclerView
  
## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
