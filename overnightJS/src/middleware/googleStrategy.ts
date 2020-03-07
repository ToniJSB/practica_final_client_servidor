var passport = require('passport');
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy;
const dotenv = require('./../../.env')
// Use the GoogleStrategy within Passport.
//   Strategies in Passport require a `verify` function, which accept
//   credentials (in this case, an accessToken, refreshToken, and Google
//   profile), and invoke a callback with a user object.
passport.use(new GoogleStrategy({
    clientID: dotenv.GOOGLE_CLIENT_ID,
    clientSecret: dotenv.GOOGLE_CLIENT_SECRET,
    callbackURL: "http://www.example.com/auth/google/callback"
  },
 /* function(accessToken:any, refreshToken:any, profile:any, done:any) {
     //  User.findOrCreate({ googleId: profile.id }, function (err:any, user:any) {
       //  return done(err, user);
       });
  }*/
))