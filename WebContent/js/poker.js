/**
 * Elküldi a kérést az új játékra
 */
function startNewGame() {
	var playerName = $('#playerName').val();
	if(playerName == "") {
		alert('A játékos nevét kötelezõ megadni.');
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
 * Elküldi a kérést az új osztásra
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
 * Létrehoz egy leosztást tartalmazó HTML objektumot.
 * @param cards A leosztásban szereplõ lapok
 * @returns A leosztás HTML ojektuma
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
 * Elküldi a kérést a lapok kiértékelésére
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
 * Megjeleníti a kiértékelés eredményét a lapok mellett
 * @param handObject A kiértékelés eredménye
 */
function displayEvaluation(handObject) {
	var handBox = $('#handsHolder .deal_holder')[0];
	var displayBox = $(handBox).find('.evaluate_box');
	displayBox.text(handObject.name);
}