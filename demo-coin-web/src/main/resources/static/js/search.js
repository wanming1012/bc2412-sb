document.addEventListener('DOMContentLoaded', function() {
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
