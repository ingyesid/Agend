/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.agend.logic;

import java.util.Vector;

/**
 *
 * @author Yesid Lazaro Mayoriano
 */
public class ContactsController {

    private Vector contacts;

    public ContactsController() {
        this.contacts = new Vector();
    }

    public void addContact(Contact contact) {
        contacts.addElement(contact);
    }

    public void RemoveContact(Contact contact) {
        contacts.removeElement(contact);
    }

    public Contact getContact(String input) {
        Contact contact = null;
        for (int i = 0; i < contacts.size(); i++) {

            if (((Contact) contacts.elementAt(i)).getName().equals(input)
                    || ((Contact) contacts.elementAt(i)).getLastName().equals(input)
                    || ((Contact) contacts.elementAt(i)).getTwitterUsername().equals(input)) {
                contact = ((Contact) contacts.elementAt(i));
            }
        }
        return contact;
    }

    public Vector getContacts() {
        return contacts;
    }
}
