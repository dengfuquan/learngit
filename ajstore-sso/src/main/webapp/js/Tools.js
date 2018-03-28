	//创建类
	function Map() {
		var myArray = new Array();
		this.put = function(key, value) {
			myArray[key] = value;
		}
		this.get = function(key) {
			var value = myArray[key];
			if (value) {
				return value;
			}
			return null;
		}
	}
	//test.html?from=portal&usename=a
	//?from=portal&usename=a
	function searchToMap() {
			var map = new Map();
		//console.log(location.search);
		var string = location.search;
		if (string.indexOf("?") >= 0) {
			//from=portal&usename=a
			string = string.substr(1);
			//console.log(string);
			var nameValues = string.split("&");
			for (var i = 0; i < nameValues.length; i++) {
				//from=portal
				//console.log(nameValues[i]);
				var keyvalue = nameValues[i].split("=");
				//console.log(keyvalue[0]);
				//console.log(keyvalue[1]);
				map.put(keyvalue[0], keyvalue[1])
			}
		}
		return map;
	}