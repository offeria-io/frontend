package offeria.frontend.view.rfq;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import offeria.frontend.model.RFQ;
import offeria.frontend.model.RFQItem;
import offeria.frontend.view.layout.MainLayout;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Create RFQ | RFQ Automation")
@Route(value = "create-rfq", layout = MainLayout.class)
public class CreateRFQView extends VerticalLayout {

    private final Binder<RFQ> binder = new Binder<>(RFQ.class);
    private final Grid<RFQItem> itemGrid = new Grid<>(RFQItem.class, false);
    private final List<RFQItem> items = new ArrayList<>();

    public CreateRFQView() {
        setPadding(true);
        setMaxWidth("1000px");
        setMargin(true);

        add(new H2("Create New RFQ"));

        FormLayout formLayout = new FormLayout();

        TextField rfqNumber = new TextField("RFQ Number");
        binder.forField(rfqNumber).bind(RFQ::getRfqNumber, RFQ::setRfqNumber);

        TextField title = new TextField("RFQ Title");
        title.setRequiredIndicatorVisible(true);
        binder.forField(title).asRequired("Title is required").bind(RFQ::getTitle, RFQ::setTitle);

        TextField clientName = new TextField("Client Name");
        clientName.setRequiredIndicatorVisible(true);
        binder.forField(clientName).asRequired("Client name is required").bind(RFQ::getClientName, RFQ::setClientName);

        TextArea remark = new TextArea("Remark / Notes");
        binder.forField(remark).bind(RFQ::getRemark, RFQ::setRemark);

        ComboBox<String> department = new ComboBox<>("Department");
        department.setItems("Procurement", "IT", "HR", "Operations", "Finance");
        binder.forField(department).bind(RFQ::getDepartment, RFQ::setDepartment);

        NumberField budget = new NumberField("Budget ($)");
        budget.setStep(0.01);
        binder.forField(budget).bind(RFQ::getBudget, RFQ::setBudget);

        DatePicker deadlineDate = new DatePicker("Deadline Date");
        binder.forField(deadlineDate)
                .asRequired("Deadline is required")
                .bind(rfq -> rfq.getDeadline() != null ? rfq.getDeadline().toLocalDate() : null,
                      (rfq, date) -> rfq.setDeadline(date != null ? LocalDateTime.of(date, LocalTime.MAX) : null));

        formLayout.add(rfqNumber, title, clientName, department, budget, deadlineDate, remark);
        formLayout.setColspan(remark, 2);

        // RFQ Items Section
        add(formLayout);
        add(new H3("RFQ Items"));
        setupItemGrid();
        add(itemGrid);

        Button addItemBtn = new Button("Add Item", new Icon(VaadinIcon.PLUS), e -> openItemDialog(null));
        add(addItemBtn);

        Button save = new Button("Create RFQ", e -> save());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button cancel = new Button("Cancel", e -> clear());

        add(new HorizontalLayout(save, cancel));
        
        binder.setBean(new RFQ());
    }

    private void setupItemGrid() {
        itemGrid.setItems(items);

        itemGrid.addColumn(RFQItem::getName).setHeader("Item");
        itemGrid.addColumn(RFQItem::getDescription).setHeader("Description");
        itemGrid.addColumn(RFQItem::getQuantity).setHeader("Qty");
        itemGrid.addColumn(RFQItem::getUnit).setHeader("Unit");
        itemGrid.addColumn(RFQItem::getUnitPrice).setHeader("Unit Price");
        itemGrid.addColumn(RFQItem::getRemark).setHeader("Remark");

        itemGrid.addComponentColumn(item -> {
            Button editBtn = new Button(new Icon(VaadinIcon.EDIT), e -> openItemDialog(item));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            
            Button removeBtn = new Button(new Icon(VaadinIcon.TRASH), e -> {
                items.remove(item);
                itemGrid.getDataProvider().refreshAll();
            });
            removeBtn.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
            
            HorizontalLayout layout = new HorizontalLayout(editBtn, removeBtn);
            layout.setPadding(false);
            return layout;
        }).setHeader("Actions");
    }

    private void openItemDialog(RFQItem itemToEdit) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(itemToEdit == null ? "Add New Item" : "Edit Item");

        FormLayout dialogFormLayout = new FormLayout();
        
        TextField nameField = new TextField("Item");
        TextField descField = new TextField("Description");
        NumberField qtyField = new NumberField("Qty");
        TextField unitField = new TextField("Unit");
        NumberField priceField = new NumberField("Unit Price");
        TextField itemRemarkField = new TextField("Remark");

        dialogFormLayout.add(nameField, descField, qtyField, unitField, priceField, itemRemarkField);
        dialogFormLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        
        Binder<RFQItem> itemBinder = new Binder<>(RFQItem.class);
        itemBinder.forField(nameField).asRequired("Item name is required").bind(RFQItem::getName, RFQItem::setName);
        itemBinder.forField(descField).bind(RFQItem::getDescription, RFQItem::setDescription);
        itemBinder.forField(qtyField)
                .bind(val -> val.getQuantity().doubleValue(), (item, val) -> item.setQuantity(BigDecimal.valueOf(val)));
        itemBinder.forField(unitField).bind(RFQItem::getUnit, RFQItem::setUnit);
        itemBinder.forField(priceField)
                .bind(val -> val.getUnitPrice().doubleValue(), (item, val) -> item.setUnitPrice(BigDecimal.valueOf(val)));
        itemBinder.forField(itemRemarkField).bind(RFQItem::getRemark, RFQItem::setRemark);

        RFQItem item = itemToEdit != null ? itemToEdit : new RFQItem();
        itemBinder.setBean(item);

        Button saveButton = new Button(itemToEdit == null ? "Add" : "Save", e -> {
            if (itemBinder.validate().isOk()) {
                if (itemToEdit == null) {
                    items.add(item);
                }
                itemGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton, saveButton);

        dialog.add(dialogFormLayout);
        dialog.open();
    }

    private void save() {
        if (binder.validate().isOk()) {
            RFQ rfq = binder.getBean();
            rfq.setItems(items);
            // In a real app, call backend service here
            Notification n = Notification.show("RFQ Created successfully!");
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            clear();
        } else {
            Notification.show("Please correct the errors", 3000, Notification.Position.MIDDLE)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    private void clear() {
        binder.setBean(new RFQ());
        items.clear();
        itemGrid.getDataProvider().refreshAll();
    }
}
