package vs.scheduledcharts;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("votes")
@RequiredArgsConstructor
public class VotingController {

    private final VotingService service;

    @GetMapping
    public ObjectNode getSummary() {
        return service.summary();
    }

    @GetMapping("all")
    public List<Vote> getAllVotes() {
        return service.findAllVotes();
    }

}
