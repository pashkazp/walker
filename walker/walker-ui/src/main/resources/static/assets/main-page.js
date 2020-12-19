/**
 * 
 */

var cellType = "w";


var divStart;
var divFinish;

var cellSet = $(".gamecell").toArray();
function setCellType(ch) {
	cellType = ch;
};

$(".gamecell").on("mousedown mouseenter", function(e) {
	if (e.buttons == 1 || e.buttons == 3) {
		var cellData = {
			cellNum: this.dataset.number,
			cellValue: cellType
		};
		stompClient.send("/app/walker/task/setcell", {}, JSON.stringify(cellData));
	}

}).on("mouseup mouseout", function(e) { });

/*function setCell(response) {
	this.dataset.value = cellType;
	switch (cellType) {
		case 'w':
			break;
		case 's':
			if (startPoint != 0) {
				divStart.setAttribute("data-value", " ");
				if (startPoint == finishPoint) {
					finishPoint = 0;
				}
			}
			divStart = this;
			startPoint = this.dataset.number;
			break;
		case 'f':
			if (finishPoint != 0) {
				divFinish.setAttribute("data-value", " ");
				if (startPoint == finishPoint) {
					startPoint = 0;
				}
			}
			divFinish = this;
			finishPoint = this.dataset.number;
			break;
		default:
			break;
	}
};
*/
// Shorthand for $( document ).ready()
$(function() {
	connect();
});

function clearall(response) {
	//console.log(frame)
	console.log(response);
	cellSet.forEach((gameCell) => {
		gameCell.setAttribute("data-value", " ");
	});
	$("input[name=optradiocell][value=wall]").prop('checked', true);
	$("input[name=optradioshape][value=square1]").prop('checked', true);
	$("input[name=optradiostep][value=ortho]").prop('checked', true);
}

function setCell(payload) {
	//	console.log('setcell: ' + payload);
	var message = JSON.parse(payload.body);
	cellSet[message.cellNum].setAttribute("data-value", message.cellValue);
}

function fillPath(payload) {
	var message = JSON.parse(payload.body);
	if (message.command == 'clear') {
		$.each(message.path, function(i, item) {
			cellSet[item.cellNum].setAttribute('data-value', " ");
			//$('.gamecell[data-number=' + item.cellNum + ']').attr('data-value', " ");
		});
	}
	if (message.command == 'set') {
		$.each(message.path, function(i, item) {
			cellSet[item.cellNum].setAttribute('data-value', item.cellValue);
			//$('.gamecell[data-number=' + item.cellNum + ']').attr('data-value', item.cellValue);
		});
	}
}

function connect() {
	var socket = new SockJS('/handshake');
	stompClient = Stomp.over(socket);
	stompClient.debug = null;
	stompClient.connect({}, function(frame) {
		stompClient.subscribe('/user/walker/commandclearall', clearall);
		stompClient.subscribe('/user/walker/solutions', function(response) {
			console.log('Connected: ' + frame);
		});
		stompClient.subscribe('/user/walker/commandclear', function(response) {
			console.log('Connected: ' + frame);
		});
		stompClient.subscribe('/user/walker/commandsetcell', setCell);
		stompClient.subscribe('/user/walker/commandfillpath', fillPath);
	});
}

function sendExec() {
	stompClient.send("/app/walker/task/execute", {}, "exec");
}

function sendShape(shapeName) {
	stompClient.send("/app/walker/task/setshape", {}, JSON.stringify({ 'shape': shapeName }));
}

function sendStepType(stepName) {
	stompClient.send("/app/walker/task/setsteptype", {}, stepName);
}

function sendClear() {
	stompClient.send("/app/walker/task/clearall", {}, "clear");
}