"use strict";

!function (npl, $) {
  "use strict";

  var $win = $(window),
    $body = $('body'),
    breaks = npl.Break;

  // Variable
  var $file_dload = $('.file-dl-toast');
  npl.FileManager = function () {
    $file_dload.on("click", function (e) {
      e.preventDefault();
      npl.Toast('<h5>Downloading File</h5><p>Generating the file to start download.</p>', 'success', {
        position: 'bottom-center',
        icon: 'ni ni-download-cloud',
        ui: 'is-dark'
      });
    });
  };
  npl.coms.docReady.push(npl.FileManager);
}(npl, jQuery);