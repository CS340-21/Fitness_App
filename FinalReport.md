# Heading
## Team Name - Do You Even Lift
members - Emory Swanger, Kincaid McGee, and William Hannon

# Introduction

Our project was to code a simple fitness app that would allow a user to time workouts, store relevant YouTube videos, calculate caloric intake, implement a workout journal, and generate custom workout templates. Our motivation was that a multitude of fitness apps that we have used before contain a plethora of unnecessary features. We thought that we would attempt crafting a better, more user-friendly experience. Although our idea was not novel, we hoped to offer a more free and lightweight experience. Collectively, we have a fair amount of experience using Android Studio alongside a high amount of experience with C++ and its relatives. 

Our approach was to first find some base code that would allow us to switch bettween different features. Next, we planned on programming said features. Finally, we would try our hand at graphical design by trying to make the app look as aesthetic and professional as possible. We intended to meet once a week and focus on one thing each to get the project completed.

As far as we got, we could unfortunately only get so much done with each of our time-demanding schedules. Because we started a later than intended, we planned to only implement 4/5 of the original features. We also had to create the most minimal features possible because we lacked the time management to learn the API's necessary to create more elegant/sophisticated features versions.

To summarize, we were not able to create the full product due to time management and underestimating the difficulty of the project. That being said, were able to provide a functional minimal viable product. Overall, we reached 70%-80% of our end goal: the creation of a simple, aesthetic fitness app for beginners and everyone else.

# Customer value

There has been no changes in our customer value or primary customers.

# Technology

<p align = "center">
  <img src="https://github.com/CS340-21/Fitness_App/blob/main/finalarch.png">
</p>

There has not been any major change in architecture other than not implementing the workout template planner or any of the planned API's (google, youtube, etc.) Because there was no status report, we will only discuss what features succeeded. Firt is the home screen. 

Initially, we planned to have the app allow the user to swipe side to side to access each feature. So far, we the app will open a feature by hitting its link at the top of the screen. To exit a feature, the user would tap an arrow at the top-left of the screen to return to the home screen.

### Timer

<p align = "center">
  <img src="https://github.com/CS340-21/Fitness_App/blob/main/timer.png">
</p>

This feature allows the user to type in how many repetitions they want to complete, and how long they are in either minutes or seconds. The user would write in any whole number bettween 0 and 59. Anything else would result in an error. We tested this by inputting legal input and illegal input such as mixed numbers, letters, and negative numbers. The error checking successfully handled these cases. There was however a bug with when we were inputting numbers. Add addition input while already running the timer resulted in the clock jumping bettween random numbers.

### Youtube video linksaver

<p align = "center">
  <img src="https://github.com/CS340-21/Fitness_App/blob/main/yt.png">
</p>

This feature allowed the user to youtube links and rename them to make it easier to remember what the link actually takes them to. We error checked this by adding code that only allowed saving links that actually work. What we could not get to work properly was saving links from youtube only.

### Calorie Calculator

<p align = "center">
  <img src="https://github.com/CS340-21/Fitness_App/blob/main/cal.png">
</p>

This feature enabled the user to record and calculate their caloric intake for the day. The user would type in what they ate and the number of calories the item has. This would then be added into the list and the total number of calories consumed would be displayed. To reset the list, the user would hit the reset button, clearing the list of foods and calories recorded. To test this, we put in random food names and random numbers. The app let us save virtualy any word we wanted and but would only allow positive numbers to be saved. We considered this to be successful.

### Journal

<p align = "center">
  <img src="https://github.com/CS340-21/Fitness_App/blob/main/journal.png">
</p>

Finally, we have the journal. This worked by setting everything else in the app as invisible while setting this feature as the only thing visible. Anything the user wrote in the journal would be saved to a file. To save their progress, the user would hit the save button on the bottom-right corner of the screen. This was tested by typing in random input and saving or not saving it then leaving then returning to the feature to see if it correctly saved or did not save. This was successful.

# Team

#### Emory Swanger
 Role: Manager 
 Accomplishments:
 - Scheduled meetings
 - kept the project on track
 - controlled the repo
 - provided the background code

#### William Hannon
 Role: Developer 
 Accomplishments:
 - programmed timer feature
 - programmed journal feature
 
#### Kincaid McGee
 Role: Developer 
 Accomplishments:
 - programmed calorie counter feature
 - programmed youtube feature
 
Over all, the roles were pretty static but everyone did their fair share of the work. The tasks did become more specialized, especially the features. The developers were both given 2 different features to complete, which they did. The manager mostly kept to their non-programming role with the exception to implementing base code for the developers to build off of.

# Project Management

We completed most of our goals by creating our minimal viable project. What we did not finish were essentially the metaphorical whipped cream and cherry on top of our cake of a product. We did not implement our 5th feature, a workout template generator, more complex traits for the features, and we did not have time to learn and implement graphics into our product. Their is one main reason for this: time management. We the team should have started the project earlier to get a better head start on the project and to try and learn how to implement the desired API's and graphic design. As stated earlier, everyone had too many other priorities to focus on rather than the project.

# Reflection

