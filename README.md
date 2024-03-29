<h2> § Description - Stage 1 </h2>
<p>
Contacts is a handy app to have. It stores all of your saved contacts. In this project, you will write one yourself. It teaches you to understand and implement the basic principles of OOP.

In the first stage, you should write a program that creates an instance of a class that stores information about one record in the Contacts. One record should contain a name, a surname, and a phone number. You can type them from the keyboard.

You should also create a class representing this app. For now, it should store only one record: a record that you created from the input. 
</p>

<h2> § Description - Stage 2 </h2>
<p>
Sometimes we need to restrict the ability to change the instance fields. For example, a phone number can't be just any string; it should follow some rules. As you can see here, the phone number format is different for every country, but they all have some elements in common.

So, you should set the field with a phone number to be private and create a getter and setter to this field. The setter should check the value using a regular expression and set the value to the field only if the value satisfies all the rules below:

The phone number should be split into groups using a space or dash. One group is also possible.
Before the first group, there may or may not be a plus symbol.
The first group or the second group can be wrapped in parentheses, but there should be no more than one group that is wrapped in parentheses. There may also be no groups wrapped in parentheses.
A group can contain numbers, uppercase, and lowercase English letters. A group should be at least 2 symbols in length. But the first group may be only one symbol in length.
If you are struggling with a regular expression that checks all of these, you might check the phone number with only String methods.
Also, create getters and setters for the name and surname of the person. Besides, there should be a method hasNumber() that checks if the user has a number. Initially, set the number to be an empty string.

Create a separate method to check the validity of the phone number. This is standalone logic, and potentially it can be used in multiple places of a class. But this is also a method for internal use. Therefore, mark the method as private.

This concept of restricting the usage of a class is called encapsulation. This is a self-documented solution for how to use a class.

In this stage, you should write a program that keeps all the records in a list. You should be able to add, remove, edit the records, and get the number of records. If the user inputs an incorrect phone number, you should reset it as empty. If the number is empty, you should write the string [no number] instead of it.
</p>
<h2> § Description - Stage 3 </h2>
<p>
In this stage, you will write a program that can store not only information about people but also organizations.

In the real app, you can also store phone numbers of different companies, like your favorite pizza shop or your bank. These organizations don't have a name or a surname; they have an organization name and an address.

Additionally, a person can have a birth date and gender, but a company can't have that.

Let's use inheritance to solve this issue. There should be a base class containing information relevant to both an organization and a person, like a phone number. And there should be two classes that inherit this base class: a class that represents an organization and a class that represents a person.

The list of records should contain both people and organizations. You can accomplish that if the list with records contains elements of the base class, not the specific classes.

There is one problem with that: how can you edit the fields that correspond to specific classes, like the address of an organization? One of the solutions is to create a final Boolean field isPerson in the base class. After that, when editing the record, first check this field, then cast to the corresponding class and then edit the field. This is a bad, workaround solution, but we will write a more general solution in the next stage.

Also, in this stage, you can improve the base class so that it has more than one field. You should implement fields that store the date of this record creation and the date of the last edit.
</p>
<h2> § Description - Stage 4 </h2>
<p>
Our temporary solution in the previous stage was bad because of every time you want to interact with concrete classes you must check the Boolean field then apply different code according to the concrete class. So far so good, you implement this behavior every time you need to. However, in a larger application, there can be 100 places or more where you must do this. And at the end of the day, a new feature request might come in: implement a third type of records, something that represents an automated system with robots serving the calls. You would be very annoyed that you were forced to find all the places where you interact with concrete classes.

The solution to this problem is a polymorphism.

Basically, you need to implement the functionality in one place inside a concrete class. All of the derived classes should implement this method, and in the base class, there should be an abstract method. In the code, you actually call this abstract method and get different code executions in the derived classes.

To solve your problem, you should create several methods:

A method that returns all of the possible fields this class is able to change.
A method that takes a string that represents a field that the class is able to change and its new value.
A method that takes a string representation of the field and returns the value of this field.
After that, you don't need any Boolean workarounds and type casts; the code will be nice and clean.

Also, in this stage, you should implement saving to a file and loading from a file. You can save the Contacts using serialization. You should specify a file you are working with by a command-line argument. This would automatically save the Contacts on the hard drive after each action that modifies data. If you don't specify an argument, then you should create a new Contacts and keep it in memory. If you specify a file that doesn't exist, you should create an empty Contacts and save all changes to the newly created file.

Also, in this stage, you should implement search functionality. For this, you can append all of the values from all of the fields and check if this string contains a search request. It should support regular expressions, too! And, of course, it should be case insensitive.

Use an empty line to separate different actions, like in the example.


</p>

