$(document).ready(function () {
	$("#myInput").on("keyup", function () {
		var value = $(this).val().toLowerCase();
		$("#myTable tr").filter(function () {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

	// Dynamic data
	var jsonArrayObj = [
		{name: "Champion"},
		
	];

	var page_number = 1;
	var records_per_page = 1;
	var total_page = Math.ceil(jsonArrayObj.length / records_per_page);

	// Pagination button
	$.fn.displayPaginationButtons = function() {
		var buttons_text = `<a class="page-link">Previous</a>`;
		var active = "";

		for(var i = 1; i <= total_page; i++) {
			if(i == 1) {
				active = "active";
			}
			else {
				active = "";
			}

			buttons_text = buttons_text + `<li class="page-item"><a id="page_index +` + i + `" class="page-link page_index ` + active + `" href="#">` + i + `</a></li>`;
		}

		buttons_text = buttons_text + `<a class="page-link" href="#" onClick="javascript:$.fn.nextPage();">Next</a>`;
		$(".pagination-buttons").text("");
		$(".pagination-buttons").append(buttons_text);
	}

	$.fn.displayPaginationButtons();

	// Data from json
	$.fn.displayTableData = function() {
		var start_index = (page_number - 1) * records_per_page;
		var end_index = start_index + (records_per_page - 1);
		end_index = (end_index >= jsonArrayObj.length) ? jsonArrayObj.length - 1 : end_index;

		var inner_html = "";
		for(var i = start_index; i <= end_index; i++) {
			inner_html = inner_html + 	`<tr>
											<td>` + (i + 1) + `</td>
											<td>` + jsonArrayObj[i].name + `</td>
											<td>
												<button name="btn_edit_employee" class="btn btn-outline-secondary">Edit</button>
												<button name="btn_delete_employee" class="btn btn-outline-danger">Delete</button>
											</td>
										</tr>`
		}

		$("table tbody tr").remove();
		$("table tbody").append(inner_html);

		$(".page_index").removeClass("active");
		$("#page_index" + page_number).addClass("active");
	}

	// // Next page
	$.fn.nextPage = function() {
		page_number++;
		$.fn.displayTableData();
	}

	$.fn.displayTableData();
	
});