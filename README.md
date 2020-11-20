# CZ2002 MySTARS 🌟

![Alt Text](https://data.whicdn.com/images/238451357/original.gif)

## Queries ❓
- line 108 @ MySTARS.java, shouldn't mode == 1 be logging into admin instead of students? （ ✅ DONE BY JIA WEN)
- Shouldn't Student class be extending User class?? （ ✅ DONE BY JIA WEN)
- Add class need to check clash ✅
- After a index been registered, it cannot add class or remove class or update class. ✅
- Can a student change/swap index with another student after the registration period? ( ✅ Nope)
- After setting a new class size, if there's student in the waitlist, we should dequeue appropriate amount of them to register the course? (✅ DONE BY JIAQING)

## To do 🚌
- Demonstration flow. Courses, indexes, students, admins to be created prior to the demonstration. （90% ✅BRYSON)
- Report... 💀
- Debug main program. (Progress: 70% ✅BRYSON, ✅JIAQING, ✅JIAWEN)
- Special check for valid input for "Monday/Tuesday..", "Male/Female", etc.
- BRYSON: Check for existing course before adding a new course. Tested, system will add two objects with the same course information. (✅ DONE BY JIAQING)
- BRYSON: Check for existing index before adding a new index to a course. (✅ DONE BY JIAQING)
- boolean "exist" must be reset for every check in each switch case, otherwise not exist print message won't be shown.
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

![Alt Text](https://bestanimations.com/media/cats/608000676cute-kitty-animated-gif-26.gif)
![Alt Text](https://media.giphy.com/media/Vzk5PFo9iH5AEustmv/giphy.gif)
