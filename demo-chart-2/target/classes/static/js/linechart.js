document.addEventListener("DOMContentLoaded", function () {
  const chart = LightweightCharts.createChart(
    document.getElementById("chart-container"),
    {
      width: 1000,
      height: 600,
      layout: {
        background: { type: "solid", color: "#000000" }, // Force black background
        textColor: "#ffffff", // White text for contrast
      },
      grid: {
        vertLines: { color: "#1f1f1f" }, // Dark grid
        horzLines: { color: "#1f1f1f" },
      },
      priceScale: {
        borderColor: "#7b5353",
      },
      timeScale: {
        borderColor: "#7b5353",
        timeVisible: true, // Enable time visibility
        secondsVisible: false,
        timezone: 'Asia/Hong_Kong',
        tickMarkFormatter: (time) => {
          const date = new Date(time * 1000); // Convert Unix timestamp to milliseconds
          const hours = date.getHours().toString().padStart(2, "0");
          const minutes = date.getMinutes().toString().padStart(2, "0");
          return `${hours}:${minutes}`; // Show time in HH:mm format
        },
        crosshair: {
          mode: LightweightCharts.CrosshairMode.Normal, // Ensure crosshair is active
        },
      },
    }
  );

  // Add line series instead of candlestick series
  const lineSeries = chart.addLineSeries({
    color: "#26a69a", // Green line color
    lineWidth: 1.5, // Line thickness
    priceFormat: {
      type: 'price',
      precision: 2, // 2 decimal places for price
      minMove: 0.01,
    },
  });

  const sma10Series = chart.addLineSeries({
    color: "#ffff00", // Green line color
    lineWidth: 1.5, // Line thickness
    priceFormat: {
      type: 'price',
      precision: 2, // 2 decimal places for price
      minMove: 0.01,
    },
  });

  const sma20Series = chart.addLineSeries({
    color: "#ff0000", // Green line color
    lineWidth: 1.5, // Line thickness
    priceFormat: {
      type: 'price',
      precision: 2, // 2 decimal places for price
      minMove: 0.01,
    },
  });

  const sma5Series = chart.addLineSeries({
    color: "#0000ff", // Green line color
    lineWidth: 1.5, // Line thickness
    priceFormat: {
      type: 'price',
      precision: 2, // 2 decimal places for price
      minMove: 0.01,
    },
  });

  chart.applyOptions({
    timeScale: {
      barSpacing: 20, // Adjust the spacing if necessary
    },
  });

  fetchLatestData();

  async function fetchLatestData() {
    const symbol = document.getElementById('symbol').value;
    const type = document.getElementById('type').value;
    const apiPath = document.getElementById('apiPath').value;
    const smaApiPath = document.getElementById('smaApiPath').value;
    
    fetch(`${apiPath}?symbol=${symbol}&type=${type}`)
      .then((response) => response.json())
      .then((data) => {
        const stockData = data
          .filter(
            (item) =>
              item.regularMarketTime &&
              item.regularMarketPrice !== null // Only need close price for line chart
          )
          .map((item) => ({
            time: Math.floor(Number(item.regularMarketTime)), // Unix timestamp
            value: item.regularMarketPrice, // Line chart uses value (close) data
          }))
          .sort((a, b) => a.time - b.time);
        // Set the data for the line series
        //console.log(stockData);
        lineSeries.setData(stockData);
  
        chart.timeScale().setVisibleRange({
          from: stockData[0].time, // Start from the first data point
          to: stockData[stockData.length - 1].time, // End at the last data point
        });
      })
      .catch((error) => {
        console.error("Error fetching line chart data:", error);
      });

    fetch(`${smaApiPath}?symbol=${symbol}&type=${type}&window=5`)
      .then((response) => response.json())
      .then((data) => {
        const stockData = data
          .filter(
            (item) =>
              item.regularMarketTime &&
              item.regularMarketPrice !== null // Only need close price for line chart
          )
          .map((item) => ({
            time: Math.floor(Number(item.regularMarketTime)), // Unix timestamp
            value: item.regularMarketPrice, // Line chart uses value (close) data
          }))
          .sort((a, b) => a.time - b.time);
        // Set the data for the line series
        //console.log(stockData);
        sma5Series.setData(stockData);
  
        // chart.timeScale().setVisibleRange({
        //   from: stockData[0].time, // Start from the first data point
        //   to: stockData[stockData.length - 1].time, // End at the last data point
        // });
      })
      .catch((error) => {
        console.error("Error fetching line chart data:", error);
      });

    fetch(`${smaApiPath}?symbol=${symbol}&type=${type}&window=10`)
      .then((response) => response.json())
      .then((data) => {
        const stockData = data
          .filter(
            (item) =>
              item.regularMarketTime &&
              item.regularMarketPrice !== null // Only need close price for line chart
          )
          .map((item) => ({
            time: Math.floor(Number(item.regularMarketTime)), // Unix timestamp
            value: item.regularMarketPrice, // Line chart uses value (close) data
          }))
          .sort((a, b) => a.time - b.time);
        // Set the data for the line series
        //console.log(stockData);
        sma10Series.setData(stockData);
  
        // chart.timeScale().setVisibleRange({
        //   from: stockData[0].time, // Start from the first data point
        //   to: stockData[stockData.length - 1].time, // End at the last data point
        // });
      })
      .catch((error) => {
        console.error("Error fetching line chart data:", error);
      });

    fetch(`${smaApiPath}?symbol=${symbol}&type=${type}&window=20`)
      .then((response) => response.json())
      .then((data) => {
        const stockData = data
          .filter(
            (item) =>
              item.regularMarketTime &&
              item.regularMarketPrice !== null // Only need close price for line chart
          )
          .map((item) => ({
            time: Math.floor(Number(item.regularMarketTime)), // Unix timestamp
            value: item.regularMarketPrice, // Line chart uses value (close) data
          }))
          .sort((a, b) => a.time - b.time);
        // Set the data for the line series
        //console.log(stockData);
        sma20Series.setData(stockData);
  
        // chart.timeScale().setVisibleRange({
        //   from: stockData[0].time, // Start from the first data point
        //   to: stockData[stockData.length - 1].time, // End at the last data point
        // });
      })
      .catch((error) => {
        console.error("Error fetching line chart data:", error);
      });
  }
  
  // Fetch and update every 5 seconds
  setInterval(fetchLatestData, 60000);
});
