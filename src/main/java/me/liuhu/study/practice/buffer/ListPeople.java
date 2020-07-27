package me.liuhu.study.practice.buffer;
import me.liuhu.study.practice.buffer.AddressBookProtos.AddressBook;
import me.liuhu.study.practice.buffer.AddressBookProtos.Person;
import java.io.FileInputStream;


/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-15 17:43
 **/
class ListPeople {
    // Iterates though all people in the AddressBook and prints info about them.
    static void Print(AddressBook addressBook) {
        for (Person person: addressBook.getPeopleList()) {
            System.out.println("Person ID: " + person.getId());
            System.out.println("  Name: " + person.getName());
            System.out.println("  newFiled: " + person.getNewFiled());

            if (person.hasEmail()) {
                System.out.println("  E-mail address: " + person.getEmail());
            }

            for (Person.PhoneNumber phoneNumber : person.getPhonesList()) {
                switch (phoneNumber.getType()) {
                    case MOBILE:
                        System.out.print("  Mobile phone #: ");
                        break;
                    case HOME:
                        System.out.print("  Home phone #: ");
                        break;
                    case WORK:
                        System.out.print("  Work phone #: ");
                        break;
                }
                System.out.println(phoneNumber.getNumber());
            }
        }
    }

    // Main function:  Reads the entire address book from a file and prints all
    //   the information inside.
    public static void main(String[] args) throws Exception {
        args = new String[1];
        args[0] = "src/main/java/me/liuhu/study/effective/java/buffer/person.bin";

        if (args.length != 1) {
            System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        // Read the existing address book.
        AddressBook addressBook =
                AddressBook.parseFrom(new FileInputStream(args[0]));

        Print(addressBook);
    }
}
