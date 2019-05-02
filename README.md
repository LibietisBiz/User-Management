# User-Management

A simple app where you can add, view and delete users and see their details.
          
    Details include:  Users ID
                      Name
                      Profession
                      Salary
                      
DESIGN DECISONS
---------------
Q: Why did i decide to do this app?
 
A: I wanted the app to look colourful but yet simplistic. Very easy to use to keep track of your employees details.
 
Q: Why did i go with SQL Lite instead of any other cloud based system, e.g Firebase?

A: Better Performance: Small edits only overwrite the parts of the file that change, reducing write time and wear on SSD drives.The   application only has to load the data it needs, rather than reading the entire file and holding a complete parse in memory.

And mainly.. Performance problems can often be resolved, even late in the development cycle, using CREATE INDEX, avoiding costly redesign, rewrite, and retest efforts.

BUILT WITH
----------
Kotlin -  Kotlin is designed to interoperate fully with Java, its standard library depends on the Java Class Library, but type inference allows its syntax to be more concise.

Android Studio - Google's Android operating system, built on JetBrains' IntelliJ IDEA software and designed specifically for Android development.

SNIPPETS
--------------

Delete button w/ code

- This deletes users ID,Name,Profession and Salary from the table.
- A Toast is used if not entered correctly
          
![DELETE](https://github.com/LibietisBiz/User-Management/blob/master/delete.png)
![DELETE BUTTON](https://github.com/LibietisBiz/User-Management/blob/master/deletebtn.png)
![TOAST](https://github.com/LibietisBiz/User-Management/blob/master/nodelete.png)
 

 
