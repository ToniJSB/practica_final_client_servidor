var passport = require('passport')
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy
require('../config/environment')
// Use the GoogleStrategy within Passport.
//   Strategies in Passport require a `verify` function, which accept
//   credentials (in this case, an accessToken, refreshToken, and Google
//   profile), and invoke a callback with a user object.
passport.use(new GoogleStrategy({
  clientID: process.env.GOOGLE_CLIENT_ID,
  clientSecret: process.env.GOOGLE_CLIENT_SECRET,
  callbackURL: "http://localhost:3000/posts"
},
  function (accessToken: any, refreshToken: any, profile: any, done: any) {
    console.log({
      accessToken: accessToken,
      refreshToken: refreshToken,
      profile: profile,
      done: done
    })
  }
  //

))