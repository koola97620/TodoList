function getTaskList(boardId){
  var html='';

  $.getJSON("/api/board/" + boardId ,
      function(list) {


      });
}


function getModal() {
  $('.ui.modal')
  .modal('show')
  ;
}


// $(function () {
//   $('#datetimepicker1').datetimepicker();
// });
//
// $(function () {
//   $('#datetimepicker2').datetimepicker();
// });
