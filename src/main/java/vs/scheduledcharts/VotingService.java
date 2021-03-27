package vs.scheduledcharts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class VotingService {

    private static final Logger log = LoggerFactory.getLogger(VotingService.class);

    private List<Vote> votes = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();
    private Random random = new Random();

    private final String CANDIDATE_A = "Candidate A";
    private final String CANDIDATE_B = "Candidate B";

    public List<Vote> findAllVotes() {
        return votes;
    }

    public ObjectNode summary() {
        var candidateAVotes =
                this.votes.stream().filter(v -> v.candidate().equals(CANDIDATE_A)).count();
        var candidateBVotes =
                this.votes.stream().filter(v -> v.candidate().equals(CANDIDATE_B)).count();

        return mapper.createObjectNode().put(CANDIDATE_A.toLowerCase(), candidateAVotes)
                .put(CANDIDATE_B.toLowerCase(), candidateBVotes).put("total", this.votes.size());
    }

    @Scheduled(fixedDelay = 3000)
    public void addVote() {

        // add a number of votes
        var numberOfVotes = randomNumber(1, 101);

        // for each vote, go for 50%-50% on the candidates
        for (int i = 0; i < numberOfVotes; i++) {
            if (randomNumber(1, 3) == 1) {
                votes.add(new Vote(i, CANDIDATE_A));
            } else {
                votes.add(new Vote(i, CANDIDATE_B));
            }
        }

        log.info("Total votes: " + votes.size());
    }

    private int randomNumber(int start, int end) {
        return random.ints(start, end).findFirst().getAsInt();
    }

}
