
# Person class
class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age
    def change_name(self,name):
        self.name = name

andy = Person("Andy", 36)
mike = Person("Mike", 28)

print("Welcome {0}, you are {1} years old. Congratulations".format(andy.name, andy.age))
print("Welcome {0}, you are {1} years old. Congratulations".format(mike.name, mike.age))

andy.change_name("Smith")
mike.change_name("John")

print("Andy: your new name is {0}".format(andy.name))
print("Mike: your new name is {0}".format(mike.name))