// TODO
// Refactor
// This
// Ugly code
// viz breaks if more than one register per date (label)
function renderBmiChart() {
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

function renderPressureChart() {
  let data = [];
  data["labels"] = [];
  data["datasets"] = [];
  data["datasets"].push({
    label: "Pressão Diastólica",
    backgroundColor: "rgb(54, 126,156)",
    borderColor: "rgb(54, 126,156)",
    data: [],
  });
  data["datasets"].push({
    label: "Pressão Sistólica",
    backgroundColor: "rgb(0, 151, 104)",
    borderColor: "rgb(0, 151, 104)",
    data: [],
  });

  // Fetching data
  console.log("Fetching");
  fetch("pressure?action=overtime", {
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
        data["datasets"][0]["data"].push(element.diastolicPressureValue);
        data["datasets"][1]["data"].push(element.systolicPressureValue);
        console.log(data["labels"]);
        chart.update();
      });
    });
  // render
  console.log(data);
  let chart = new Chart(document.querySelector("#pressureChart"), {
    type: "line",
    data: data,
  });
}
