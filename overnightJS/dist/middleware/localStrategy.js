"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var mysql_1 = require("../mysql/mysql");
var passport = require('passport'), LocalStrategy = require('passport-local').Strategy;
var email = '551';
passport.use(new LocalStrategy(function (email, password, done) {
    console.log('entra');
    var query = 'SELECT * FROM user WHERE email = ' + email;
    mysql_1.default.ejecutarQuery(query, function (err, result, done) {
        console.log(result);
    });
}));
//# sourceMappingURL=localStrategy.js.map