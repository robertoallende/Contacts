# Contacts

An Native Android App and Backend implemented with ExpressJS/MongoDB to list, add and remove contacts.

## Android Client

![Demo](https://media.giphy.com/media/JzxkMPCaOeky4/giphy.gif)

[Watch Demo on youtube.](https://www.youtube.com/watch?v=4LRbFHFeuww)

### Running the server

Follow instructions from [Server Documentation](https://github.com/robertoallende/Contacts/tree/master/server).


### Compiling and running the client

The application was coded running Android Studio 2.1.3. In order to get it running, clone the source code from [Github](https://github.com/robertoallende/Contacts/tree/master/androidClient). Import the project into Android Studio. 

Before running the client, it is require to setup the address server is running. To do that, modify app gradle file, replacing the values of the server_url variable inside buildTypes to match the ip number server is running.

```buildTypes {

        debug{
            resValue "string", "server_url", "http://192.168.2.71:8000"
        }

        release {
            resValue "string", "server_url", "http://192.168.2.71:8000"
```

In this example, the server is running on a machine with IP number 192.168.2.71. If the application server is running on another IP number, this value hast to be set accordingly. 


### About the code

The code follows MVC Architecture described by Yigit Boyar in the article titled  [A Recipe for writing responsive REST clients on Android](www.birbit.com/a-recipe-for-writing-responsive-rest-clients-on-android/) and in the talk title [Android Application Architecture (Android Dev Summit 2015)](https://www.youtube.com/watch?v=BlkJzgjzL0c) at the Android Dev Summit 2015.

By defining clear goals for each layer, this architecture allows me to have decoupled comoments that follow Single responsibility principle. 

Since this project was built in less than 24 hours man in total, I took decisions in order to deliver and have a fully functional program at the same time. This means that for certain components i chose conservative alternatives, for example, I use a ListView instead a RecyclerView or even a ListView using the View Holder Pattern. Although RecyclerView is more effiecient and behaves better, ListView requires less boilerplate code and it usually require less testing. A similar reason led me to use Serializable instead of Parceleable. Although Parceleable is more efficient, requires more boilerplate code. 
In a 'real world' project, I would use RecyclerView and Parceleable.

This project has a test case to show an example of a functional test case made with Espresso but, again in a 'real world' project, testing wout be more exhaustive and cover different cases. I would use mocks to avoid real api calls. 


