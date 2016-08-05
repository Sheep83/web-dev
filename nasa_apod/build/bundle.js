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

	window.onload = function () {
	  visited = [];
	  var url = 'https://api.nasa.gov/planetary/apod?&api_key=T1AUnT68vq8FeqlfaGROtZl5h6mk9iMoz9Z7MKNy';
	  var calendar = document.getElementById('inputDate');
	  var button = document.getElementById('random');
	  var historyDropDown = document.getElementById('history');
	  var fullSizeButton = document.getElementById('fullSize');
	  var local = localStorage;
	  console.log(local);
	  calendar.onchange = getByDate;
	  button.onclick = randomDate;
	  historyDropDown.onchange = getFromHistory;
	    // fullSizeButton.onclick = showFullSize;
	    // console.log(url);
	    var request = new XMLHttpRequest();
	    request.open("GET", url);
	    request.onload = function () {
	      if (request.status === 200) {
	        var jsonString = request.responseText;
	        var data = JSON.parse(jsonString);
	        console.log(data);
	      }
	      main(data, local)
	    }
	    request.send();
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
	    // console.log(data);
	    if (data.media_type === "image"){
	      showImage(data.url, 500, 375, data.url);
	    }else if (data.media_type === 'video'){
	      showImage('/Under-construction.jpeg', 500, 375, '/Under-construction.jpeg');
	      console.log('Video Support Coming Soon!')
	      // showVideo(data.url, 500, 375, data.url)
	    }
	
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
	
	var showVideo = function(src, width, height, alt) {
	  var imgDiv = document.getElementById('img');
	  if (imgDiv.firstChild){
	    imgDiv.removeChild(imgDiv.firstChild);
	    var video = document.createElement("video");
	    video.src = src;
	    video.width = 500;
	    video.height = 375;
	    video.alt = src;
	    video.type="video/mp4";
	    imgDiv.appendChild(video);
	  }else{var video = document.createElement("video");
	  video.src = src;
	  video.width = 500;
	  video.height = 375;
	  video.alt = src;
	  video.type="video/mp4";
	  imgDiv.appendChild(video);
	  video.play();
	}
	
	}
	
	var main = function(data){
	  apodDisplay(data);
	  populateHistory();
	}
	
	var randomDate = function(){
	  array = [];
	  randDay = Math.floor((Math.random() * 28) + 1);
	  randMonth = Math.floor((Math.random() * 12) + 1);
	  randYear = Math.floor(Math.random() * (2015 - 1996) + 1996);
	  array.push(randYear, randMonth, randDay);
	  randDate = array.join('-');
	  // console.log(randDate);
	  url = 'https://api.nasa.gov/planetary/apod?date=' + randDate + '&api_key=T1AUnT68vq8FeqlfaGROtZl5h6mk9iMoz9Z7MKNy';
	  var request = new XMLHttpRequest();
	  request.open("GET", url);
	  request.onload = function () {
	    if (request.status === 200) {
	      var jsonString = request.responseText;
	      var data = JSON.parse(jsonString);
	      console.log(data);
	      localStorage.setItem('selectedHistory', JSON.stringify(data));
	      console.log(localStorage);
	      // addToStorage(data);
	      console.log(localStorage);
	      
	      // console.log(data);
	      // var local = localStorage.getItem("selectedHistory");
	      // var localObject = JSON.parse(local);
	      //     console.log(localObject);
	      //     if (localObject === null){
	      //     console.log('Local Storage Empty!')
	      //     localStorage.setItem("selectedHistory", JSON.stringify(data)); 
	      //     console.log('Local Storage Updated!')
	
	      //     }else{
	      //     visited.push(data);
	      //     // visited.forEach(function(index, object){
	      //     //   visited.push(object);
	      //     // console.log(visited);
	      //     var objectsSent = JSON.stringify(visited);
	      //     var objects = localStorage.setItem("selectedHistory", (objectsSent));
	
	      
	      //     var retrieved = localStorage.getItem("selectedHistory");
	      //     var objectsGot = JSON.parse(retrieved);
	      //     console.log(objectsGot);
	
	
	          
	          // visited.push(data);
	
	          // local.push(data);
	          // console.log(visited);
	          // local.push(data);
	          // console.log(local);
	          // localStorage.setItem("selectedHistory", JSON.stringify(visited));
	          // console.log(localStorage);
	        // }
	      }
	      
	      main(data);
	    }
	    request.send();
	  }
	
	  var getByDate = function(event){
	    console.log(event);
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
	            // localStorage.setItem("selectedHistory",JSON.stringify(data));
	            // console.log(visited);    
	          }
	          main(data);
	        }
	        request.send();
	      }
	
	      var populateHistory = function(){
	        var local = localStorage.getItem('selectedHistory');
	        console.log( local )
	        local2 = JSON.parse(local);
	        // console.log(local[0]);
	        if (local === null){
	        return
	        }else{
	          visited.push(local)
	          var historyDropDown = document.querySelector('#history');
	          historyDropDown.innerHTML = "";
	          console.log(visited);
	          visited.forEach(function (item, index) {
	            item.index = index;
	            var option = document.createElement("option");
	            option.value = index.toString();
	            option.text = item.title;
	            historyDropDown.appendChild(option);
	          });
	          historyDropDown.style.display = 'block';
	        }
	      }
	
	      var getFromHistory = function(event){
	        console.log(event);
	        var index = this.value;
	        var img = visited[index];
	        // console.log(index);    
	        apodDisplay(img);
	    // localStorage.setItem("selectedImg",JSON.stringify(img));
	
	  }
	
	  // var addToStorage = function(data){
	  //   var local = localStorage.getItem("selectedHistory");
	  //   var localObject = JSON.parse(local);
	  //   console.log(localObject);
	  //   if (local === null){
	  //   var dataToSend = JSON.stringify(data);
	  //   console.log(dataToSend);
	  //   localStorage.setItem("selectedHistory", (dataToSend));
	  //   }else{
	  //   // console.log(localObject); 
	  //   // localObject.forEach(function(object){
	  //     visited.push(localObject);
	  //     console.log(visited);
	  //     visited.forEach(function(object){
	  //       console.log(object.title);
	  //     })
	  //   // })
	  //   }
	  // }
	
	


/***/ }
/******/ ]);
//# sourceMappingURL=bundle.js.map