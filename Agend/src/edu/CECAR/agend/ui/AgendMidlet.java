package edu.CECAR.agend.ui;

import edu.CECAR.agend.logic.Contact;
import edu.CECAR.agend.logic.ContactsController;
import edu.CECAR.agend.logic.CurrencyConverter;
import edu.CECAR.agend.logic.Task;
import edu.CECAR.agend.logic.TasksController;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;

/**
 * @author Yesid Lazaro Mayoriano
 */
public class AgendMidlet extends MIDlet {

    private ContactsController contactsController;
    private TasksController tasksController;
    private Display display;
    private List listMenu;
    private StringItem stringItemSearchResult;
    private StringItem stringItemSearchTaskResult;
    private Form formAddContact;
    private Form formAddTask;
    private Form formSearchContact;
    private Form formSearchTask;
    private Form formCurrencyConverter;
    private TextField txtName;
    private TextField txtInputContactSearch;
    private TextField txtInputTaskSearch;
    private TextField txtLastName;
    private TextField txtPhone;
    private TextField txtMobile;
    private TextField txtEmail;
    private TextField txtTwitterUsername;
    private TextField txtInputCurrency;
    private TextField txtResultCurrency;
    private TextField txtTaskName;
    private TextField txtTaskDescription;
    private DateField dateFieldTaskDate;
    private ChoiceGroup choiceGroupConverterOptions;
    private final String COP = "COP";
    private final String USD = "USD";
    private final String EURO = "EURO";
    private final String[]  menuOptios = new String[]{
                                  "Agregar Contacto",
                                  "Buscar Contacto",
                                  "Convertidor de Monedas",
                                  "Agregar Tarea",
                                  "Buscar Tarea"};
    private final String[] converterOptions =
            new String[]{"COP -> USD",
        "COP -> EURO",
        "USD -> COP",
        "USD -> EURO",
        "EURO -> USD",
        "EURO -> COP",};
    private Command commandSelect;
    private Command commandSave;
    private Command commandConvert;
    private Command commandBack;
    private Command commandSearch;

    public AgendMidlet() {
       
        display = Display.getDisplay(AgendMidlet.this);
        listMenu = new List("Menu", List.EXCLUSIVE, menuOptios, null);

        formAddContact = new Form("Agregar Contact");
        formSearchContact = new Form("buscar Contact");
        formCurrencyConverter = new Form("Convertidor");
        formAddTask = new Form("Agregar Tarea");
        formSearchTask = new Form("Buscar Tarea");

        commandSelect = new Command("Seleccionar", Command.OK, 0);
        commandBack = new Command("Atras", Command.BACK, 0);
        commandSave = new Command("Guardar", Command.SCREEN, 0);
        commandSearch = new Command("buscar", Command.OK, 0);
        commandConvert = new Command("Convertir", Command.OK, 0);

        txtName = new TextField("Nombre", "", 100, TextField.ANY);
        txtLastName = new TextField("Apellido", "", 100, TextField.ANY);
        txtPhone = new TextField("Telefono", "", 100, TextField.PHONENUMBER);
        txtMobile = new TextField("Celular", "", 100, TextField.PHONENUMBER);
        txtEmail = new TextField("Correo Electronico", "", 100, TextField.EMAILADDR);
        txtTwitterUsername = new TextField("Usuario de Twitter", "", 100, TextField.ANY);

        txtInputContactSearch = new TextField("Nombre/apellido o telefono", "", 100, TextField.ANY);
        txtInputTaskSearch = new TextField("Nombre", "", 100, TextField.ANY);
        stringItemSearchResult = new StringItem("Resultado", "");
        stringItemSearchTaskResult = new StringItem("Resultado", "");

        txtInputCurrency = new TextField("COP", "", 100, TextField.NUMERIC);
        txtResultCurrency = new TextField("USD", "", 100, TextField.UNEDITABLE);

        txtTaskName = new TextField("Nombre", "", 100, TextField.ANY);
        txtTaskDescription = new TextField("Descripcion", "", 100, TextField.ANY);
        dateFieldTaskDate = new DateField("Fecha", DateField.DATE_TIME);

        choiceGroupConverterOptions = new ChoiceGroup("", ChoiceGroup.POPUP, converterOptions, null);

        listMenu.addCommand(commandSelect);
        listMenu.setCommandListener(commandListenerMenu);

        // form add contact
        formAddContact.append(txtName);
        formAddContact.append(txtLastName);
        formAddContact.append(txtPhone);
        formAddContact.append(txtMobile);
        formAddContact.append(txtEmail);
        formAddContact.append(txtTwitterUsername);
        formAddContact.addCommand(commandBack);
        formAddContact.addCommand(commandSave);
        formAddContact.setCommandListener(commandListenerFormContact);

        // form search contact
        formSearchContact.append(txtInputContactSearch);
        formSearchContact.append(stringItemSearchResult);

        formSearchContact.addCommand(commandSearch);
        formSearchContact.addCommand(commandBack);
        formSearchContact.setCommandListener(commandListenerForSearchContact);

        // form search task
        formSearchTask.append(txtInputTaskSearch);
        formSearchTask.append(stringItemSearchTaskResult);

        formSearchTask.addCommand(commandSearch);
        formSearchTask.addCommand(commandBack);
        formSearchTask.setCommandListener(commandListenerFormSearchTask);
        //form add task
        formAddTask.append(txtTaskName);
        formAddTask.append(txtTaskDescription);
        formAddTask.append(dateFieldTaskDate);

        formAddTask.addCommand(commandSave);
        formAddTask.addCommand(commandBack);
        formAddTask.setCommandListener(commandListenerFormTask);
        //form currency convert
        formCurrencyConverter.append(choiceGroupConverterOptions);
        formCurrencyConverter.append(txtInputCurrency);
        formCurrencyConverter.append(txtResultCurrency);
        formCurrencyConverter.addCommand(commandConvert);
        formCurrencyConverter.addCommand(commandBack);
        formCurrencyConverter.setCommandListener(commandListenerFormConverter);
        formCurrencyConverter.setItemStateListener(new ItemStateListener() {
            public void itemStateChanged(Item item) {
                if (item == choiceGroupConverterOptions) {
                    if (choiceGroupConverterOptions.getSelectedIndex() == 0) {
                        txtInputCurrency.setLabel(COP);
                        txtResultCurrency.setLabel(USD);
                    }
                    if (choiceGroupConverterOptions.getSelectedIndex() == 1) {
                        txtInputCurrency.setLabel(COP);
                        txtResultCurrency.setLabel(EURO);
                    }
                    if (choiceGroupConverterOptions.getSelectedIndex() == 2) {
                        txtInputCurrency.setLabel(USD);
                        txtResultCurrency.setLabel(COP);
                    }
                    if (choiceGroupConverterOptions.getSelectedIndex() == 3) {
                        txtInputCurrency.setLabel(USD);
                        txtResultCurrency.setLabel(EURO);
                    }
                    if (choiceGroupConverterOptions.getSelectedIndex() == 4) {
                        txtInputCurrency.setLabel(EURO);
                        txtResultCurrency.setLabel(USD);
                    }
                    if (choiceGroupConverterOptions.getSelectedIndex() == 5) {
                        txtInputCurrency.setLabel(EURO);
                        txtResultCurrency.setLabel(COP);
                    }
                }
            }
        });
        contactsController = new ContactsController();
        tasksController = new TasksController();
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

    public boolean taskFormIsComplete() {
        if (txtTaskName.getString().equals("")
                || txtTaskDescription.getString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public void clearAddContactForm() {
        txtName.setString("");
        txtLastName.setString("");
        txtPhone.setString("");
        txtMobile.setString("");
        txtTwitterUsername.setString("");
        txtEmail.setString("");
    }

    public void clearAddTaskForm() {
        txtTaskDescription.setString("");
        txtTaskName.setString("");

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
                    case 2:
                        display.setCurrent(formCurrencyConverter);
                        break;
                    case 3:
                        display.setCurrent(formAddTask);
                        break;
                    case 4:
                        display.setCurrent(formSearchTask);
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
                    clearAddContactForm();
                    display.setCurrent(listMenu);

                } else {
                    Alert alert = new Alert("Faltan datos", "ingrese todos los datos ", null, AlertType.ERROR);
                    display.setCurrent(alert, formAddContact);
                }
            }
        }
    };
    //listener para los commands del form add contac
    CommandListener commandListenerFormTask = new CommandListener() {
        public void commandAction(Command command, Displayable displayable) {
            if (command == commandBack) {
                display.setCurrent(listMenu);
            }
            if (command == commandSave) {
                if (taskFormIsComplete()) {

                    Task task = new Task(txtTaskName.getString().trim(), txtTaskDescription.getString().trim(), dateFieldTaskDate.getDate());
                    display.setCurrent(listMenu);
                    tasksController.addTask(task);
                    clearAddTaskForm();
                    display.setCurrent(listMenu);
                } else {
                    Alert alert = new Alert("Faltan datos", "ingrese todos los datos ", null, AlertType.ERROR);
                    display.setCurrent(alert, formAddContact);
                }
            }
        }
    };
    //listener para los commands del form agregar tarea
    CommandListener commandListenerForSearchContact = new CommandListener() {
        public void commandAction(Command command, Displayable displayable) {
            if (command == commandSave) {
                if (txtInputTaskSearch.getString().equals("")) {
                    Alert alert = new Alert("Faltan datos", "ingrese los datos para la busqueda ", null, AlertType.ERROR);
                    display.setCurrent(alert, formSearchTask);
                } else {
                    if (tasksController.getTask(txtInputTaskSearch.getString().trim()) != null) {
                        stringItemSearchResult.setText(tasksController.getTask(txtInputTaskSearch.getString().trim()).toString());
                    }
                }
            }
            if (command == commandBack) {
                display.setCurrent(listMenu);
            }
        }
    };
    //listener para los commands del form agregar tarea
    CommandListener commandListenerFormSearchTask = new CommandListener() {
        public void commandAction(Command command, Displayable displayable) {
            if (command == commandSave) {
                if (txtInputContactSearch.getString().equals("")) {
                    Alert alert = new Alert("Faltan datos", "ingrese los datos para la busqueda ", null, AlertType.ERROR);
                    display.setCurrent(alert, formSearchContact);
                } else {
                    if (contactsController.getContact(txtInputContactSearch.getString().trim()) != null) {
                        stringItemSearchResult.setText(contactsController.getContact(txtInputContactSearch.getString().trim()).toString());
                    }
                }
            }
            if (command == commandBack) {
                display.setCurrent(listMenu);
            }
        }
    };
    //listener para los commands del form buscar contacto
    CommandListener commandListenerFormConverter = new CommandListener() {
        public void commandAction(Command command, Displayable displayable) {
            if (command == commandConvert) {
                if (txtInputCurrency.getString().equals("")) {
                    Alert alert = new Alert("Faltan datos", "ingrese una cantidad ", null, AlertType.ERROR);
                    display.setCurrent(alert, formCurrencyConverter);
                } else {
                    switch (choiceGroupConverterOptions.getSelectedIndex()) {
                        case 0:
                            txtResultCurrency.setString("" + CurrencyConverter.COPToUSD(Double.parseDouble(txtInputCurrency.getString().trim())));
                            break;
                        case 1:
                            txtResultCurrency.setString("" + CurrencyConverter.COPToEURO(Double.parseDouble(txtInputCurrency.getString().trim())));

                            break;
                        case 2:
                            txtResultCurrency.setString("" + CurrencyConverter.USDToCOP(Double.parseDouble(txtInputCurrency.getString().trim())));

                            break;
                        case 3:
                            txtResultCurrency.setString("" + CurrencyConverter.USDToEURO(Double.parseDouble(txtInputCurrency.getString().trim())));

                            break;
                        case 4:
                            txtResultCurrency.setString("" + CurrencyConverter.EUROToUSD(Double.parseDouble(txtInputCurrency.getString().trim())));

                            break;
                        case 5:
                            txtResultCurrency.setString("" + CurrencyConverter.EUROToCOP(Double.parseDouble(txtInputCurrency.getString().trim())));

                            break;
                    }
                }
            }
            if (command == commandBack) {
                display.setCurrent(listMenu);
            }
        }
    };
}
