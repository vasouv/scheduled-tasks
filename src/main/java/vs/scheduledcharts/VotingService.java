package vs.scheduledcharts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class VotingService {

    private List<Vote> votes = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();
    private Random random = new Random();

    private final String CANDIDATE_A = "Candidate A";
    private final String CANDIDATE_B = "Candidate B";

    @PostConstruct
    public void init() {
        addVote();
    }

    public List<Vote> findAllVotes() {
        // to delete
        sample();
        return votes;
    }

    private void sample() {
        this.votes.clear();
        addVote();
    }

    public ObjectNode summary() {
        sample();
        var candidateAVotes = this.votes.stream().filter(v -> v.candidate.equals(CANDIDATE_A)).count();
        var candidateBVotes = this.votes.stream().filter(v -> v.candidate.equals(CANDIDATE_B)).count();

        return mapper.createObjectNode()
            .put(CANDIDATE_A.toLowerCase(), candidateAVotes)
            .put(CANDIDATE_B.toLowerCase(), candidateBVotes)
            .put("total", this.votes.size());

    }

    // @Scheduled(fixedDelay = 3000)
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
