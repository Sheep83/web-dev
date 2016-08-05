
window.onload = function () {
    // var url = 'https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=T1AUnT68vq8FeqlfaGROtZl5h6mk9iMoz9Z7MKNy'
    var url = 'https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=T1AUnT68vq8FeqlfaGROtZl5h6mk9iMoz9Z7MKNy'

    var request = new XMLHttpRequest();
    request.open("GET", url);
    request.onload = function () {
        if (request.status === 200) {
            var jsonString = request.responseText;
            var data = JSON.parse(jsonString);
            console.log(data);
            
        }
            main(data);
        
    }
    
    request.send();
};

var main = function (data) {
    populateSelect(data);
    // updateDisplay(data);

    var cached = localStorage.getItem("selectedImg");
    var selected = data.photos[0];
    if(cached){
        selected = JSON.parse(cached);
        document.querySelector('#info').selectedIndex = selected.index;
    }
    updateDisplay(selected);
    document.querySelector('#info').style.display = 'block';
}

var updateDisplay = function (data) {
    var stats = document.querySelectorAll('#info p');
    console.log(data);
    var url = data.img_src;
    // document.getElementById("img-preview").src = url;
    // console.log(data.img_src);
    stats[0].innerHTML = "<b>Rover Name : </b>" + data.rover.name;
    stats[1].innerHTML = "<b>Camera : </b>" + data.camera.full_name;
    showImage(data.img_src, 400, 300, data.img_src);
}
function showImage(src, width, height, alt) {
    var imgDiv = document.getElementById('img');
    // console.log(imgDiv.firstChild);
    if (imgDiv.firstChild){
        // console.log(imgDiv.firstChild);
        imgDiv.removeChild(imgDiv.firstChild);
        var img = document.createElement("img");
        img.src = src;
        img.width = 400;
        img.height = 300;
        img.alt = src;
        imgDiv.appendChild(img);
    }else{var img = document.createElement("img");
        img.src = src;
        img.width = 400;
        img.height = 300;
        img.alt = src;
        imgDiv.appendChild(img);
    }
    
}

var populateSelect = function (data) {
    console.log(data);
    var dropDown = document.querySelector('#dropDown');
    data.photos.forEach(function (item, index) {
        item.index = index;
        var option = document.createElement("option");
        option.value = index.toString();
        option.text = item.rover.name;
        dropDown.appendChild(option);
    });
    dropDown.style.display = 'block';
    dropDown.addEventListener('change', function (e) {
        var index = this.value;
        var img = data.photos[index];
        // console.log(img);
        updateDisplay(img);
        localStorage.setItem("selectedImg",JSON.stringify(img));
    });
}
