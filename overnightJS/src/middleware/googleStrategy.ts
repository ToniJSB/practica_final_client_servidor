var passport = require('passport')
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy
require('../config/environment')
// Use the GoogleStrategy within Passport.
//   Strategies in Passport require a `verify` function, which accept
//   credentials (in this case, an accessToken, refreshToken, and Google
//   profile), and invoke a callback with a user object.
const GOOGLE_AUTHORIZATION_URL = "https://accounts.google.com/o/oauth2/v2/auth"
const GOOGLE_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token"
const GOOGLE_USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo"

passport.use(new GoogleStrategy({
  clientID: process.env.GOOGLE_CLIENT_ID,
  clientSecret: process.env.GOOGLE_CLIENT_SECRET,
  callbackURL: "/login/google/callback"
},
  function (accessToken: any, refreshToken: any, profile: any, done: any) {

    let userEmail = profile.emails[0].value
    console.log(userEmail)  
    //here we have to authenticate the email
    // if it does not exist, we have to throw an error,
    // if it does exists, we create a jwt
    return done(null,userEmail)  
  }


))