package offeria.frontend.view.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import offeria.frontend.view.layout.MainLayout;

@PageTitle("Dashboard | RFQ Automation")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    public DashboardView() {
        setSpacing(true);
        setPadding(true);

        add(new H2("RFQ Automation Dashboard"));

        // Stats Row - Enterprise stats with Icons
        HorizontalLayout statsRow = new HorizontalLayout();
        statsRow.setWidthFull();
        statsRow.add(
            createStatCard("Active RFQs", "12", VaadinIcon.LIST, "var(--lumo-primary-color)"),
            createStatCard("Pending Proposals", "45", VaadinIcon.RECORDS, "var(--lumo-warning-color)"),
            createStatCard("Approved Proposals", "8", VaadinIcon.CHECK_CIRCLE, "var(--lumo-success-color)"),
            createStatCard("Budget Utilization", "72%", VaadinIcon.CHART, "var(--lumo-contrast-70pct)")
        );
        add(statsRow);

        // Placeholder for charts or recent activity
        VerticalLayout activityLayout = new VerticalLayout();
        activityLayout.add(new H2("Recent Activity"));
        activityLayout.add(new Span("Log: RFQ #1024 was updated by admin"));
        activityLayout.add(new Span("Log: New proposal received for RFQ #1021"));
        activityLayout.add(new Span("Log: Material 'Steel Pipe' stock low"));
        add(activityLayout);
    }

    private Component createStatCard(String title, String value, VaadinIcon icon, String color) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setPadding(true);
        layout.getStyle().set("border", "1px solid #e0e0e0");
        layout.getStyle().set("border-radius", "8px");
        layout.getStyle().set("background-color", "#f9f9f9");
        layout.setWidth("250px");

        Icon vaadinIcon = icon.create();
        vaadinIcon.getStyle().set("color", color);
        
        HorizontalLayout header = new HorizontalLayout(vaadinIcon, new Span(title));
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        Span val = new Span(value);
        val.getStyle().set("font-size", "24px");
        val.getStyle().set("font-weight", "bold");

        layout.add(header, val);
        return layout;
    }
}
