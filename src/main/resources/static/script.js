window.addEventListener('load', (event) => {
    fetch('http://localhost:8080/votes')
        .then(response => response.json())
        .then(data => console.log(data));

});

let candidates = ["Candidate A", "Candidate B"];
let votes = [148, 123];
let colors = ["#49A9EA", "#36CAAB"];

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
            text: "Total number of votes",
            display: true
        },
        legend: {
            display: false
        }
    }
});
