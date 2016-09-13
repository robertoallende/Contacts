var User = require('../models/user');
var express = require('express');
var bodyParser = require('body-parser');
var router = express.Router();
router.use(bodyParser.json());

// GET /users
// Get a list of users
router.get('/', function(req, res) {
  User.find({}, function(err, users) {
    if (err) {
      return res.status(500).json({
        error: "Error listing users: " + err
      });
    }

    res.json(users);
  });
});

// GET /users/:id
// Get a user by ID
router.get('/:id', function(req, res) {
  User.findOne({
    _id: req.params.id
  }, function(err, user) {
    if (err) {
      return res.status(500).json({
        error: "Error reading user: " + err
      });
    }

    if (!user) {
      return res.status(404).end();
    }

    res.json(user);
  });
});

router.post('/new', function(req, res) {
  var user = new User(req.body);
  user.save( function(error, data){
    if(error){
        return false;
    }
    else {
        // res.json(data);
        return true;
    }
  });
});

router.delete('/:id', function(req, res) {
  findByIdAndRemove(req.session.user._id, {}, function(error, data) {
    if(error){
        return false;
    }
    else {
        return true;
    }
  }

});


module.exports = router;
