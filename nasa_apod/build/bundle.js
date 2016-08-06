/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
/******/
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports) {

	var lastImage = [];
	
	window.onload = function () {  
	  var url = 'https://api.nasa.gov/planetary/apod?&api_key=T1AUnT68vq8FeqlfaGROtZl5h6mk9iMoz9Z7MKNy';
	  var calendar = document.getElementById('inputDate');
	  var button = document.getElementById('random');
	  var historyDropDown = document.getElementById('history');
	  var fullSizeButton = document.getElementById('fullSize');
	  var favouriteButton = document.getElementById('favourite');
	  var getFavourites = document.getElementById('favs');
	  
	  calendar.onchange = getByDate;
	  button.onclick = randomDate;
	  historyDropDown.onchange = getFromHistory;
	  favouriteButton.onclick = addToFavourites;
	  getFavourites.onchange = getFromFavourites;
	    // fullSizeButton.onclick = showFullSize;
	    // console.log(url);
	    var request = new XMLHttpRequest();
	    request.open("GET", url);
	    request.onload = function () {
	      if (request.status === 200) {
	        var jsonString = request.responseText;
	        var data = JSON.parse(jsonString);
	      }
	      main(data);
	    }
	    request.send();
	  }
	
	  var main = function(data){
	    apodDisplay(data);
	    populateHistory();
	    populateFavourites();
	
	  }
	
	  var apodDisplay = function(data){
	    var stats = document.querySelectorAll('#info p');
	    var title = document.getElementById('subheading');
	    var image = document.getElementById('fullSize');
	    image.innerHTML = "<a href= '"+ data.hdurl + "'>Full Size"
	    title.innerHTML = "<b><center>" + data.title + "</center></b>"
	    stats[0].innerHTML = "" + data.explanation;
	    stats[1].innerHTML = "<b>Copyright : </b>" + data.copyright;
	    stats[2].innerHTML = "<b>Date : </b>" + data.date;
	    if (data.media_type === "image"){
	      showImage(data.url, 500, 375, data.url);
	    }else if (data.media_type === 'video'){
	      showImage('/Under-construction.jpeg', 500, 375, '/Under-construction.jpeg');
	      console.log('Video Support Coming Soon!')
	      // showVideo(data.url, 500, 375, data.url)
	    }
	    if (lastImage.length > 0){
	      lastImage.pop();
	      lastImage.push(data);
	    }else{
	      lastImage.push(data);
	    }
	    console.log(lastImage);
	  }
	
	  var showImage = function(src, width, height, alt) {
	    var imgDiv = document.getElementById('img');
	    if (imgDiv.firstChild){
	      imgDiv.removeChild(imgDiv.firstChild);
	      var img = document.createElement("img");
	      img.src = src;
	      img.width = 500;
	      img.height = 375;
	      img.alt = src;
	      imgDiv.appendChild(img);
	    }else{var img = document.createElement("img");
	    img.src = src;
	    img.width = 500;
	    img.height = 375;
	    img.alt = src;
	    imgDiv.appendChild(img);
	  }
	}
	
	var randomDate = function(){
	  array = [];
	  randDay = Math.floor((Math.random() * 28) + 1);
	  randMonth = Math.floor((Math.random() * 12) + 1);
	  randYear = Math.floor(Math.random() * (2015 - 1996) + 1996);
	  array.push(randYear, randMonth, randDay);
	  randDate = array.join('-');
	  url = 'https://api.nasa.gov/planetary/apod?date=' + randDate + '&api_key=T1AUnT68vq8FeqlfaGROtZl5h6mk9iMoz9Z7MKNy';
	  var request = new XMLHttpRequest();
	  request.open("GET", url);
	  request.setRequestHeader("Content-Type", "application/json");
	       request.onload = function(){
	         if(request.status === 200){
	          var jsonString = request.responseText;
	          var data = JSON.parse(jsonString);
	        } 
	        main(data);
	        saveToDb(data);
	      }
	      request.send();
	    }
	
	    var getByDate = function(event){
	      // console.log(event);
	      selected = event.target.value;
	      url = 'https://api.nasa.gov/planetary/apod?&date=' + selected + '&api_key=T1AUnT68vq8FeqlfaGROtZl5h6mk9iMoz9Z7MKNy';
	      var request = new XMLHttpRequest();
	      request.open("GET", url);
	      request.onload = function () {
	        if (request.status === 200) {
	          var jsonString = request.responseText;
	          var data = JSON.parse(jsonString);
	          visited.push(data);
	          var length = localStorage.length;
	        }
	        main(data);
	        saveToDb(data);
	      }
	      request.send();
	    }
	
	    var populateHistory = function(){
	      var request = new XMLHttpRequest();
	      request.open("GET", '/history');
	      request.setRequestHeader("Content-Type", "application/json");
	             // console.log(request);
	             request.onload = function(){
	               if(request.status === 200){
	                var jsonString = request.responseText;
	                var history = JSON.parse(jsonString);
	              }
	              console.log(history.length);
	              if(history.length > 3){
	                history.shift();
	                // history.push(lastImage[0]);
	              }else
	              {history.push(lastImage[0]);
	              }
	              var historyDropDown = document.querySelector('#history');
	              historyDropDown.innerHTML = "";
	              history.forEach(function (item, index) {
	                item.index = index;
	                var option = document.createElement("option");
	                option.value = index.toString();
	                option.text = item.title;
	                historyDropDown.appendChild(option);
	              });
	              historyDropDown.style.display = 'block';
	            }
	            request.send();
	
	          }
	
	          var getFromHistory = function(event){
	              var index = this.value;
	              var request = new XMLHttpRequest();
	              request.open("GET", '/history');
	              request.setRequestHeader("Content-Type", "application/json");
	                   request.onload = function(){
	                     if(request.status === 200){
	                      var jsonString = request.responseText;
	                      var history = JSON.parse(jsonString);
	                    }
	                    var img = history[index];
	                    apodDisplay(img);
	                  }
	                  request.send();
	                }
	
	                var addToFavourites = function(event){
	                  console.log(event);
	                  var request = new XMLHttpRequest();
	                  request.open("POST", '/favourites');
	                  request.setRequestHeader("Content-Type", "application/json");
	             request.onload = function(){
	              if(request.status === 200){
	                console.log('Posted to Favourites!');
	              }
	                populateFavourites();
	
	            }
	            request.send(JSON.stringify(lastImage[0]));
	          }
	
	          var saveToDb = function(data){
	           var request = new XMLHttpRequest();
	           request.open("POST", '/history');
	           request.setRequestHeader("Content-Type", "application/json");
	             request.onload = function(){
	              if(request.status === 200){
	                console.log('Posted to Db!');
	              }
	            }
	            request.send(JSON.stringify(data));
	          }
	
	          var populateFavourites = function(){
	            var request = new XMLHttpRequest();
	            request.open("GET", '/favourites');
	            request.setRequestHeader("Content-Type", "application/json");
	                   request.onload = function(){
	                     if(request.status === 200){
	                      var jsonString = request.responseText;
	                      var favourites = JSON.parse(jsonString);
	                      console.log(favourites);
	                    }
	                    var favouritesDropDown = document.querySelector('#favs');
	                    favouritesDropDown.innerHTML = "";
	                    favourites.forEach(function (item, index) {
	                      item.index = index;
	                      var option = document.createElement("option");
	                      option.value = index.toString();
	                      option.text = item.title;
	                      favouritesDropDown.appendChild(option);
	                    });
	                    favouritesDropDown.style.display = 'block';
	                  }
	                  request.send();
	
	                }
	                var getFromFavourites = function(event){
	                    var index = this.value;
	                    var request = new XMLHttpRequest();
	                    request.open("GET", '/favourites');
	                    request.setRequestHeader("Content-Type", "application/json");
	                         request.onload = function(){
	                           if(request.status === 200){
	                            var jsonString = request.responseText;
	                            var favourites = JSON.parse(jsonString);
	                          }
	                          var img = favourites[index];
	                          apodDisplay(img);
	                        }
	                        request.send();
	                      }
	
	
	
	  // var showVideo = function(src, width, height, alt) {
	  //   var imgDiv = document.getElementById('img');
	  //   if (imgDiv.firstChild){
	  //     imgDiv.removeChild(imgDiv.firstChild);
	  //     var video = document.createElement("video");
	  //     video.src = src;
	  //     video.width = 500;
	  //     video.height = 375;
	  //     video.alt = src;
	  //     video.type="video/mp4";
	  //     imgDiv.appendChild(video);
	  //   }else{var video = document.createElement("video");
	  //   video.src = src;
	  //   video.width = 500;
	  //   video.height = 375;
	  //   video.alt = src;
	  //   video.type="video/mp4";
	  //   imgDiv.appendChild(video);
	  //   video.play();
	  // }
	
	  // }
	
	
	


/***/ }
/******/ ]);
//# sourceMappingURL=bundle.js.map