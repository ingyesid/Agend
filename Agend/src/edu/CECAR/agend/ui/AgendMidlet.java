/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.agend.ui;

import edu.CECAR.agend.logic.Contact;
import edu.CECAR.agend.logic.ContactsController;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.StringItem;

/**
 * @author Yesid Lazaro Mayoriano
 */
public class AgendMidlet extends MIDlet {

    private ContactsController contactsController;
    private Display display;
    private List listMenu;
    
    private StringItem stringItemSearchResult;
    private Form formAddContact;
    private Form formSearchContact;
    
    private TextField txtName;
    private TextField txtInputContactSearch;
    private TextField txtLastName;
    private TextField txtPhone;
    private TextField txtMobile;
    private TextField txtEmail;
    private TextField txtTwitterUsername;
    
    private final String[] menuOptios = new String[]{"Agregar Contacto", "Buscar Contacto"};
    private Command commandSelect;
    private Command commandSave;
    private Command commandBack;
    private Command commandSearchContact;

    public AgendMidlet() {
        display = Display.getDisplay(AgendMidlet.this);
        listMenu = new List("Menu", List.EXCLUSIVE, menuOptios, null);

        formAddContact = new Form("Agregar Contact");
        formSearchContact = new Form("buscar Contact");
        commandSelect = new Command("Seleccionar", Command.OK, 0);
        commandBack = new Command("Atras", Command.SCREEN, 0);
        commandSave = new Command("Guardar", Command.SCREEN, 0);
        commandSearchContact = new Command("buscar", Command.OK, 0);

        txtName = new TextField("Nombre", "", 100, TextField.ANY);
        txtLastName = new TextField("Apellido", "", 100, TextField.ANY);
        txtPhone = new TextField("Telefono", "", 100, TextField.PHONENUMBER);
        txtMobile = new TextField("Celular", "", 100, TextField.PHONENUMBER);
        txtEmail = new TextField("Correo Electronico", "", 100, TextField.EMAILADDR);
        txtTwitterUsername = new TextField("Usuario de Twitter", "", 100, TextField.ANY);

        txtInputContactSearch = new TextField("Nombre/apellido o telefono", "", 100, TextField.ANY);
        stringItemSearchResult=new StringItem("Resultado", "");
        listMenu.addCommand(commandSelect);
        listMenu.setCommandListener(commandListenerMenu);

        formAddContact.append(txtName);
        formAddContact.append(txtLastName);
        formAddContact.append(txtPhone);
        formAddContact.append(txtMobile);
        formAddContact.append(txtEmail);
        formAddContact.append(txtTwitterUsername);
        formAddContact.addCommand(commandBack);
        formAddContact.addCommand(commandSave);
        formAddContact.setCommandListener(commandListenerFormContact);

        formSearchContact.append(txtInputContactSearch);
        formSearchContact.append(stringItemSearchResult);

        formSearchContact.addCommand(commandSearchContact);
        formSearchContact.setCommandListener(commandListenerForSeacrhContact);
        // 
        contactsController = new ContactsController();

    }

    public void startApp() {
        display.setCurrent(listMenu);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public boolean contactFormIsComplete() {
        if (txtName.getString().equals("")
                || txtLastName.getString().equals("")
                || txtMobile.getString().equals("")
                || txtPhone.getString().equals("")
                || txtTwitterUsername.getString().equals("")
                || txtEmail.getString().equals("")) {
            return false;
        } else {
            return true;
        }
    }
    //listener para los commands del list
    CommandListener commandListenerMenu = new CommandListener() {
        public void commandAction(Command command, Displayable displayable) {
            if (command == commandSelect) {
                switch (listMenu.getSelectedIndex()) {
                    //opcion gestionar contacto
                    case 0:
                        display.setCurrent(formAddContact);
                        break;
                    case 1:
                        display.setCurrent(formSearchContact);
                        break;
                }

            }
        }
    };
    //listener para los commands del form add contac
    CommandListener commandListenerFormContact = new CommandListener() {
        public void commandAction(Command command, Displayable displayable) {
            if (command == commandBack) {
                display.setCurrent(listMenu);
            }
            if (command == commandSave) {
                if (contactFormIsComplete()) {
                    Contact contact = new Contact(txtName.getString().trim(),
                            txtLastName.getString().trim(),
                            txtPhone.getString().trim(),
                            txtMobile.getString().trim(),
                            txtEmail.getString().trim(),
                            txtTwitterUsername.getString().trim());
                    contactsController.addContact(contact);
                    display.setCurrent(listMenu);
                    System.out.println(contactsController.getContacts().size());
                } else {
                    Alert alert = new Alert("Faltan datos", "ingrese todos los datos ", null, AlertType.ERROR);
                    display.setCurrent(alert, formAddContact);
                }
            }
        }
    };
    
    //listener para los commands del list
    CommandListener commandListenerForSeacrhContact = new CommandListener() {
        public void commandAction(Command command, Displayable displayable) {
            if (command == commandSearchContact) {
                if (txtInputContactSearch.getString().equals("")) {
                     Alert alert = new Alert("Faltan datos", "ingrese los datos de la busqueda ", null, AlertType.ERROR);
                    display.setCurrent(alert, formSearchContact);
                } else {
                    if (contactsController.getContact(txtInputContactSearch.getString().trim())!=null) {
                        stringItemSearchResult.setText(contactsController.getContact(txtInputContactSearch.getString().trim()).toString());
                    }
                }
            }
        }
    };
}
