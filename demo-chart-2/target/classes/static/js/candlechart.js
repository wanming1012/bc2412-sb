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
        tickMarkFormatter: (time) => {
          const date = new Date(time * 1000); // Convert Unix timestamp to milliseconds
          const year = date.getFullYear();
          const month = (date.getMonth() + 1).toString().padStart(2, "0"); // Ensure 2-digit month
          const day = (date.getDate()).toString().padStart(2, "0");
          return `${year}/${month}/${day}`;
        },
      },
    }
  );

  const candleSeries = chart.addCandlestickSeries({
    upColor: "#26a69a", // Green for bullish candles
    downColor: "#ef5350", // Red for bearish candles
    borderUpColor: "#26a69a",
    borderDownColor: "#ef5350",
    wickUpColor: "#26a69a",
    wickDownColor: "#ef5350",
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
      barSpacing: 20, // Increase or decrease to adjust the width of candles
    },
  });

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
            item.timestamp &&
            item.open !== null &&
            item.high !== null &&
            item.low !== null &&
            item.close !== null
        )
        .map((item) => ({
          time: Math.floor(Number(item.timestamp)),
          open: item.open,
          high: item.high,
          low: item.low,
          close: item.close,
        }))
        .sort((a, b) => a.time - b.time);
      // ! lightweight-chart.js cannot handle data without date order
      //console.log(stockData);
      candleSeries.setData(stockData);

      chart.timeScale().setVisibleRange({
        from: stockData[0].time, // Start from the first data point
        to: stockData[stockData.length - 1].time, // End at the last data point
      });
    })
    .catch((error) => {
      console.error("Error fetching candlestick data:", error);
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
      console.log(stockData);
      sma10Series.setData(stockData);
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
      console.log(stockData);
      sma20Series.setData(stockData);
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
    })
    .catch((error) => {
      console.error("Error fetching line chart data:", error);
    });
});
