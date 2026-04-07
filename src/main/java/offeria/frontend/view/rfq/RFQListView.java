package offeria.frontend.view.rfq;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import offeria.frontend.model.RFQ;
import offeria.frontend.service.MockDataService;
import offeria.frontend.view.layout.MainLayout;

@PageTitle("RFQs | RFQ Automation")
@Route(value = "rfqs", layout = MainLayout.class)
public class RFQListView extends VerticalLayout {

    private final MockDataService service;
    private final Grid<RFQ> grid = new Grid<>(RFQ.class, false);

    public RFQListView(MockDataService service) {
        this.service = service;
        setSizeFull();
        setPadding(true);

        add(new H2("Request For Quotations"));

        configureGrid();
        add(createToolbar(), grid);

        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(RFQ::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(RFQ::getTitle).setHeader("Title").setSortable(true).setResizable(true);
        grid.addColumn(new LocalDateTimeRenderer<>(RFQ::getCreatedDate, "yyyy-MM-dd HH:mm")).setHeader("Created").setSortable(true);
        grid.addColumn(new LocalDateTimeRenderer<>(RFQ::getDeadline, "yyyy-MM-dd HH:mm")).setHeader("Deadline").setSortable(true);
        grid.addColumn(RFQ::getDepartment).setHeader("Department").setSortable(true);
        grid.addColumn(new NumberRenderer<>(RFQ::getBudget, "$#,###.00")).setHeader("Budget").setSortable(true);
        
        // Custom renderer for status - providing visual enterprise-level cues
        grid.addComponentColumn(rfq -> {
            Span status = new Span(rfq.getStatus());
            String color = "var(--lumo-contrast-50pct)";
            if ("Open".equals(rfq.getStatus())) color = "var(--lumo-success-color)";
            else if ("Draft".equals(rfq.getStatus())) color = "var(--lumo-primary-color)";
            else if ("Closed".equals(rfq.getStatus())) color = "var(--lumo-error-color)";
            status.getStyle().set("background-color", color);
            status.getStyle().set("color", "white");
            status.getStyle().set("padding", "var(--lumo-space-xs) var(--lumo-space-s)");
            status.getStyle().set("border-radius", "var(--lumo-border-radius-l)");
            status.getStyle().set("font-size", "var(--lumo-font-size-xs)");
            status.getStyle().set("font-weight", "600");
            return status;
        }).setHeader("Status");

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);
    }

    private VerticalLayout createToolbar() {
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setWidth("300px");
        filter.addValueChangeListener(e -> updateList());

        return new VerticalLayout(filter);
    }

    private void updateList() {
        grid.setItems(service.getRFQs());
    }
}
