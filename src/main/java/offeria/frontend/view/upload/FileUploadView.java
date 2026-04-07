package offeria.frontend.view.upload;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import offeria.frontend.view.layout.MainLayout;

@PageTitle("File Upload | RFQ Automation")
@Route(value = "upload", layout = MainLayout.class)
public class FileUploadView extends VerticalLayout {

    public FileUploadView() {
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        add(new H2("RFQ Document Upload"));
        add(new Paragraph("Upload RFQ specifications, vendor lists, or terms and conditions."));

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/pdf", ".doc", ".docx", ".xls", ".xlsx");
        upload.setMaxFileSize(5 * 1024 * 1024); // 5MB

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            Notification n = Notification.show("File uploaded: " + fileName);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

        upload.addFailedListener(event -> {
            Notification n = Notification.show("Upload failed: " + event.getReason().getMessage());
            n.addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

        add(upload);
    }
}
