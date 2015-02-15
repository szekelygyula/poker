function startNewGame() {
	var playerName = $('#playerName').val();
	if(playerName == "") {
		alert('A j�t�kos nev�t k�telez� megadni.');
	} else {
		$.ajax({
			type: 'POST',
			url: 'GameServlet',
			data: {action: 'newGame', playerName: playerName}
		}).done(function(msg) {
			if(msg.status == 'success') {
				$('#handsHolder').empty();
				$('#gameHolder').show();
				$('#playerNameHolder').text(msg.playerName);
				$('#newDealButton').show();
				$('#evaluateButton').hide();
			} else {
				alert(msg.msg);
			}
		});
	}
}

function getNewDeal() {
	$.ajax({
		type: 'POST',
		url: 'GameServlet',
		data: {action: 'newDeal'}
	}).done(function(msg) {
		if(msg.status == 'success') {
			var newDealHolder = buildNewDeal(msg.cards);
			$('#handsHolder').prepend(newDealHolder);
			$('#newDealButton').hide();
			$('#evaluateButton').show();
		} else {
			alert(msg.msg);
		}
	});
}

function buildNewDeal(cards) {
	var dealHolder = $('<div class="deal_holder"></div>');
	for(var i = 0; i < cards.length; i++) {
		var actCard = $('<img style="padding: 5px; vertical-align: middle;" src="img/card/' + cards[i].suit + '_' + cards[i].rank + '.gif" />');
		dealHolder.append(actCard);
	}
	dealHolder.append($('<span class="evaluate_box" style="vertical-align: middle;"></span>'));
	return dealHolder;
}

function evaluateCards() {
	$.ajax({
		type: 'POST',
		url: 'GameServlet',
		data: {action: 'evaluate'}
	}).done(function(msg) {
		if(msg.status == 'success') {
			displayEvaluation(msg.hand);
			$('#newDealButton').show();
			$('#evaluateButton').hide();
		} else {
			alert(msg.msg);
		}
	});
}

function displayEvaluation(handObject) {
	var handBox = $('#handsHolder .deal_holder')[0];
	var displayBox = $(handBox).find('.evaluate_box');
//	displayBox.empty();
	displayBox.text(handObject.name);
}