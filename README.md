#How to run program:
1) mvn package
2) cd target
3) java -jar BSCExercise-1.0-SNAPSHOT.jar <filepath>

#Invalid input
1) In a file - Print a message with correct format of input and cancel the program
2) User input - Print a message with correct format, but the program is not canceled. User can try it again.
3) Too many arguments - Print a message and cancel the program

#My thoughts:
1) utils - for static reusable methods
2) StringBuffer instead of StringBuilder - thread safe
3) As little as possible of duplicated code
4) I thought about make PackageResolver methods static, it was 50 to 50 for me
