

var data = [
	{
		label: "选项一",
		value: 30,
		color:"#F38630"
	},
	{
		value : 50,
		color : "#E0E4CC"
	},
	{
		value : 100,
		color : "#69D2E7"
	},
	{
		value : 100,
		color : "#949FB1"
	}
];

var chartPie = null;
window.onload = function(){				
	var ctx = document.getElementById("myPieChart").getContext("2d");
	chartPie = new Chart(ctx).Pie(data, {segmentShowStroke : false, showTooltips : 1});
	initEvent(chartPie, clickCall);
}

function clickCall(evt) {
	var segments = chartPie.getSegmentsAtEvent(evt);
	
	if ( segments.length > 0 ) {
		var segment = segments[0];
		alert( segment.label + ": " + segment.value);
	}
}

function initEvent(chart, handler) {
	var method = handler;
	var eventType = "click";
	var node = chart.chart.canvas;
					
	if (node.addEventListener) {
		node.addEventListener(eventType, method);
	} else if (node.attachEvent) {
		node.attachEvent("on" + eventType, method);
	} else {
		node["on" + eventType] = method;
	}
}