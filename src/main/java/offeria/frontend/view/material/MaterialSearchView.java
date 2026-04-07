package offeria.frontend.view.material;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import offeria.frontend.model.Material;
import offeria.frontend.service.MockDataService;
import offeria.frontend.view.layout.MainLayout;

@PageTitle("Materials | RFQ Automation")
@Route(value = "materials", layout = MainLayout.class)
public class MaterialSearchView extends VerticalLayout {

    private final MockDataService service;
    private final Grid<Material> grid = new Grid<>(Material.class, false);
    private final TextField searchField = new TextField();

    public MaterialSearchView(MockDataService service) {
        this.service = service;
        setSizeFull();
        setPadding(true);

        add(new H2("Material Catalog Search"));

        configureGrid();
        add(createToolbar(), grid);

        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(Material::getSku).setHeader("SKU").setSortable(true);
        grid.addColumn(Material::getName).setHeader("Name").setSortable(true);
        grid.addColumn(Material::getCategory).setHeader("Category").setSortable(true);
        grid.addColumn(Material::getUnit).setHeader("Unit");
        grid.addColumn(new NumberRenderer<>(Material::getUnitPrice, "$#,###.00")).setHeader("Unit Price").setSortable(true);
        grid.addColumn(Material::getStock).setHeader("Stock").setSortable(true);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);
    }

    private HorizontalLayout createToolbar() {
        searchField.setPlaceholder("Search by name or SKU...");
        searchField.setClearButtonVisible(true);
        searchField.setWidth("400px");
        searchField.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(searchField);
        toolbar.setPadding(true);
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.searchMaterials(searchField.getValue()));
    }
}
