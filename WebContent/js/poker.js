/**
 * Elk�ldi a k�r�st az �j j�t�kra
 */
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

/**
 * Elk�ldi a k�r�st az �j oszt�sra
 */
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

/**
 * L�trehoz egy leoszt�st tartalmaz� HTML objektumot.
 * @param cards A leoszt�sban szerepl� lapok
 * @returns A leoszt�s HTML ojektuma
 */
function buildNewDeal(cards) {
	var dealHolder = $('<div class="deal_holder"></div>');
	for(var i = 0; i < cards.length; i++) {
		var actCard = $('<img style="padding: 5px; vertical-align: middle;" src="img/card/' + cards[i].suit + '_' + cards[i].rank + '.gif" />');
		dealHolder.append(actCard);
	}
	dealHolder.append($('<span class="evaluate_box" style="vertical-align: middle;"></span>'));
	return dealHolder;
}

/**
 * Elk�ldi a k�r�st a lapok ki�rt�kel�s�re
 */
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

/**
 * Megjelen�ti a ki�rt�kel�s eredm�ny�t a lapok mellett
 * @param handObject A ki�rt�kel�s eredm�nye
 */
function displayEvaluation(handObject) {
	var handBox = $('#handsHolder .deal_holder')[0];
	var displayBox = $(handBox).find('.evaluate_box');
	displayBox.text(handObject.name);
}