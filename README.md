# CZ2002 MySTARS 🌟

![Alt Text](https://data.whicdn.com/images/238451357/original.gif)

## Queries ❓

- line 108 @ MySTARS.java, shouldn't mode == 1 be logging into admin instead of students? （ ✅ DONE BY JIA WEN)
- Shouldn't Student class be extending User class?? （ ✅ DONE BY JIA WEN)
- Add class need to check clash ✅
- After a index been registered, it cannot add class or remove class or update class. ✅
- Can a student change/swap index with another student after the registration period? ( ✅ Nope)
- After setting a new class size, if there's student in the waitlist, we should dequeue appropriate amount of them to register the course? (✅ Done by Jia Qing)
- Jia Wen: If enter wrong mode or username, should we loop back to the menu instead of terminating program? (BRYSON: Not needed?)

## To do 🚌

- Demonstration flow. Courses, indexes, students, admins to be created prior to the demonstration. （90% ✅BRYSON)
- Debug main program. (Progress: 70% ✅BRYSON, ✅JIAQING, ✅JIAWEN)
- BRYSON: Check for existing course before adding a new course. Tested, system will add two objects with the same course information. (✅ DONE BY JIAQING)
- BRYSON: Check for existing index before adding a new index to a course. (✅ DONE BY JIAQING)
- Special check for valid input for "Monday/Tuesday..", "Male/Female", etc. （ ✅ DONE BY JIA WEN)
- Limit input for... school? （✅ DONE BY JIA WEN)
- Jia Wen: Time change to 8.30am blahblah???? （✅ DONE BY JIA WEN)
- Need to add javadoc for Email.java (✅ DONE BY BRYSON)
- Report... 💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀💀
- boolean "exist" must be reset for every check in each switch case, otherwise not exist print message won't be shown.（✅ DONE BY JIA WEN)//let exist=false for every while loop
- Need to include feedback message for every operations.


## Debug 🎅 

```java
//Better way to read int/number
int option = 0;
try {
    option = Integer.parseInt(input.nextLine());
} catch (NumberFormatException e) {
    e.printStackTrace();
}
String str1 = input.nextLine();
```

## Program Testing 💻

Step 1: Delete everything inside mySTARS.txt  
Step 2: Delete every account inside account.txt (EXCEPT FOR ADMIN's // First line of the file)  
Step 3: For the first login, uncomment the following codes and login into admin account.  

```java
// Demonstration prepopulation, comment it out after prepopulation before login into Admin Account again!!
System.out.println("Prepopulating Students, Courses, Indexes, Classes ...");
Populate.prepopulate(admin, mainApp.courses);
System.out.println("Finished prepopulating!");
```

Step 4: Comment the codes above immediately after prepoulation is done. [DO THIS BEFORE LOGIN INTO ADMIN AGAIN].

![Alt Text](https://bestanimations.com/media/cats/608000676cute-kitty-animated-gif-26.gif)
![Alt Text](https://media.giphy.com/media/Vzk5PFo9iH5AEustmv/giphy.gif)
![Alt Text](https://media4.giphy.com/avatars/tontonfriends/oR1fkkiDPgSG.gif)
![Alt Text](https://media.giphy.com/media/j6qTili1bHtQqV805M/giphy.gif)
