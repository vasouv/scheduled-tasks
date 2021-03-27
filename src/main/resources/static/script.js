let candidates = ["Candidate A", "Candidate B"];
let colors = ["#49A9EA", "#36CAAB"];

// asynchronously calls the backend and returns the response
async function getVotes() {
	try {
		let response = await fetch("http://localhost:8080/votes");
		let data = await response.json();
    	return data;	
	} catch (error){
		console.log("Failed to fetch from backend");
		let error2 = {"candidate a":0, "candidate b":0};
		return error2;
	}
    
}

// creates the chart
async function createChart() {

    // call the API
    let votesResult = await getVotes();

    // get the total number of votes
    let totalVotes = await votesResult.total;

    // create an array with the number of votes for each candidate
    let votes = await [votesResult["candidate a"], votesResult["candidate b"]];

    let pollChart = document.getElementById('pollChart').getContext('2d');
    let chart = new Chart(pollChart, {
        type: 'horizontalBar',
        data: {
            labels: candidates,
            datasets: [{
                data: votes,
                backgroundColor: colors
            }]
        },

        // Configuration options go here
        options: {
            title: {
                text: `Total number of votes: ${totalVotes}`,
                display: true
            },
            legend: {
                display: false
            },
            scales: {
                xAxes: [{
                    offset: true,
					ticks: {
						beginAtZero: true
					}
                }]
            },
			responsive: false
        }
    });
}

window.setInterval('createChart()', 5000);
