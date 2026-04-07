package offeria.frontend.view.proposal;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import offeria.frontend.model.Proposal;
import offeria.frontend.service.MockDataService;
import offeria.frontend.view.layout.MainLayout;

@PageTitle("Proposals | RFQ Automation")
@Route(value = "proposals", layout = MainLayout.class)
public class ProposalView extends VerticalLayout {

    private final MockDataService service;
    private final Grid<Proposal> grid = new Grid<>(Proposal.class, false);

    public ProposalView(MockDataService service) {
        this.service = service;
        setSizeFull();
        setPadding(true);

        add(new H2("Vendor Proposals"));

        configureGrid();
        
        // Split view: Grid on top, Details (Accordion) on bottom when selected
        add(grid);

        grid.asSingleSelect().addValueChangeListener(event -> {
            // Placeholder for showing details when a proposal is selected
            if (event.getValue() != null) {
                showProposalDetails(event.getValue());
            }
        });

        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(Proposal::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Proposal::getRfqId).setHeader("RFQ ID").setSortable(true);
        grid.addColumn(Proposal::getVendorName).setHeader("Vendor").setSortable(true);
        grid.addColumn(new NumberRenderer<>(Proposal::getTotalAmount, "$#,###.00")).setHeader("Total Amount").setSortable(true);
        grid.addColumn(new LocalDateTimeRenderer<>(Proposal::getSubmissionDate, "yyyy-MM-dd HH:mm")).setHeader("Submitted").setSortable(true);
        grid.addColumn(Proposal::getStatus).setHeader("Status");

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);
    }

    private void showProposalDetails(Proposal proposal) {
        // We could add an accordion here to show Technical and Commercial details
        Accordion details = new Accordion();
        details.add("Technical Proposal", new VerticalLayout(new Span(proposal.getTechnicalDetails())));
        details.add("Commercial Proposal", new VerticalLayout(new Span(proposal.getCommercialDetails())));
        details.setWidthFull();

        // For simplicity, let's just show a notification or add it to the layout
        // In a real app, this would be a Dialog or a side panel
        add(new H2("Details for Proposal #" + proposal.getId()), details);
    }

    private void updateList() {
        grid.setItems(service.getProposals());
    }
}
