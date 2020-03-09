"use strict";
var passport = require('passport');
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy;
require('../config/environment');
var GOOGLE_AUTHORIZATION_URL = "https://accounts.google.com/o/oauth2/v2/auth";
var GOOGLE_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";
var GOOGLE_USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";
passport.use(new GoogleStrategy({
    clientID: process.env.GOOGLE_CLIENT_ID,
    clientSecret: process.env.GOOGLE_CLIENT_SECRET,
    callbackURL: "/login/google/callback"
}, function (accessToken, refreshToken, profile, done) {
    var userEmail = profile.emails[0].value;
    console.log(userEmail);
    return done(null, userEmail);
}));
//# sourceMappingURL=googleStrategy.js.map