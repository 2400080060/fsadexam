package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        SessionFactory sf = new Configuration()
                .configure()
                .addAnnotatedClass(Library.class)
                .buildSessionFactory();

        Session session = sf.openSession();

        Scanner sc = new Scanner(System.in);

        System.out.println("1.Insert Record");
        System.out.println("2.Delete Record");
        System.out.print("Enter Choice: ");
        int choice = sc.nextInt();

        session.beginTransaction();

        if(choice == 1)
        {
            Library lib = new Library();

            System.out.print("Enter Name: ");
            lib.setName(sc.next());

            System.out.print("Enter Description: ");
            lib.setDescription(sc.next());

            lib.setDate(new Date());

            System.out.print("Enter Status: ");
            lib.setStatus(sc.next());

            session.save(lib);

            System.out.println("Record Inserted");
        }

        else if(choice == 2)
        {
            System.out.print("Enter ID to Delete: ");
            int id = sc.nextInt();

            Library lib = session.get(Library.class,id);

            if(lib != null)
            {
                session.delete(lib);
                System.out.println("Record Deleted");
            }
            else
            {
                System.out.println("Record Not Found");
            }
        }

        session.getTransaction().commit();

        session.close();
        sf.close();
    }
}