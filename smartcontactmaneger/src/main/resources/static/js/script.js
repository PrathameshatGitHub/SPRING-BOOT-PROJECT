console.log("this is js")


function togglesidebar() {
    if ($(".sidebar").is(":visible")) {
      $(".sidebar").css("display", "none");
      $(".content").css("margin-left", "0%");
    } else {
      $(".sidebar").css("display", "block");
      $(".content").css("margin-left", "20%");
    }
  }
//   $(document).ready(function() {
//     // show the alert
//     setTimeout(function() {
//         $(".alert").alert('close');
//     }, 2000);
// });
