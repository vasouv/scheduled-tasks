package vs.scheduledcharts;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("votes")
public class VotingController {

    private final VotingService service;

    public VotingController(VotingService service) {
        this.service = service;
    }

    @GetMapping
    public ObjectNode getSummary() {
        return service.summary();
    }

    @GetMapping("all")
    public List<Vote> getAllVotes() {
        return service.findAllVotes();
    }

}
