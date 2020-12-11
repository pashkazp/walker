/**
 * 
 */

var cellType = "w";


var divStart;
var divFinish;

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
	console.log(response)
	var gameCells = document.getElementsByClassName("gamecell")
	Array.from(gameCells).forEach((gameCell) => {
		gameCell.setAttribute("data-value", " ")
	})
}

function setCell(payload) {
//	console.log('setcell: ' + payload);
	var message = JSON.parse(payload.body);
	$('.gamecell[data-number=' + message.cellNum + ']').attr('data-value', message.cellValue);
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
	});
}

function sendMessage() {
	//var from = document.getElementById('from').value;
	//var text = document.getElementById('text').value;
	stompClient.send("/app/walker/task", {},
		JSON.stringify({ 'from': 'from' }));
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