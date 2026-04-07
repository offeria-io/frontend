package offeria.frontend.view.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import offeria.frontend.view.audit.AuditLogsView;
import offeria.frontend.view.dashboard.DashboardView;
import offeria.frontend.view.material.MaterialSearchView;
import offeria.frontend.view.proposal.ProposalView;
import offeria.frontend.view.rfq.CreateRFQView;
import offeria.frontend.view.rfq.RFQListView;
import offeria.frontend.view.upload.FileUploadView;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Offeria RFQ");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.getStyle().set("padding", "0 var(--lumo-space-m)");

        addToNavbar(header);
    }

    private void createDrawer() {
        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("Dashboard", DashboardView.class, VaadinIcon.DASHBOARD.create()));
        nav.addItem(new SideNavItem("RFQs", RFQListView.class, VaadinIcon.LIST.create()));
        nav.addItem(new SideNavItem("Create RFQ", CreateRFQView.class, VaadinIcon.PLUS.create()));
        nav.addItem(new SideNavItem("Materials", MaterialSearchView.class, VaadinIcon.PACKAGE.create()));
        nav.addItem(new SideNavItem("Proposals", ProposalView.class, VaadinIcon.RECORDS.create()));
        nav.addItem(new SideNavItem("Upload", FileUploadView.class, VaadinIcon.UPLOAD.create()));
        nav.addItem(new SideNavItem("Audit Logs", AuditLogsView.class, VaadinIcon.FILE_TEXT.create()));

        addToDrawer(new VerticalLayout(nav));
    }
}
