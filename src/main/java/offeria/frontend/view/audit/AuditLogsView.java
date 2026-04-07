package offeria.frontend.view.audit;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import offeria.frontend.model.AuditLog;
import offeria.frontend.service.MockDataService;
import offeria.frontend.view.layout.MainLayout;

@PageTitle("Audit Logs | RFQ Automation")
@Route(value = "audit-logs", layout = MainLayout.class)
public class AuditLogsView extends VerticalLayout {

    private final MockDataService service;
    private final Grid<AuditLog> grid = new Grid<>(AuditLog.class, false);

    public AuditLogsView(MockDataService service) {
        this.service = service;
        setSizeFull();
        setPadding(true);

        add(new H2("System Audit Logs"));

        configureGrid();
        add(grid);

        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(new LocalDateTimeRenderer<>(AuditLog::getTimestamp, "yyyy-MM-dd HH:mm:ss")).setHeader("Timestamp").setSortable(true).setAutoWidth(true);
        grid.addColumn(AuditLog::getUser).setHeader("User").setSortable(true);
        grid.addColumn(AuditLog::getAction).setHeader("Action").setSortable(true);
        grid.addColumn(AuditLog::getEntity).setHeader("Entity").setSortable(true);
        grid.addColumn(AuditLog::getEntityId).setHeader("Entity ID").setAutoWidth(true);
        grid.addColumn(AuditLog::getDetails).setHeader("Details").setResizable(true);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);
    }

    private void updateList() {
        grid.setItems(service.getAuditLogs());
    }
}
