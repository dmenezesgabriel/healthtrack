// TODO
// Refactor
// This
// Ugly code
// viz breaks if more than one register per date (label)
function renderChart() {
  let data = [];
  data["labels"] = [];
  data["datasets"] = [];
  data["datasets"].push({
    label: "índice de massa corporal",
    backgroundColor: "rgb(255, 99, 132)",
    borderColor: "rgb(255, 99, 132)",
    data: [],
  });

  // Fetching data
  console.log("Fetching");
  fetch("bmi?action=overtime", {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (resData) {
      resData.forEach((element) => {
        let labelDate = `${element.measureDate.day}/${element.measureDate.month}/${element.measureDate.year}`;
        console.log(labelDate);
        data["labels"].push(labelDate);
        console.log(element.measureValue);
        data["datasets"][0]["data"].push(element.measureValue * 100);
        console.log(data["labels"]);
        chart.update();
      });
    });
  // render
  console.log(data);
  let chart = new Chart(document.querySelector("#bmiChart"), {
    type: "line",
    data: data,
  });
}
