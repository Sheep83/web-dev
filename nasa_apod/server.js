var express = require('express');
var app = express();
var path = require('path')
var fs  = require('fs');
var bodyParser = require('body-parser');
var MongoClient = require('mongodb').MongoClient;

app.use(bodyParser.json());

app.get('/', function (req, res) {
  res.sendFile(path.join(__dirname + '/index.html'));
});

app.get('/history', function(req,res){
   // Connection URL
   var url = 'mongodb://localhost:27017/apod';
   MongoClient.connect(url, function(err, db) {
     var collection = db.collection('history');
     collection.find({}).toArray(function(err, docs) {
       res.json(docs)
       db.close();
     });
   });
 })

app.post('/history', function(req,res){
 var url = 'mongodb://localhost:27017/apod';
   MongoClient.connect(url, function(err, db) {
     var collection = db.collection('history');
       collection.insert(req.body)
       res.status(200).end()
       db.close();
   });
 })

app.use(express.static('public'));

var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('NASA APoD app listening at http://%s:%s', host, port);
});

