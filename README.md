Movie Database and API

This is a lightweight backend service for storing and retrieving information about movies (Rating, release date, description, and average user voting). The backend fetches movie data from **The Movie Database (TMDb) API** and stores it into a postgres database using JPA.

Features

Nice toString for console printing

-API Fetching methods for:
    Retrieve a movie by id
    Retrieve all movies containing a word in their title
    Retrieve all movies containing a word in their overview (description)
    Retrieve all movies released on a specified date
    Retrieve all movies in chosen original language, from the last X years

-Database CRUD Operations:
    Save data to database
    Find movies by ID
    Find all movies
    Update movie
    Delete movie with id

-A few tests on API fetching methods

-Method for populating the database ((hibernate.hbm2ddl.auto needs to be set to "create" when rebuilding the database from new, otherwise, "update" is used.
