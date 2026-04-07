package offeria.frontend.service;

import offeria.frontend.model.RFQ;
import offeria.frontend.model.Material;
import offeria.frontend.model.Proposal;
import offeria.frontend.model.AuditLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MockDataService {

    private final List<RFQ> rfqs = new ArrayList<>();
    private final List<Material> materials = new ArrayList<>();
    private final List<Proposal> proposals = new ArrayList<>();
    private final List<AuditLog> auditLogs = new ArrayList<>();

    public MockDataService() {
        // Initialize with dummy data
        for (int i = 1; i <= 25; i++) {
            rfqs.add(new RFQ((long) i, "RFQ-NO-" + i, "RFQ Title " + i, "Remark for RFQ " + i, "Client " + (i % 3),
                i % 3 == 0 ? "Open" : (i % 3 == 1 ? "Draft" : "Closed"),
                LocalDateTime.now().minusDays(i), LocalDateTime.now().plusDays(i + 10),
                "Dept " + (i % 5), 1000.0 * i));
            
            materials.add(new Material((long) i, "Material " + i, "SKU-" + (1000 + i),
                "Category " + (i % 4), "Unit", 10.0 * i, 100 + i));
            
            proposals.add(new Proposal((long) i, (long) (i % 10 + 1), "Vendor " + i, 500.0 * i,
                i % 2 == 0 ? "Submitted" : "Under Review", LocalDateTime.now().minusHours(i),
                "Technical specs for proposal " + i, "Commercial terms for proposal " + i));
            
            auditLogs.add(new AuditLog((long) i, "User " + (i % 3), "Action " + i, "Entity " + (i % 2 == 0 ? "RFQ" : "Material"),
                (long) i, LocalDateTime.now().minusMinutes(i * 15), "Detailed log message " + i));
        }
    }

    public List<RFQ> getRFQs() { return rfqs; }
    public List<Material> getMaterials() { return materials; }
    public List<Proposal> getProposals() { return proposals; }
    public List<AuditLog> getAuditLogs() { return auditLogs; }

    public List<Material> searchMaterials(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) return materials;
        return materials.stream()
            .filter(m -> m.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                         m.getSku().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
    }
}
