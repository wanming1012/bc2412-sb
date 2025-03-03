$(document).ready(function() {
  // Function to refresh data
  function refreshData() {
    console.log("Refresh data....");
      $.ajax({
          url: '/coin-data/refresh',
          type: 'GET',
          success: function(data) {
              let tbody = $('#coinTableBody');
              title = title + '.';
              tbody.empty();
              data.forEach(function(coin) {
                let row = '<tr class="coinRow">' +
                      '<td>' +
                          '<p>' +
                              '<img src="' + coin.image + '" alt="Coin Image" height="50">' +
                              '<span class="coinName">' + coin.name + '</span> ' +
                              '<span>' + coin.symbol.toUpperCase() + '</span>' +
                          '</p>' +
                          '<p>' +
                              '<i style="font-size:0.9em">' + 
                                  new Date(coin.lastUpdated).toLocaleString('en-US', {
                                      timeZone: 'Asia/Hong_Kong',
                                      year: 'numeric', month: '2-digit', day: '2-digit',
                                      hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false
                                  }).replace(/,/, '') + ' HKT' +
                              '</i>' +
                          '</p>' +
                      '</td>' +
                      '<td>$' + coin.currentPrice.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) + '</td>' +
                      '<td style="color:' + (coin.priceChangePercentage24h >= 0 ? 'green' : 'red') + '" class="price-change">' +
                          '<i class="' + (coin.priceChangePercentage24h >= 0 ? 'fa fa-caret-up' : 'fa fa-caret-down') + '"></i>' +
                          '<span>' + coin.priceChangePercentage24h.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) + '%</span>' +
                      '</td>' +
                      '<td>$' + coin.marketCap.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) + '</td>' +
                      '</tr>';
                  tbody.append(row);
              });
              searchTable();
          },
          error: function() {
              console.error('Error refreshing data');
          }
      });
  }

  // Auto-refresh every 30 seconds
  setInterval(refreshData, 30000); // 30000 ms = 30 seconds

  var coinFilter = document.getElementById('coinFilter');
  var coinRows = document.querySelectorAll('.coinRow');

  function debounce(func, wait) {
    var timeout;
    return function() {
      var context = this, args = arguments;
      clearTimeout(timeout);
      timeout = setTimeout(function() {
        func.apply(context, args);
      }, wait);
    };
  }

  function searchTable() {
    var filter = $('#coinFilter').val().toUpperCase();
    var coinRows = $('.coinRow');

    coinRows.each(function() {
        var coinNameSpan = $(this).find('.coinName');
        var txtValue = coinNameSpan.text();
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            $(this).css('display', '');
            var originalText = txtValue;
            var highlightedText = originalText.replace(new RegExp(filter, 'gi'), function(match) {
                return '<span class="highlight">' + match + '</span>';
            });
            coinNameSpan.html(highlightedText);
        } else {
            $(this).css('display', 'none');
            coinNameSpan.text(txtValue); // Reset to original text
        }
    });
  }

  coinFilter.addEventListener('input', debounce(searchTable, 300));

});
